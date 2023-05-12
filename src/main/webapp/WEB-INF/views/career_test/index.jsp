<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Лучшие онлайн-курсы</title>
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
    <main id="course">
        <section class="help">
        <h1>Пройдите тест и найдите свою профессию мечты</h1>

        <h3>Всего ${size} вопросов и вы узнаете своё предназначение!</h3>
        <form method="get" action="${contextPath}/career_test/start/1">
            <button class="detailed" type="submit" value="Удалить">Пройти тест</button>
        </form>
        </section>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>