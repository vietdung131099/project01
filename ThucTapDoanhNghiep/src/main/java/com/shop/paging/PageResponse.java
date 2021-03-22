package com.shop.paging;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PageResponse <T>{
    private List<T> data = new ArrayList<>();
    private long totalItem;
    private long totalPage;
    private int pageIndex;
    private int pageSize;

    private PageResponse(List<T> data, long totalItem, int pageIndex, int pageSize) {
        this.data = data;
        this.totalItem = totalItem;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalItem < pageSize ? 1 : (long) Math.ceil(((double ) this.totalItem) / this.pageSize);
    }

    public static <T> PageResponse<T> of(List<T> data, long totalItem, int pageIndex, int pageSize){
        return new PageResponse<>(data, totalItem, pageIndex, pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
