<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование профиля</title>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
    <main class="edit">
            <form:form method="POST" modelAttribute="user" action="${contextPath}/update/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                <h3 class="form-signin-heading">Редактирование профиля</h3>

                <spring:bind path="email">
                    <div class="form-group">
                        <form:input type="text" path="email" class="form-control" value="${user.email}" placeholder="Email"></form:input>
                        <form:errors path="email"></form:errors>
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
                        <form:input type="date" path="dateOfBirth" class="form-control" value="${user.dateOfBirth}" placeholder="Пока пусто"></form:input>
                        <form:errors path="dateOfBirth"></form:errors>
                    </div>
                    <p>${user.dateOfBirth}</p>
                </spring:bind>

                <spring:bind path="photolink">
                    <div id="edit">
                        <img src="${user.photolink}">
                    </div>

                    <div class="form-group">
                        <form:input type="text" path="photolink" class="form-control" value="${user.photolink}" placeholder="Ну пока пусть будет типа ссыль"></form:input>
                        <form:errors path="photolink"></form:errors>
                    </div>
                </spring:bind>



                <form method="post" action="${contextPath}/edit/${user.id}">
                    <button class="update" type="submit" value="edit">Обновить</button>
                </form>
            </form:form>
    </main>
        <a class="formcource" href="${contextPath}/profile">Вернуться к профилю</a>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>
