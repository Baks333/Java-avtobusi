<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>NewTour</title>
</head>
<body>
<div align="center">
    <h1 class="title">New Tour</h1>
    <form:form action="saveTour" method="post" modelAttribute="newTour">
        <table>
            <form:hidden path="id"/>
            <input type="hidden" name="concertID" value="${concertID}">
            <tr>
                <td>License Plate:</td>
                <td><form:input path="licensePlate" /></td>
            </tr>
            <tr>
                <td>Model:</td>
                <td><form:input path="model" /></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input path="type" /></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><form:input path="date" /></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price" /></td>
            </tr>
            <tr>
                <td>Details:</td>
                <td><form:input path="details"/></td>
            </tr>
        </table>
        <button type="submit">Save</button>
    </form:form>
</div>
</body>
</html>