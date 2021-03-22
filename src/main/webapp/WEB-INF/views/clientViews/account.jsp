<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="label" uri="http://www.springframework.org/tags/form" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>Account</title>
</head>
<body>
<div class="container">
    <div class="position-main" style="margin: 0 16% 0 16%">
        <h1 class="title">Account</h1>
        <div class="acc-left">
            <br><p class="info">${currentUser.login}</p>
            <br><p class="info">First Name: ${currentUser.firstName}</p>
            <br><p class="info">Last Name: ${currentUser.lastName}</p>
            <br><a class = "choose" href="editProfile?id=${currentUser.id}">Edit</a>
        </div>
        <div class="acc-right">
            <br><p><a class="choose" href="myOrder">Мои туры</a></p>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <br><p><a class="choose" href="listUsers">Пользователи</a></p>
                <br><p><a class="choose" href="listOrders">Все заказы</a></p>
            </sec:authorize>
        </div>
    </div>
</div>

</body>
</html>
