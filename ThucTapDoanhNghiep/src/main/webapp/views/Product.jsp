<%@include file="../common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="ApiUrl" value="/product/api/user"/>

<html>
<head>
    <title>Product</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href=<c:url value="/template/assets/css/bootstrap.min.css" /> />
    <link rel="stylesheet" href=<c:url value="/template/assets/font-awesome/4.5.0/css/font-awesome.min.css" /> />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href=<c:url value="/template/assets/css/fonts.googleapis.com.css" /> />

    <!-- ace styles -->
    <link rel="stylesheet" href=<c:url value="/template/assets/css/ace.min.css" /> class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href=<c:url value="/template/assets/css/ace-part2.min.css" /> class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href=<c:url value="/template/assets/css/ace-skins.min.css" /> />
    <link rel="stylesheet" href=<c:url value="/template/assets/css/ace-rtl.min.css" /> />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href=<c:url value="/template/assets/css/ace-ie.min.css" /> />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src=<c:url value="/template/assets/js/ace-extra.min.js"/> ></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src=<c:url value="/template/assets/js/html5shiv.min.js"/> ></script>
    <script src=<c:url value="/template/assets/js/respond.min.js"/> ></script>
    <![endif]-->

</head>

<body class="no-skin">

<%@include file="/common/header.jsp"%> <!-- include header -->

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>

    <%@include file="/common/menu.jsp"%> <!-- include menu -->

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/home">Trang chủ</a>
                    </li>

                    <li>
                        <a href="/cart">Giỏ hàng</a>
                    </li>
                    <li class="active">Blank Page</li>
                </ul><!-- /.breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- /.nav-search -->
            </div>


            <div class="page-content">

                <form id="formSubmit">
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Thumbnail</th>
                                    <th>Price</th>
                                    <th>Category_id</th>
                                    <th>Action</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="item" items="${products}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.name}</td>
                                        <td>${item.description}</td>
                                        <td><img style="width: 50px; height: 50px;" src=<c:url value="${item.thumbnail}"/> ></td>
                                        <td>${item.price}</td>
                                        <td>${item.categoryId}</td>
                                        <th align="center">
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="update user"
                                               href="/product/user?action=update&id=${item.id}">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>

                                            <a class="btn btn-sm btn-primary btn-edit btnDelete"
                                               data-toggle="tooltip"
                                               title="delete user"
                                               data-id="${item.id}">
                                                <i class="fa fa-trash-o" aria-hidden="true"></i>
                                            </a>
                                        </th>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <input class="btn btn-sm btn-primary btn-edit itemDelete" id="btnThem" value="Add">
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </form>


            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <%@include file="/common/footer.jsp"%> <!-- include footer -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="<c:url value="/template/assets/js/jquery-2.1.4.min.js"/>" ></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="<c:url value="/template/assets/js/jquery-1.11.3.min.js"/>" ></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script  src='<c:url value='/template/assets/js/jquery.mobile.custom.min.js' />'    > "+"<"+"/script>");
</script>
<script src="<c:url value="/template/assets/js/bootstrap.min.js"/>" ></script>

<script src="<c:url value="/template/assets/js/ace-elements.min.js"/>"></script>
<script src="<c:url value="/template/assets/js/ace.min.js"/>"></script>

<script>
    $('.btnDelete').click(function (e){
        e.preventDefault();
        let data = {};
        data['id'] = $(this).data('id');

        deleteProduct(data);
    });

    function deleteProduct(data){
        $.ajax({
            url: '${ApiUrl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (){
              alert('Xóa thành công');
              location.reload();
            },
            error: function (){
                alert('Xóa thất bại');
            }
        })
    }

    // xử lý nút thêm
    $('#btnThem').click(function (){
       window.open("/product/user","_self");
    });
</script>


</body>
</html></title>
</head>
<body>

</body>
</html>
