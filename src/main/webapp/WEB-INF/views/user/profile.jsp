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
    <main id="show">
        <section class="help">
            <div class="show">
                <h2>${user.username}</h2>
                <h4>${user.lastname} ${user.name} ${user.midname}</h4>
                <h4>${user.email}</h4>
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
            <form method="get" action="${contextPath}/update/${user.id}">
                <button class="logout" type="submit" value="update">Обновить данные профиля</button>
            </form>

            <form method="get" action="${contextPath}/updateUsername/${user.id}">
                <button class="logout" type="submit" value="update">Сменить логин</button>
            </form>

            <form method="get" action="${contextPath}/updatePassword/${user.id}">
                <button class="logout" type="submit" value="update">Сменить пароль</button>
            </form>

            <c:if test="${user.id != 13}">
            <form method="post" action="${contextPath}/delete/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                <button class="logout" type="submit" onclick="return confirm('Вы действительно хотите удалить профиль?')" value="Удалить">Удалить профиль</button>
            </form>
            </c:if>

            <c:if test="${status == 'admin'}">
                <form method="get" action="${pageContext.request.contextPath}/index">
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
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>
