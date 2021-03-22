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
    <title>ListUsers</title>
</head>
<body>
<div class="container" style="margin: 5% 10%">
    <div class="col-left">
        <c:forEach var="role" items="${usersRoles}" varStatus="status">
            <h2><a class="left-panel" href="listUsersByRole?role=${role}">${role}</a></h2>
        </c:forEach>
    </div>
    <div class="product-main" style="margin: 0 16% 0 16%">
        <h1 class="title">User List</h1>
        <table border="1" align="center">
            <th>Login</th>
            <th>Role</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th></th>

            <c:forEach var="user" items="${listUsers}" varStatus="status">
                <tr>
                    <td>${user.login}</td>
                    <td>${user.role}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <a class = "choose" href="editRole?id=${user.id}">Edit</a>
                        <a class = "choose"  href="deleteUser?id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
