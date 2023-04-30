<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Преподавание</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>

<header id="black">
    <nav>
        <ul>
            <a href="${contextPath}/index.jsp"><img src="${contextPath}/resources/img/logo.png" width="400"/></a>
            <li><a href="${contextPath}/profile">Личный кабинет</a></li>
            <li><a href="${contextPath}/course/category/index">Каталог курсов</a></li>
            <li><a href="${contextPath}/course/teach">Преподавание</a></li>
            <li><a href="${contextPath}/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>

<div id="container">
    <main id="main">
        <section class="help">
            <h3>Конструктор курсов</h3>
            <p>Вы уверены в себе и своих знаниях?<br>Тогда можете попробовать себя в роли не просто обучаемого, а преподавателя.</p>
            <br>
            <c:choose>
                <c:when test="${auth == 'not'}">
                    <h4>Преподавателем может стать только зарегистрированный пользователь.</h4>
                    <h4><a href="${contextPath}/login">Пожалуйста, войдите</a></h4>
                </c:when>
                <c:otherwise>
                    <form method="get" action="${contextPath}/course/create">
                        <button class="teach" type="submit" value="create">Создать курс</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </section>

    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>