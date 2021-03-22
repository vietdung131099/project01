package com.shop.repositories.impl;

import com.shop.constant.annotation.Id;
import com.shop.listerner.ApplicationListener;
import com.shop.paging.PageAble;
import com.shop.paging.PageRequest;
import com.shop.paging.PageResponse;
import com.shop.repositories.JpaRepository;
import com.shop.repositories.Query;
import com.shop.repositories.ReferenceTable;
import com.shop.repositories.Value;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.shop.utils.ReflectionUtils.*;

public class BaseRepository<T, ID> implements JpaRepository<T, ID> {
    private Class<T> tClass;
    private String tableName;
    private static String insert = "INSERT INTO %s(%s) VALUES(%s)";
    private static String update = "UPDATE %s SET %s WHERE %s";
    private static String selectByCondition = "SELECT * FROM %s WHERE %s";
    private static String selectAll = "SELECT * FROM %s";
    private static String delete = "DELETE FROM %s WHERE %s";
    private static String count = "SELECT COUNT(1) FROM %s";



    public BaseRepository(){
        this.tClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.tableName = getClassName(tClass);
    }

    @Override
    public T save(T t) {
        StringBuilder attributes = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Field[] fields = tClass.getDeclaredFields();
        boolean checkAutoIncrement = false;
        int index = 0;
        int count = 0;

        for(Field field : fields){
            if(field.isAnnotationPresent(Id.class)){
                index = count;
                checkAutoIncrement = autoIncrement(tClass,field.getName());
                if(checkAutoIncrement){
                    continue;
                }
                attributes.append(primaryName(tClass,field.getName())).append(",");
                values.append("?,");
                continue;
            }
            attributes.append(getColumnName(tClass,field.getName())).append(",");
            values.append("?,");
            count++;
        }
        String sql = String.format(insert,tableName,attributes.deleteCharAt(attributes.length()-1),values.deleteCharAt(values.length()-1));

        Connection connection = ApplicationListener.connectionManagerment.connection();

        try {
            connection.setAutoCommit(false); // sql transaction
            PreparedStatement ps = connection.prepareStatement(sql);
            int j = 0;
            for(int i=0;i< fields.length;i++){
                if(i == index){
                    if(checkAutoIncrement){
                        continue;
                    }
                    ps.setObject(j+1,methodGet(t,fields[i].getName()));
                    j++;
                    continue;
                }
                ps.setObject(j+1,methodGet(t, fields[i].getName()));
                j++;
            }
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return t;
    }

    @Override
    public void update(ID id, T t) {
        StringBuilder attributes = new StringBuilder();
        StringBuilder condition = new StringBuilder();
        Field[] fields = tClass.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            Field field = fields[i];
            if(field.isAnnotationPresent(Id.class)){
                condition.append(primaryName(tClass,field.getName())).append(" = ?");
                continue;
            }
            if(methodGet(t, field.getName()) == null){
                continue;
            }
            attributes.append(getColumnName(tClass, field.getName())).append(" = ?,");
        }
        String sql = String.format(update,tableName,attributes.deleteCharAt(attributes.length()-1),condition);
        Connection connection = ApplicationListener.connectionManagerment.connection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            int j=1;
            for(int i=0;i<fields.length;i++){
                Field field = fields[i];
                if(field.isAnnotationPresent(Id.class)){
                    continue;
                }
                if(methodGet(t, field.getName()) == null){
                    continue;
                }
                ps.setObject(j++, methodGet(t,field.getName()));
            }
            ps.setObject(j, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        Field[] fields = tClass.getDeclaredFields();
        Field field = null;
        for(Field find : fields){
            if(find.isAnnotationPresent(Id.class)){
                field = find;
                break;
            }
        }
        StringBuilder condition = new StringBuilder();
        condition.append(primaryName(tClass,field.getName())).append(" = ?");
        String sql = String.format(selectByCondition,tableName,condition);

        try(Connection connection = ApplicationListener.connectionManagerment.connection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,id);
            ResultSet rs = ps.executeQuery();
            T t = null;
            if(rs.next()){
                t = convertToEntity(rs,tClass);
            }

            return Optional.of(t);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(ID id) {
        Field[] fields = tClass.getDeclaredFields();
        Field field = null;
        for(Field find : fields){
            if(find.isAnnotationPresent(Id.class)){
                field = find;
                break;
            }
        }

        StringBuilder condition = new StringBuilder();
        condition.append(primaryName(tClass,field.getName())).append(" = ?");
        String sql = String.format(delete,tableName,condition);
        Connection connection = ApplicationListener.connectionManagerment.connection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Stream<T> findAll() {
        String sql = String.format(selectAll,tableName);
        try (Connection connection = ApplicationListener.connectionManagerment.connection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<T> results = new ArrayList<>();
            while (rs.next()){
                T t = convertToEntity(rs,tClass);
                results.add(t);
            }
            return results.stream();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Stream.empty();
    }

    @Override
    public Stream<T> findAll(PageAble pageAble) {

        try (Connection connection = ApplicationListener.connectionManagerment.connection()){
            String sql = String.format(selectAll,tableName);
            if(pageAble != null){
                sql = sql + " LIMIT ? OFFSET ?";
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,pageAble.getSize());
            ps.setInt(2,pageAble.getOffset());
            ResultSet rs = ps.executeQuery();
            List<T> results = new ArrayList<>();
            while (rs.next()){
                T t = convertToEntity(rs,tClass);
                results.add(t);
            }
            return results.stream();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Stream.empty();
    }

    @Override
    public long count() {
        String sql = String.format(count,tableName);
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getLong(1);
            }

            return 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Stream<T> findAll(Query<T> query) { // 39:00 video 60 java08
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            String sql = toQuery(query, selectAll,null);
            PreparedStatement ps = connection.prepareStatement(sql);
            if (query != null && !query.values().isEmpty()){
                for (Value value : query.values()){
                    ps.setObject(value.getIndex(), value.getValue());
                }
            }
            ResultSet rs = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while(rs.next()){
                T t = convertToEntity(rs,tClass);
                list.add(t);
            }

            return list.stream();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return Stream.of();
        }
    }

    @Override
    public PageResponse<T> findAll(Query<T> query, PageRequest pageRequest) {
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            String sql = toQuery(query, selectAll, pageRequest);
            PreparedStatement ps = connection.prepareStatement(sql);
            if (query != null && !query.values().isEmpty()) {
                for (Value value : query.values()){
                    ps.setObject(value.getIndex(), value.getValue());
                }
            }
            if(pageRequest != null){
                int i = (query == null || query.values().isEmpty()) ? 0 : query.values().size();
                ps.setObject(i+1, pageRequest.getSize());
                ps.setObject(i+2,pageRequest.getOffset());
            }
            ResultSet rs = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while(rs.next()){
                T t = convertToEntity(rs,tClass);
                list.add(t);
            }
            long totalItem = count(query);
            
            return PageResponse.of(list,totalItem, pageRequest.getIndex(), pageRequest.getSize());

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return null;
        }
    }

    @Override
    public long count(Query<T> query) {
        try(Connection connection = ApplicationListener.connectionManagerment.connection()){
            String sql = toQuery(query,count,null);
            PreparedStatement ps = connection.prepareStatement(sql);
            if (query != null && !query.values().isEmpty()){
                for (Value value : query.values()){
                    ps.setObject(value.getIndex(), value.getValue());
                }
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getLong(1);
            }

            return 0;

        } catch (Exception throwables){
            throwables.printStackTrace();
            return 0;
        }

    }

    @Override
    public Optional<T> findOne(Query<T> query) {
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            String sql = toQuery(query,selectAll,null);
            PreparedStatement ps = connection.prepareStatement(sql);
            if (query != null && !query.values().isEmpty()){
                for (Value value : query.values()){
                    ps.setObject(value.getIndex(), value.getValue());
                }
            }
            ResultSet rs = ps.executeQuery();
            T t = null;
            while(rs.next()){
                t = convertToEntity(rs,tClass);
            }

            return Optional.of(t);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return Optional.empty();
        }
    }

    private String toQuery(Query<T> query, String rootQuery, PageRequest pageRequest){ // đoạn này logic hơi loằng ngoằng
        StringBuilder sql = new StringBuilder();
        String finalQuery = String.format(rootQuery,tableName);
        sql.append(finalQuery);
        if(query == null){
            if(pageRequest != null){
                sql.append(" LIMIT ? OFFSET ?");
            }
            return sql.toString();
        }
        if(!query.reference().isEmpty()) { //hihi
            for (Map.Entry<String, ReferenceTable> entry : query.reference().entrySet()){
                sql.append(" JOIN ")
                        .append(entry.getKey())
                        .append(" ON ")
                        .append(entry.getValue().getConditionJoin());
            }
        }
        if (StringUtils.isEmpty(query.condition())){
            return sql.toString();
        }
        sql.append(" WHERE ").append(query.condition());

        if(pageRequest != null){
            sql.append(" LIMIT ? OFFSET ?");
        }

        return sql.toString();
    }

}
