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
<table border="1">
    <th>№</th>
    <th>Concert</th>
    <th>User</th>
    <th>Tour</th>
    <th>Hotel</th>
    <th>Cost</th>
    <th>Status</th>
    <th></th>
    <form:form action="payment" method="post">
    <c:forEach var="order" items="${listOrder}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${order.concert.title}</td>
            <td><sec:authentication property="principal.username" /></td>
            <td>${order.tour.date}</td>
            <td>${order.room.hotel.title}</td>
            <td>${order.cost}</td>
            <td>${order.status}</td>
            <input type="hidden" name="orderID" value="${order.id}">
            <td>
                <c:if test = "${order.status == 'Ожидает оплаты'}">
                    <div class="bot">
                        <button type="submit">Оплатить</button>
                    </div>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </form:form>
</table>

</body>
</html>
