<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>Edit Concert</title>
</head>
<body>
<div align="center">
    <h1>Edit Concert</h1>
    <form:form action="saveConcert" method="post" modelAttribute="concert">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" value="${concert.title}"/></td>
            </tr>
            <tr>
                <td>Band:</td>
                <td><form:input path="band" value="${concert.band}"/></td>
            </tr>
            <tr>
                <td>Poster:</td>
                <td><form:input path="poster" value="${concert.poster}"/></td>
            </tr>
            <tr>
                <td>Location:</td>
                <td><form:input path="location" value="${concert.location}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price" value="${concert.price}"/></td>
            </tr>
            <tr>
                <td>Details:</td>
                <td><form:input path="details" value="${concert.details}"/></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><form:input path="date" value="${concert.date}"/></td>
            </tr>
        </table>
        <button type="submit">Save</button>
    </form:form>
</div>
</body>
</html>