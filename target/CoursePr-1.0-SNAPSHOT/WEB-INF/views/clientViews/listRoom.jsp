<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <%@include file="../layout.jsp"%>
    <title>List Room</title>
</head>
<div class="container">
    <!--
    <div class="col-left">
        <h2>Вместимость: </h2>
        <form:form action="">
        <select class="opt">
            <c:forEach var="capacity" items="${capacities}" varStatus="status">
            <option>${capacity}</option>
            </c:forEach>
        </select>
        <button type="submit">Поиск</button>
        </form:form>
    </div>
    -->
    <div class="col-right" style="width: 15%">
        <h2>Отзывы</h2>

        <c:forEach var="feedback" items="${Feedback}" varStatus="status">
            <p class="info">${feedback.user.firstName}</p>
            <p class="info">Рейтинг: ${feedback.rating}/5</p>
            <p class="info">Дата: ${feedback.date}</p>
            <p class="info">${feedback.text}</p>
            <br><br>
        </c:forEach>
        <form:form action="saveFeedback" method="post" modelAttribute="newFeedback">
            <form:hidden path="id"/>
            <input type="hidden" name="hotelID" value="${hotelID}">
            <tr>
                <td>Rating:</td>
                <td><form:input path="rating" /></td>
            </tr>
            <tr>
                <td>Text:</td>
                <td><form:input path="text" /></td>
            </tr>
            <button type="submit">Отправить отзыв</button>
        </form:form>
    </div>
    <div class="position-main" style="margin-left: 16%; margin-right: 16%;">
        <h1 class="title">List Room</h1>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h2><a href="newRoom?hotelID=${hotelID}">New Room</a> </h2>
        </sec:authorize>
        <p class="info">${hotel.title}</p>
        <p class="info">Star: ${hotel.star}</p>
        <p class="info">Рейтинг: ${hotel.rating}/5</p>
        <p class="info">${hotel.address}</p>
        <p class="info">${hotel.phoneNumber}</p>
        <p class="info">${hotel.details}</p>
        <br><br>
            <c:forEach var="room" items="${listRoom}" varStatus="status">
              <form:form action="createOrder" method="post">
                <div class="position-second">
                    <input type="hidden" name="concertID" value="${concertID}">
                    <input type="hidden" name="tourID" value="${tourID}">
                    <input type="hidden" name="hotelID" value="${hotel.id}">
                    <input type="hidden" name="roomID" value="${room.id}">
                    <p class="info">${room.price}</p>
                    <p class="info">Вместимость: ${room.capacity}</p>
                    <div class="position-img-sec"><img class="image-sec" src="${room.roomImg}"></div>
                    <p class="info">${room.facilities}</p>
                    <button type="submit">Выбрать</button>
                </form:form>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <br><a class = "choose" href="editRoom?id=${room.id}&hotelID=${hotelID}">Edit</a>
                    <a class = "choose" href="deleteRoom?id=${room.id}">Delete</a>
                </sec:authorize>
                </div>
            </c:forEach>
    </div>
</div>
</body>
</html>