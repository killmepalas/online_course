<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование ответов</title>
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
            <form:form method="POST" modelAttribute="answer" >
                <h3>Редактирование ответа на вопрос</h3>

                <spring:bind path="text">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="text" class="form-control" value="${answer.text}" placeholder="Пока пусто"></form:input>
                        <form:errors path="text"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="is_right">
                        <label>Правильный ответ</label>
                        <form:checkbox path="is_right" class="form-control" value="${answer.is_right}" placeholder="Пока пусто"/>
                </spring:bind>
<div>
    <form method="post" action="/answer/update/${answer.id}">
        <button class="teach" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить</button>
    </form>
</div>
            </form:form>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>