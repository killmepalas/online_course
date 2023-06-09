<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Конструктор курса</title>
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
    <main id="course">
        <section class="help">
                <h4>Настройка прохождения теста</h4>
                <form:form method="POST" modelAttribute="test" >
                    <br>
                    <spring:bind path="mix">
                        <label>Перемешивать вопросы и ответы</label>
                        <form:checkbox id="check" path="mix" class="form-control" onchange="changeCount()" name="mix"/>
                    </spring:bind>

                    <spring:bind path="count">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <div id="count">
                                <form:label path="count">Сколько вопросов должно быть в тесте?</form:label>
                                <form:input type="text" path="count" class="form-control" value="${test.count}" placeholder="10"></form:input>
                                <br>
                                <form:errors cssClass="red" path="count"></form:errors>
                            </div>
                        </div>
                    </spring:bind>

                    <form method="post" action="${contextPath}/test/edit/${test.id}">
                        <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Сохранить</button>
                    </form>
                </form:form>
            <form method="get" action="${contextPath}/question/${test.id}">
                <button class="teach" type="submit" >Назад</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

<script src="${pageContext.request.contextPath}/resources/js/test.js"></script>

</html>
