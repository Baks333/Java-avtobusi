<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>List Hotel</title>
</head>
<body>
<div class="container">
    <h1 class="title">Hotel List</h1>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2><a href="newHotel?tourID=${tourID}">New Hotel</a> </h2>
    </sec:authorize>
    <!--
    <div class="col-left">
        <h2>Star:</h2>
        <form:form action="">
        <select class="opt" name="star">
            <c:forEach var="star" items="${stars}" varStatus="status">
            <option>${star}</option>
            </c:forEach>
        </select>
        <br><button type="submit" action="">Поиск</button>
        </form:form>
        <h2>Названия:</h2>
        <form:form action="">
            <select class="opt" name="title">
                <c:forEach var="title" items="${titles}" varStatus="status">
                    <option>${title}</option>
                </c:forEach>
            </select>
            <br><button type="submit" action="">Поиск</button>
        </form:form>
    </div>
    -->
    <div class="position-main" style="margin-left: 16%">
        <p class="info">${tour.concert.title}</p>
        <p class="info">${tour.date}</p>
        <p class="info">${tour.details}</p>
        <form:form action="chooseHotel">
        <c:forEach var="hotel" items="${listHotel}" varStatus="status">
        <div class="position">
                <input type="hidden" name="concertID" value="${concertID}">
                <input type="hidden" name="tourID" value="${tourID}">
                <input type="hidden" name="hotelID" value="${hotel.id}">
                <h2>${hotel.title}</h2>
                <p class="info">Star: ${hotel.star}</p>
                <p class="info">Рейтинг: ${hotel.rating}</p>
                <div class="position-img"><img class="image" src="${hotel.hotelImg}"></div>
                <p class="info">Адрес: ${hotel.address}</p>
                <button type="submit">Выбрать</button>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <br><a class = "choose" href="editHotel?id=${hotel.id}&tourID=${tourID}">Edit</a>
                <a class = "choose" href="deleteHotel?id=${hotel.id}">Delete</a>
            </sec:authorize>
        </div>
        </c:forEach>
        </form:form>
    </div>
</div>
</body>
</html>