<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Личный кабинет</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="../course">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="show">
        <section>
            <div class="show">
                <h2>${user.username}</h2>
                <h4>${user.lastname} ${user.name} ${user.midname}</h4>
                <h4>${user.dateOfBirth}</h4>
                <h4>Рейтинг:</h4>
                <h4>
                    <progress id="progressbar" value="${user.rating}" max="100"></progress>
                    <span class="progress-value">${user.rating}%</span>
                </h4>

            </div>

            <img src="${user.photolink}" alt="фотку поставь">

            <form method="post" action="/update/${user.id}">
                <button class="update" type="submit" value="update">Обновить данные профиля</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
