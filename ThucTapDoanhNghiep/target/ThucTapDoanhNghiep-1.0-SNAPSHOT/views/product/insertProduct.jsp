<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/product/api/user" />
<c:url var="ApiUrlImage" value="/api/image" />
<!DOCTYPE html>
<html>
<head>
    <title>Product</title>
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
                    <form id="formUpdateOrCreate">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Category</label>
                            <div class="col-sm-9">
                                    <select style="width: 300px;" id="category" name="categoryId">
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.id}"> ${item.name} </option>
                                        </c:forEach>
                                    </select>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Name</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name"
                                       value="${product.name}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Description</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="description"
                                       value="${product.description}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thumbnail</label>
                            <div class="col-sm-9">
                                <div class="col-sm-9">
                                    <input type="file" name="thumbnail" id="uploadImage"/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Price</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="price"
                                       value="${product.price}"/>
                            </div>
                        </div>

                        <input type="hidden" class="form-control" id="id" name="id" value="${product.id}"/>

                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty product.id}">
                                    <input type="button" class="btn btn-danger"
                                           value="Cập nhật Product"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                                <c:if test="${empty product.id}">
                                    <input type="button" class="btn btn-danger"
                                           value="Thêm mới Product"
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
    var dataArray = {};  // lấy dữ liệu tên ảnh
    $('#uploadImage').change(function (){
        var files = $('#uploadImage')[0].files[0]; // lấy giá trị

        if(files != undefined) { // có upload file
            var base64 = '';
            var reader = new FileReader(); // fileReader in jquery
            reader.onload = function (e) {
                dataArray['base64'] = e.target.result;
                dataArray['name'] = files.name;
                upload(dataArray);
            };
            reader.readAsDataURL(files);
        }
    });

    function upload(data) {
        $.ajax({
            url : '${ApiUrlImage}',
            type : 'POST',
            contentType: 'application/json',
            data : JSON.stringify(data),
            success : function (res){
                console.log(res);
            },
            error: function (res){
                console.log(res);
            }
        });
    }
    <!-- kết thúc xử lý upload file ảnh -->

    $('#btnAddOrUpdate').click(function (e){
        let data = {};
        let ids = [];
        let dataForm = $('#formUpdateOrCreate').serializeArray();
        $.each(dataForm,function (i,v){
            data[""+v.name+""] = v.value;
        })
        data['thumbnail'] = '/templateHome/image/'+dataArray['name'];

        if(data['id']==''){
            addProduct(data);
        } else {
            updateProduct(data);
        }

    })

    function addProduct(data){
        $.ajax({
            url: '${ApiUrl}',
            type: 'POST',
            contentType: 'application/json',
            data : JSON.stringify(data),
            success : function (){
                window.open("/product","_self");
            },
            error : function (){
                alert('Thêm sản phẩm thất bại');
            }
        });
    }

    function updateProduct(data){
        $.ajax({
            url: '${ApiUrl}',
            type: 'PUT',
            contentType: 'application/json',
            data : JSON.stringify(data),
            success : function (){
                window.open("/product","_self");
            },
            error : function (){
                alert('Cập nhật sản phẩm thất bại');
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