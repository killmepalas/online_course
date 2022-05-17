<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Создание пользователя</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">
</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="../../../index.jsp"><img src="/resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="../course">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main class="edit">
        <form:form method="POST" modelAttribute="user">
            <h3 class="form-signin-heading">Создание аккаунта</h3>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" value="${user.username}"
                                placeholder="Логин" field="${username}"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="lastname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="lastname" class="form-control" value="${user.lastname}" placeholder="Фамилия"></form:input>
                    <form:errors path="lastname"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" value="${user.name}" placeholder="Имя"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="midname">
                <div class="form-group">
                    <form:input type="text" path="midname" class="form-control" value="${user.midname}" placeholder="Отчество"></form:input>
                    <form:errors path="midname"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="dateOfBirth">
                <div class="form-group">
                    <form:input type="date" path="dateOfBirth" class="form-control" value="${user.dateOfBirth}" placeholder="Дата рождения"></form:input>
                    <form:errors path="dateOfBirth"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="photolink">
                <div class="form-group">
                    <form:input type="text" path="photolink" class="form-control" value="${user.photolink}" placeholder="Фото"></form:input>
                    <form:errors path="photolink"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" value="${user.password}" placeholder="Пароль"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="confirmPassword">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="confirmPassword" class="form-control" value="${user.password}"
                                placeholder="Введите пароль ещё раз"></form:input>
                    <form:errors path="confirmPassword"></form:errors>
                </div>
            </spring:bind>

            <form method="post" action="/create">
                <button class="update" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Создать</button>
            </form>
        </form:form>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
