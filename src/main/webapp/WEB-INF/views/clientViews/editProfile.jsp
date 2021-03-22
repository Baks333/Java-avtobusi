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
    <title>EditProfile</title>
</head>
<div align="center">
    <h1 class="title">Edit Profile</h1>
    <form:form action="saveProfile" method="post" modelAttribute="user">
        <table>
            <form:hidden path="id"/>
            <form:hidden path="password" value="${user.password}"/>
            <form:hidden path="login" value="${user.login}"/>
            <form:hidden path="role" value="${user.role}"/>
            <tr>
                <td>firstName:</td>
                <td><form:input path="firstName" value="${user.firstName}"/></td>
            </tr>
            <tr>
                <td>lastName:</td>
                <td><form:input path="lastName" value="${user.lastName}"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><button type="submit">Save</button></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
