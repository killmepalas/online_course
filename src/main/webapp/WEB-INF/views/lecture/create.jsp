<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Создание лекции</title>
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
    <main>
        <section class="help">
            <form:form method="POST" modelAttribute="lecture" >
                <h1 class="form-signin-heading">Создание лекции</h1>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control" value="${lecture.name}"
                                    placeholder="Название" field="${name}"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="description">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="description" class="form-control" value="${lecture.description}" placeholder="Описание"></form:input>
                        <form:errors path="description"></form:errors>
                    </div>
                </spring:bind>

                <button onclick="beginParagraph()" type="button" class="jsBut">Начало абзаца</button>
                <button onclick="endParagraph()"  type="button" class="jsBut">Конец абзаца</button>

                <spring:bind path="text">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:textarea type="text" id="text" path="text" class="description" value="${lecture.text}" placeholder="Текст лекции"></form:textarea>
                        <form:errors path="text"></form:errors>
                    </div>
                </spring:bind>

                <form method="post" action="${contextPath}/lecture/create/${topic}">
                    <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Создать</button>
                </form>
            </form:form>
            <form method="get" action="${contextPath}/topic/show/${topic}">
                <button class="formcource" type="submit">Назад</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

<script src="${pageContext.request.contextPath}/resources/js/lecture.js"></script>
</html>
