<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Упс...</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/resources/img/logo.png" width="400"/></a>
            <li><a href="${pageContext.request.contextPath}/profile">Личный кабинет</a></li>
            <li><a href="${pageContext.request.contextPath}/course/index/0">Каталог курсов</a></li>
            <li><a href="${pageContext.request.contextPath}/course/teach">Преподавание</a></li>
            <li><a href="${pageContext.request.contextPath}/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main>
        <br>
        <br/>
        <h3>Ой. Мы не знаем, что это было, но уже работаем над тем, чтобы такого больше не повторилось.</h3>
        <img height="300" width="400" src="${pageContext.request.contextPath}/resources/img/duck_start.png"/>
        <img height="300" width="400" src="${pageContext.request.contextPath}/resources/img/duck_end.png"/>
        <br/>
        <br/>
        <img id="error" src="${pageContext.request.contextPath}/resources/img/otchislenie.png"/>
        <br/>
        <br/>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
