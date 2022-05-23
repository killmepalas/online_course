<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование курса</title>
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
    <main>
        <section class="help">
            <form:form method="POST" modelAttribute="course" >
                <h1 class="form-signin-heading">Редактирование курса</h1>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control" value="${course.name}"
                                    placeholder="Название" field="${name}"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="description">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="description" class="form-control" value="${course.description}" placeholder="Описание"></form:input>
                        <form:errors path="description"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="price">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="price" class="form-control" value="${course.price}" placeholder="Стоимость"></form:input>
                        <form:errors path="price"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="photolink">
                    <div class="form-group">
                        <form:input type="text" path="photolink" class="form-control" value="${course.photolink}" placeholder="Ссылка на фото"></form:input>
                        <form:errors path="photolink"></form:errors>
                    </div>
                </spring:bind>
                <br>
                <div class="form-group">
                    <form method="post" action="/course/update/${course.id}">
                        <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить</button>
                    </form>
                </div>
            </form:form>
            <form method="get" action="/course/${course.id}">
                <button class="formcource" type="submit" >Вернуться к курсу</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>

