<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="label" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>Login</title>
</head>

<body>

<div class="container">
    <div class="wrapper">
        <c:url value="/login" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h1 class="info">Please sign in</h1>
            <input type="text" class="form-control" name="j_username" placeholder="Email address">
            <input type="password" class="form-control" name="j_password" placeholder="Password" r>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </form>
    </div>
</div>
</body>
</html>
