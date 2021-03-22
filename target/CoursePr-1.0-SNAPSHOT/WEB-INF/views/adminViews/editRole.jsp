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
    <title>EditRole</title>
</head>
<div align="center">
    <h1 class="title">Edit Role</h1>
    <form:form action="saveUser" method="post" modelAttribute="user">
        <table>
            <form:hidden path="id"/>
            <form:hidden path="password" value="${user.password}"/>
            <form:hidden path="login" value="${user.login}"/>
            <form:hidden path="firstName" value="${user.firstName}"/>
            <form:hidden path="lastName" value="${user.lastName}"/>
            <tr>
                <td>Role:</td>
                <td><form:input path="role" value="${user.role}"/></td>
            </tr>
        </table>
        <tr>
            <td colspan="2" align="center"><button type="submit">Save</button></td>
        </tr>
    </form:form>
</div>
</body>
</html>
