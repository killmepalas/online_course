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
    <main id="mine">
        <h4>${course.name}</h4>
        <h4>Автор - ${teacher.lastname} ${teacher.midname} ${teacher.name}</h4>
        <img src="${course.photolink}"/>
        <c:if test="${course.price!=0}">
            <p id="money">Цена: ${course.price} ₽</p>
        </c:if>
        <c:if test="${course.price==0}">
           <p id="free">Бесплатно</p>
        </c:if>
        <h5>${course.description}</h5>

        <form method="post" action="@{/course/buy/{id}(id=${course.id})}">
            <input type="submit" value="Buy"/>
        </form>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
