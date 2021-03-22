<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="ApiUrl" value="/cart/api/user"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Trang Chủ</title>

    <link href=<c:url value="/templateHome/vendor/bootstrap/css/bootstrap.min.css"/> rel="stylesheet">
    <link href=<c:url value="/templateHome/css/shop-homepage.css"/> rel="stylesheet">

    <!-- thư viện phân trang -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src=<c:url value="/templateHome/paging/jquery.twbsPagination.min.js"/> type="text/javascript"></script>

    <style>
        #paging{
            margin: auto;
            width: 35%;
        }
    </style>
</head>
<body>

<%@include file="/common/home/header.jsp"%>
<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <h1 class="my-4">Shop</h1>
            <div class="list-group">
                <a href="/home?product=quan" class="list-group-item">Quần</a>
                <a href="/home?product=ao" class="list-group-item">Áo</a>
                <a href="/home?product=giay" class="list-group-item">Giày</a>
            </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid" src="<c:url value="/templateHome/image/polo_2-100.jpg"/>" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="<c:url value="/templateHome/image/outlet.jpg"/>" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="<c:url value="/templateHome/image/Collection_(9).jpg"/>" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div class="row">
                <c:forEach var="item" items="${products}">
                    <input type="hidden" value="${item.id}" class="product_id">
                    <div class="col-lg-4 col-md-6 mb-4" style="float: left;">
                        <div class="card h-100">
                            <a href="#"><img style="height: 300px;" class="card-img-top" src=<c:url value="${item.thumbnail}"/> alt=""></a> <!-- thumbnail -->
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="#">${item.name}</a> <!-- name -->
                                </h4>
                                <h5>${item.price}</h5> <!-- Price -->
                                <p class="card-text">${item.description}</p> <!-- description -->
                                <button class="btn btn-danger btnAddCart" data-id="${item.id}">Đặt hàng</button>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
                <!-- /.row -->
        </div>
        <!-- /.col-lg-9 -->
    </div>

    <!-- chỗ phân trang -->

    <div id="paging">
        <ul class="pagination" id="pagination"></ul>
    </div>

    <script type="text/javascript">
        var totalPages = 10;
        var currentPage = 1;
        var limit = 9; // số bản ghi hiển thị lên 1 page

        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 5,
                startPage: currentPage,
                limit: limit,
                onPageClick: function (event, page) {

                }
            })
        });
    </script>
    <!-- /.row -->
</div>



<!-- /.container -->
<%@include file="/common/home/footer.jsp"%>
<!-- Bootstrap core JavaScript -->
<%--<script src=<c:url value="/templateHome/vendor/jquery/jquery.min.js"/> ></script>--%>
<%--<script src=<c:url value="/templateHome/vendor/bootstrap/js/bootstrap.bundle.min.js"/> ></script>--%>

<script>
    // cách lấy id trong forEach thêm data-id vào chỗ nút button rồi xử lý code bên dưới
    $('.btnAddCart').click(function (e){
        e.preventDefault();
        let data = {};
        let productId = $(this).data("id");

        data = {
            productId : productId
        };

        addToCart(data);
    });


    function addToCart(data) {
        $.ajax({
            url: '${ApiUrl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (){
                alert('Thêm vào giỏ hàng thành công');
            },
            error: function (){
                window.open("/login","_self");
            }
        });
    }
</script>

</body>
</html>
