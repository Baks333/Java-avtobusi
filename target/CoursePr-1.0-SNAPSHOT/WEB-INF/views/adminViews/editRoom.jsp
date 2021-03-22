<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>Edit Room</title>
</head>
<body>
<div align="center">
    <h1>Edit Room</h1>
    <form:form action="saveRoom" method="post" modelAttribute="room">
        <table>
            <form:hidden path="id"/>
            <input type="hidden" name="hotelID" value="${hotelID}">
            <tr>
                <td>Capacity:</td>
                <td><form:input path="capacity"  value="${room.capacity}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price" value="${room.price}"/></td>
            </tr>
            <tr>
                <td>RoomImg:</td>
                <td><form:input path="roomImg" value="${room.roomImg}"/></td>
            </tr>
            <tr>
                <td>Facilities:</td>
                <td><form:input path="facilities"  value="${room.facilities}"/></td>
            </tr>
        </table>
        <button type="submit">Save</button>
    </form:form>
</div>
</body>
</html>