<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="ApiUrl" value="/cart/api/user"/>
<html>
<head>
    <title>Giỏ Hàng</title>

<%--    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>--%>
<%--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>


    <link href=<c:url value="/templateHome/vendor/bootstrap/css/bootstrap.min.css"/> rel="stylesheet">
    <link href=<c:url value="/templateHome/css/shop-homepage.css"/> rel="stylesheet">

</head>
<body>

<%@include file="/common/home/header.jsp"%>


<div class="container" style="margin-bottom: 300px;">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">GIỎ HÀNG</h1>
        </div>
    </section>

    <div class="container mb-4">
        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col" class="text-center">Số lượng</th>
                            <th scope="col" class="text-right">giá</th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="item" items="${products}">
                            <tr>
                                <td><img style="height: 50px; width: 50px;" src=<c:url value="${item.thumbnail}"/> /> </td>
                                <td>${item.name}</td>
                                <td>Còn hàng</td>
                                <td>
<%--                                    <input class="form-control" type="text" value="1" />--%>
                                    <div class="buttons_added" style="margin: auto; width: 65%;">
                                        <input class="minus is-form" type="button" value="-">
                                        <input style="width: 100px;" aria-label="quantity" class="input-qty" max="Số tối đa" min="Số tối thiểu" name="" type="number" value="1">
                                        <input class="plus is-form" type="button" value="+">
                                    </div>
                                </td>
                                <td class="text-right">${item.price}</td>
                                <td class="text-right"><button class="btn btn-sm btn-danger btnDelete" data-id="${item.id}">Xóa</button> </td>
                            </tr>
                        </c:forEach>

                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Tạm tính</td>
                            <td class="text-right">${tamtinh}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Phí giao hàng</td>
                            <td class="text-right">25000</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Tổng cộng</strong></td>
                            <td class="text-right"><strong>${tongtien}</strong></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col mb-2">
                <div class="row">
                    <div class="col-sm-12  col-md-6">
                        <button id="continueShopping" class="btn btn-lg btn-block btn-danger text-uppercase">Continue Shopping</button>
                    </div>
                    <div class="col-sm-12 col-md-6 text-right">
                        <button id="checkout" class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<%@include file="/common/home/footer.jsp"%>

<script src=<c:url value="/templateHome/vendor/jquery/jquery.min.js"/> ></script>
<script src=<c:url value="/templateHome/vendor/bootstrap/js/bootstrap.bundle.min.js"/> ></script>

<script>

    $('#checkout').click(function (e){
        e.preventDefault();
        $.ajax({
            url : '${ApiUrl}?action=checkout',
            type : "DELETE",
            success : function (){
                alert("Checkout thành công");
                location.reload();
            },
            error : function (){
                alert("Checkout thất bại");
            }
        });
    })

    $('#continueShopping').click(function (){
        window.open("/home","_self");
    })

    $('.btnDelete').click(function (e){
        e.preventDefault();
        let data = {};
        data["id"] = $(this).data("id");

        deleteCart(data);
    })

    function deleteCart(data){
        $.ajax({
            url : '${ApiUrl}',
            type : "DELETE",
            contentType : "application/json",
            data : JSON.stringify(data),
            success : function (){
                alert("Xóa thành công");
                location.reload();
            },
            error : function (){
                alert("Xóa thất bại");
            }
        });
    }

</script>

</body>
</html>
