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
        <section>
            <div class="show">
                <h2>${test.name}</h2>
                <p id="description">${test.description}</p>
                <p>${test.start}</p>
                <p>${test.stop}</p>
            </div>
            <div>
                <form method="get" action="/test/update/${test.id}">
                    <input type="submit" value="Редактировать">
                </form>
            </div>
            <div>
                <form method="post" action="/test/delete/${test.id}/${test.course.id}?${_csrf.parameterName}=${_csrf.token}">
                    <input type="submit"   value="Удалить">
                </form>
            </div>
            <div>
                <form method="get" action="/question/${test.id}">
                    <input type="submit" value="Конструктор">
                </form>
            </div>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
