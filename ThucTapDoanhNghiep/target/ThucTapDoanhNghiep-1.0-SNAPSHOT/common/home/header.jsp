<%@ page import="com.shop.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/home">Adidas Originals</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/home">Trang Chủ
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cart">Giỏ Hàng</a>
                </li>
                <c:if test="${not empty user}">
                    <li class="light-blue dropdown-modal" style="margin: 8px;">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <span class="user-info" style="color: white;">
					    				<small>Welcome,</small>
					    				${user}
                                </span>
                            <i class="ace-icon fa fa-caret-down"></i>
                        </a>

                        <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <form action="<c:url value="/logout?action=logout"/>" method="post">
                                    <input type="submit" class="btn btn-danger" value="Logout">
                                </form>
                            </li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${empty user}">
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Đăng Nhập</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
