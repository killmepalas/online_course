<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Конструктор курса</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="/course">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="course">
        <form action="/test/find/" path="name">
            <input type="text" placeholder="Search" name="courses" class="search">
        </form>
        <c:if test="${tests.isEmpty()}">
            <section>
                <h4>У курса пока нет тестов. Создайте свой первый тест прямо сейчас!</h4>
            </section>
        </c:if>

        <c:if test="${!tests.isEmpty()}">
        <c:forEach items="${tests}" var="test">
        <c:choose>
        <c:when test="${tests.indexOf(test) % 3==0}"><section id="left"></c:when>
        <c:when test="${tests.indexOf(test) % 3==1}"><section id="center"></c:when>
            <c:otherwise><section id="right"></c:otherwise>
                </c:choose>
                <div>
                    <h4>${test.name}</h4>
                    <h4>${test.description}</h4>
                    <h4>${test.start}</h4>
                    <h4>${test.stop}</h4>
                    <form method="get" action="/test/update/${test.id}">
                        <button class="detailed" type="submit" value="Редактировать тест">Редактировать тест</button>
                    </form>
                    <form method="get" action="/test/manage/${test.id}">
                        <button class="detailed" type="submit" value="Добавить вопросы">Добавить вопросы</button>
                    </form>
                    <form method="post" action="/test/delete/${test.id}">
                        <button class="detailed" type="submit" value="Удалить тест">Удалить тест</button>
                    </form>
                </div>
            </section>
            </c:forEach>
        </c:if>
        <section id="center">
            <form method="post" action="/test/create">
                <button class="detailed" type="submit" value="Создать тест">Создать тест</button>
            </form>
        </section>

    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
