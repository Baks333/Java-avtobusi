<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>List Concerts</title>
</head>
<body>
<div class="container">
    <h1 class="title">Concert List</h1>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2><a href="newConcert">New Concert</a></h2>
    </sec:authorize>
    <div class="col-left" style="text-align: center">
        <h2>Концерт:</h2>
        <form:form action="ListConcertsByTitle" method="get">
            <select class="opt" name="title">
                <c:forEach var="Title" items="${Titles}" varStatus="status">
                    <option>${Title}</option>
                </c:forEach>
            </select>
            <br><button type="submit" action="ListConcertsByTitle?title=${title}">Поиск</button>
        </form:form>
            <h2>Место проведения:</h2>
            <form:form action="ListConcertsByLocation" method="get">
            <select class="opt" name="location">
                <c:forEach var="Location" items="${Locations}" varStatus="status">
                    <option>${Location}</option>
                </c:forEach>
            </select>
                <br><button type="submit" action="ListConcertsByLocation?location=${location}">Поиск</button>
        </form:form>
        <h2>Исполнитель:</h2>
        <form:form action="ListConcertsByBand" method="get">
            <select class="opt" name="band">
                <c:forEach var="Band" items="${Bands}" varStatus="status">
                    <option>${Band}</option>
                </c:forEach>
            </select>
            <br><button type="submit" action="ListConcertsByBand?band=${band}">Поиск</button>
        </form:form>
    </div>
        <div class="position-main" style="margin-left: 16%">
        <c:forEach var="concerts" items="${listConcerts}" varStatus="status">
            <div class="position">
                <form:form action="chooseConcert" method="post">
                    <input type="hidden" name="concertID" value="${concerts.id}">
                    <h2>${concerts.title}</h2>
                    <div class="position-img"><img class="image" src="${concerts.poster}"></div>
                    <div class="bot">
                        <p class="info">${concerts.band}</p>
                        <p class="info">${concerts.location}</p>
                        <p class="info">${concerts.date}</p>
                        <p class="info">${concerts.price}</p>
                        <sec:authorize access="isAuthenticated()">
                        <button type="submit">Выбрать</button>
                        </sec:authorize>
                    </div>
                </form:form>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <br><a class = "choose" href="editConcert?id=${concerts.id}">Edit</a>
                    <a class = "choose" href="deleteConcert?id=${concerts.id}">Delete</a>
                </sec:authorize>
            </div>
        </c:forEach>
        </div>
    </div>
</body>
</html>
