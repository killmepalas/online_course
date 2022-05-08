<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Каталог курсов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <img src="resources/img/logo.png" width="400"/>
            <li><a href="/first/hello">Личный кабинет</a></li>
            <li><a href="index.jsp">Каталог курсов</a></li>
            <li><a href="WEB-INF/jsp/teaching.jsp">Преподавание</a></li>
            <li><a href="WEB-INF/jsp/training.jsp">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="mine">
        <form>
            <input type="search" placeholder="Search" class="search">
        </form>

        <section id="left">
            <div>
                <h4>Веб-программирование</h4>
                <img src="resources/img/c1.jpeg">
                <article>
                    <p id="free">Бесплатно</p>
                </article>
            </div>
        </section>
        <section id="center">
            <h4>C# для начинающих</h4>
            <img src="resources/img/c2.png">
            <article>
                <p id="money">1450₽</p>
            </article>
        </section>
        <section id="right">
            <h4>Основы Java</h4>
            <img src="resources/img/c3.jpg">
            <article>
                <p id="free">Бесплатно</p>
            </article>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
