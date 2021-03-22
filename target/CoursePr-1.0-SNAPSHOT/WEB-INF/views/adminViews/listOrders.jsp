<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>ListOrders</title>
</head>
<body>
<div class="container" style="margin: 5% 10%">
    <div class="product-main" style="margin: 0 16% 0 16%">
        <h1 class="title">Orders List</h1>
        <table border="1" align="center">
            <th>Concert</th>
            <th>Tour</th>
            <th>User</th>
            <th>Hotel</th>
            <th>Cost</th>
            <th>Status</th>
            <th></th>

            <c:forEach var="order" items="${listOrders}" varStatus="status">
                <tr>
                    <td>${order.concert.title}</td>
                    <td>${order.tour.date}</td>
                    <td>${order.user.login}</td>
                    <td>${order.room.hotel.title}</td>
                    <td>${order.cost}</td>
                    <td>${order.status}</td>
                    <td>
                        <a class = "choose"  href="deleteOrder?id=${order.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
