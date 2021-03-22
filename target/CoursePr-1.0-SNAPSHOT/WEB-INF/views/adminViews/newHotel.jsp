<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>New Hotel</title>
</head>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div align="center">
    <h1 class="title">New Hotel</h1>
    <form:form action="saveHotel" method="post" modelAttribute="newHotel">
        <table>
            <form:hidden path="id"/>
            <input type="hidden" name="tourID" value="${tourID}">
            <tr>
                <td>Title:</td>
                <td><form:input path="title" value="${newHotel.title}"/></td>
            </tr>
            <tr>
                <td>Star:</td>
                <td><form:input path="star" value="${newHotel.star}"/></td>
            </tr>
            <tr>
                <td>Rating:</td>
                <td><form:input path="rating" value="${newHotel.rating}"/></td>
            </tr>
            <tr>
                <td>Hotel Image:</td>
                <td><form:input path="hotelImg" value="${newHotel.hotelImg}"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" value="${newHotel.address}"/></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><form:input path="phoneNumber" value="${newHotel.phoneNumber}"/></td>
            </tr>
            <tr>
                <td>Details:</td>
                <td><form:input path="details" value="${newHotel.details}"/></td>
            </tr>
        </table>
        <button type="submit">Save</button>
    </form:form>
</div>
</body>
</html>