<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Содержание курса</title>
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
<div class="container">
    <main id="course">
        <c:if test="${status == 'teacher'}">
            <section class="help">
                <c:if test="${topics.isEmpty()}">
                    <h4>У курса пока нет тем (уроков). Создайте свою первую тему прямо сейчас!</h4>
                </c:if>
                <div>
                    <form method="get" action="/topic/create/${id_course}">
                        <button class="detailed" type="submit">Создать тему</button>
                    </form>
                </div>
                <div>
                    <form method="get" action="/course/${id_course}">
                        <button class="detailed" type="submit">Вернуться к курсу</button>
                    </form>
                </div>
                <c:if test="${!topics.isEmpty()}">
                    <c:forEach items="${topics}" var="topic">
                        <c:if test="${topics.indexOf(topic) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h4>Название: ${topic.name}</h4>
                                <h4>Описание: ${topic.description}</h4>
                                <form method="get" action="/topic/show/${topic.id}">
                                    <button class="detailed" type="submit" value="Управление">Управление</button>
                                </form>
                            </div>
                        </section>
                        <c:if test="${topics.indexOf(topic) % 3==2}"></div></c:if>
                    </c:forEach>
                </c:if>
            </section>
        </c:if>

        <jsp:useBean id="now" class="java.util.Date" scope="page"/>

        <c:if test="${status == 'student'}">
            <c:if test="${topics.isEmpty()}">
                <section class="help">
                    <h4>У курса пока нет тем (уроков). Возвращайтесь позже!</h4>
                </section>
            </c:if>
            <c:if test="${!topics.isEmpty()}">
                <c:forEach items="${topics}" var="topic">
                    <c:if test="${topics.indexOf(topic) % 3==0}"><div class="row"></c:if>
                    <section class="col-3">
                        <div>
                            <h4>Название: ${topic.name}</h4>
                            <h4>Описание: ${topic.description}</h4>
                            <c:forEach items="${grades}" var="grade">
                                <c:if test="${grade.topic.id == topic.id}">
                                    <p>
                                        <progress id="progressbar" value="${grade.grade}" max="100"></progress>
                                        <span class="progress-value">${grade.grade}%</span>
                                    </p>
                                </c:if>
                            </c:forEach>
                        </div>
                    </section>
                    <c:if test="${topics.indexOf(topic) % 3==2}"></div></c:if>
                </c:forEach>
            </c:if>
        </c:if>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>
