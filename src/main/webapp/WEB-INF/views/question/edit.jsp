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
        <section>
            <form:form method="POST" modelAttribute="question" >
                <h2>Редактирование теста</h2>

                <spring:bind path="text">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="text" class="form-control" value="${question.text}" placeholder="Пока пусто"></form:input>
                        <form:errors path="text"></form:errors>
                    </div>
                </spring:bind>

                <form method="post" action="/question/update/${question.id}">
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

