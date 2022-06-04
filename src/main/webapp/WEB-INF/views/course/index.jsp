<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Каталог курсов</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/resources/img/logo.png" width="400"/></a>
            <li><a href="${pageContext.request.contextPath}/profile">Личный кабинет</a></li>
            <li><a href="${pageContext.request.contextPath}/course/index/0">Каталог курсов</a></li>
            <li><a href="${pageContext.request.contextPath}/course/teach">Преподавание</a></li>
            <li><a href="${pageContext.request.contextPath}/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div class="container">
    <main id="course">
        <form action="${pageContext.request.contextPath}/course/find/" path="name">
            <input type="text" placeholder="Search" name="courses" class="search">
        </form>
        <c:forEach items="${course}" var="courselist">
        <c:choose>
        <c:when test="${course.indexOf(courselist) % 3==0}"><div class="row"><section class="col-3">
        <div>
            <h4>${courselist.name}</h4>
            <img src="${courselist.photolink}">
            <div id="management">
                <form method="get" action="/course/${courselist.id}">
                    <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                </form>
            </div>
            <c:if test="${status == 'admin'}">
                <form method="post" action="/course/delete/${courselist.id}">
                    <button class="detailed" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Удалить</button>
                </form>
            </c:if>
        </div>
    </section>
        </c:when>
        <c:when test="${course.indexOf(courselist) % 3==1}"><section class="col-3">
            <div>
                <h4>${courselist.name}</h4>
                <img src="${courselist.photolink}">
                <div id="management">
                    <form method="get" action="/course/${courselist.id}">
                        <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                    </form>
                </div>
                <c:if test="${status == 'admin'}">
                    <form method="post" action="/course/delete/${courselist.id}">
                        <button class="detailed" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Удалить</button>
                    </form>
                </c:if>
            </div>
        </section>
            </c:when>
            <c:otherwise><section class="col-3">
            <div>
                <h4>${courselist.name}</h4>
                <img src="${courselist.photolink}">
                <div id="management">
                    <form method="get" action="/course/${courselist.id}">
                        <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                    </form>
                </div>
                <c:if test="${status == 'admin'}">
                    <form method="post" action="/course/delete/${courselist.id}">
                        <button class="detailed" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Удалить</button>
                    </form>
                </c:if>
            </div>
        </section></div>
                </c:otherwise>
                </c:choose>

            </c:forEach>
        </div>
            <div class="pages">
                <section class="page">
                    <c:if test="${num>0}">
                        <form method="get" action="/course/index/${num-1}">
                            <button class="previous" type="submit" value="Подробнее">${num}</button>
                        </form>
                    </c:if>
                </section>
                <section class="page">
                    <span>Страница ${num+1}.</span>
                </section>
                <section class="page">
                    <c:if test="${end != 'true'}">
                        <form method="get" action="/course/index/${num+1}">
                            <button class="next" type="submit" value="Подробнее">${num +2}</button>
                        </form>
                    </c:if>
                </section>
            </div>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>