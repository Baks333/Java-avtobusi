<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="label" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>Registration</title>
</head>

<body>

<div class="container">
    <div class="wrapper"

    <form:form  method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="title">Create your account</h2>
        <spring:bind path="login">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="login" class="form-control" placeholder="login"
                            autofocus="true"></form:input>
                <form:errors path="login"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group">
                <form:input type="text" path="firstName" class="form-control" placeholder="firstName"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group">
                <form:input type="text" path="lastName" class="form-control" placeholder="lastName"></form:input>
            </div>
        </spring:bind>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
</div>
</div>
</body>
</html>
