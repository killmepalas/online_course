<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Преподавание</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">
</head>

<body>
<nav>
    <ul>
        <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
        <li><a href="/profile">Личный кабинет</a></li>
        <li><a href="../course">Каталог курсов</a></li>
        <li><a href="/course/teach">Преподавание</a></li>
        <li><a href="/course/my_courses">Моё обучение</a></li>
    </ul>
</nav>
<div id="container">
    <main id="main">
        <section class="help">
            <h3>Конструктор курсов</h3>
            <p>Вы уверены в себе и своих знаниях?<br>Тогда можете попробовать себя в роли не просто обучаемого, а преподавателя.</p>
            <br>
            <c:choose>
                <c:when test="${auth == 'not'}">
                    <h4>Преподавателем может стать только зарегистрированный пользователь.</h4>
                    <h4><a href="/login">Пожалуйста, войдите</a></h4>
                </c:when>
                <c:otherwise>
                    <form method="post" action="/course/create">
                        <button class="teach" type="submit" value="create">Создать курс</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </section>

    </main>
    <footer>
        <p>Мы ничего не успеваем и не понимаем.</p>
    </footer>
</div>
</body>

</html>