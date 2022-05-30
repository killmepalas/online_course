<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
            <a href="../../../index.jsp"><img src="/resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="../course/index/0">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="show">
        <section class="help">
            <div class="show">
                <h2>${user.username}</h2>
                <h4>${user.lastname} ${user.name} ${user.midname}</h4>
                <h4>${user.dateOfBirth}</h4>
                <c:if test="${rating != null}">
                <h4>Рейтинг:</h4>
                <h4>
                    <progress id="progressbar" value="${rating}" max="100"></progress>
                    <span class="progress-value">${rating}%</span>
                </h4>
                </c:if>
            </div>

            <img src="${user.photolink}" alt="фотку поставь">
        <div class="profile">
            <form method="get" action="/update/${user.id}">
                <button class="logout" type="submit" value="update">Обновить данные профиля</button>
            </form>

            <form method="get" action="/updateUsername/${user.id}">
                <button class="logout" type="submit" value="update">Сменить логин</button>
            </form>

            <form method="get" action="/updatePassword/${user.id}">
                <button class="logout" type="submit" value="update">Сменить пароль</button>
            </form>

            <form method="post" action="/delete/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                <button class="logout" type="submit" onclick="return confirm('Вы действительно хотите удалить профиль?')" value="Удалить">Удалить профиль</button>
            </form>

            <c:if test="${status == 'admin'}">
                <form method="get" action="/index">
                    <button class="logout" type="submit" value="index">Управление пользователями</button>
                </form>

            </c:if>

            <form id="logoutForm" method="post" action="${contextPath}/logout">
                <button class="logout" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Выйти</button>
            </form>
        </div>

        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>
