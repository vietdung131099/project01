<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/admin/api/user"/>
<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="<c:url value='/template/assets/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/assets/css/ace.min.css' />" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <script src="<c:url value='/template/assets/js/ace-extra.min.js' />"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body class="no-skin">
<!-- header -->
<%@ include file="/common/header.jsp" %>
<!-- header -->

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">User</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>
                    <form id="formUpdateOrCreate">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Quyền</label>
                            <div class="col-sm-9">
                                <c:forEach var="item" items="${roles}">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="roleIds" value="${item.id}"
                                               id="defaultCheck2">
                                        <label class="form-check-label" for="defaultCheck2">
                                                ${item.name}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên đăng nhập</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="thumbnail" name="userName"
                                       value="${user.userName}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Password</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="shortDescription" name="password"
                                       value="${user.password}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">email</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${user.email}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số điện thoại</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="phone" name="phone"
                                       value="${user.phone}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Họ Tên</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="name" name="name"
                                       value="${user.name}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">

                            <input type="hidden" class="form-control" id="id" name="id" value="${user.id}"/>

                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty user.id}">
                                    <input type="button" class="btn btn-danger"
                                           value="Cập nhật user"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                                <c:if test="${empty user.id}">
                                    <input type="button" class="btn btn-danger"
                                           value="Thêm mới user"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                            </div>
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btnAddOrUpdate').click(function (e){
        let data = {};
        let ids = [];
        let dataForm = $('#formUpdateOrCreate').serializeArray();

        $.each(dataForm,function (i,v){
            if(v.name === 'roleIds'){
                ids.push(v.value);
            } else {
                data[""+v.name+""] = v.value;
            }
        })
        data["ids"] = ids;


        if(data['id']==''){
            addUser(data);
        } else {
            updateUser(data);
        }


    })

    function addUser(data) {
        $.ajax({
            url: '${ApiUrl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (){
                window.open("/admin?pageIndex=1&pageSize=10","_self");
            },
            error: function (){
                alert("Thêm Thất Bại");
            }
        });
    }

    function updateUser(data) {
        $.ajax({
           url: '${ApiUrl}',
           type: 'PUT',
           contentType: 'application/json',
           data: JSON.stringify(data),
           success: function (){
               window.open("/admin?pageIndex=1&pageSize=10","_self");
           },
           error: function (){
               alert("Cập nhật Thất Bại");
           }
        });
    }
</script>

<%@ include file="/common/footer.jsp" %>
<!-- footer -->

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
</div>


<script src="<c:url value='/template/assets/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery-ui.custom.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.easypiechart.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.flot.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.flot.pie.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.flot.resize.min.js' />"></script>
<script src="<c:url value='/template/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/assets/js/ace.min.js' />"></script>
<script src="<c:url value='/template/assets/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/template/assets/js/jquery-ui.min.js'/>"></script>
</body>
</html>