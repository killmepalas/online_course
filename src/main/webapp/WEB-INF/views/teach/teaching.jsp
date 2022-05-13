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

<body id="container">
<nav>
    <ul>
        <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
        <li><a href="/profile">Личный кабинет</a></li>
        <li><a href="../course">Каталог курсов</a></li>
        <li><a href="/course/teach">Преподавание</a></li>
        <li><a href="/course/my_courses">Моё обучение</a></li>
    </ul>
</nav>
<div >
    <main id="teaching">
        <h3>Конструктор курсов</h3>
        <p>Вы уверены в себе и своих знаниях?<br>Тогда можете попробовать себя в роли не просто обучаемого, а преподавателя.</p>
        <br>
        <form method="post" action="/course/create">
            <button type="submit" value="create">Создать курс</button>
        </form>
    </main>
    <footer>
        <p>Мы ничего не успеваем и не понимаем.</p>
    </footer>
</div>
</body>

</html>