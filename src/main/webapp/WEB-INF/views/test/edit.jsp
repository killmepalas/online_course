<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование теста</title>
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
    <main>
        <section class="help">
            <form:form method="POST" modelAttribute="test" >
                <h2 class="form-signin-heading">Редактирование теста</h2>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control" value="${test.name}"
                                    placeholder="Пока пусто" field="${name}"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="description">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="description" class="form-control" value="${test.description}" placeholder="Пока пусто"></form:input>
                        <form:errors path="description"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="start">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="date" path="start" class="form-control" value=" ${test.start}>" placeholder="Пока пусто"></form:input>
                        <form:errors path="start"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="stop">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="date" path="stop" class="form-control" value="${test.stop}" placeholder="Пока пусто"></form:input>
                    <form:errors path="stop"></form:errors>
                </div>
                </spring:bind>
                <form method="post" action="/test/update/${test.id}">
                    <button class="update" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить</button>
                </form>
            </form:form>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
