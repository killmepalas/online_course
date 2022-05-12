<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>О курсе</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <img src="../../../resources/img/logo.png" width="400"/>
            <li><a href="/first/hello">Личный кабинет</a></li>
            <li><a href="index.jsp">Каталог курсов</a></li>
            <li><a href="../teaching.jsp">Преподавание</a></li>
            <li><a href="../training.jsp">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="show">
        <div class="show">
            <h2>${course.name}</h2>
            <h4>Автор - ${teacher.username}</h4>
        </div>
        <img src="${course.photolink}"/>
        <div class="show">
            <p id="description">${course.description}</p>
        </div>

        <c:if test="${course.price != 0}">
            <form method="post" action="/course/buy/${course.id}">
                <input type="submit" value=${course.price}>
            </form>
        </c:if>
        <c:if test="${course.price==0}">
            <form method="post" action="/user/addCourse/${course.id}">
                <input type="submit" value="Бесплатно">
            </form>
        </c:if>

    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
