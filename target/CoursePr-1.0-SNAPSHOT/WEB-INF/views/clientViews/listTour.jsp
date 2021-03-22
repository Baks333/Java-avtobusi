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
    <h1 class="title">Tour List</h1>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h2><a href="newTour?concertID=${concertID}">New Tour</a></h2>
        </sec:authorize>
            <div class="position-main" style="margin: 0 15%">
                <p class="info">${concert.title}</p>
                <p class="info">${concert.location}</p>
                <p class="info">${concert.details}</p>
                <c:forEach var="tour" items="${listTour}" varStatus="status">
                <div class="position">
                    <form:form action="chooseTour" method="post">
                        <input type="hidden" name="concertID" value="${concertID}">
                        <input type="hidden" name="tourID" value="${tour.id}">
                        <p class="info">${tour.model} ${tour.type}</p>
                        <p class="info">${tour.date}</p>
                        <p class="info">${tour.licensePlate}</p>
                        <p class="info">${tour.price}</p>
                        <button type="submit">Выбрать</button>
                    </form:form>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <br><a class = "choose" href="editTour?id=${tour.id}&concertID=${concertID}">Edit</a>
                        <a class = "choose" href="deleteTour?id=${tour.id}">Delete</a>
                    </sec:authorize>
                </div>
                </c:forEach>
            </div>
    </div>
</body>
</html>
