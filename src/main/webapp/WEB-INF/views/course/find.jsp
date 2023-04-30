<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Результаты поиска</title>
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
        <form action="${contextPath}/course/find" path="name">
            <input type="text" placeholder="Search" name="courses" class="search">
        </form>

        <c:forEach items="${course}" var="courselist">
        <c:choose>
        <c:when test="${course.indexOf(courselist) % 3==0}"><div class="row"><section class="col-3">
        <div>
            <h4>${courselist.name}</h4>
            <img src="${courselist.photolink}">
            <div id="management">
                <form method="get" action="${contextPath}/course/${courselist.id}">
                    <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                </form>
            </div>
            <c:if test="${status == 'admin'}">
                <form method="post" action="${contextPath}/course/delete/${courselist.id}">
                    <button class="detailed" type="submit" value="Подробнее">Удалить</button>
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
                    <form method="get" action="${contextPath}/course/${courselist.id}">
                        <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                    </form>
                </div>
                <c:if test="${status == 'admin'}">
                    <form method="post" action="${contextPath}/course/delete/${courselist.id}">
                        <button class="detailed" type="submit" value="Подробнее">Удалить</button>
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
                    <form method="get" action="${contextPath}/course/${courselist.id}">
                        <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                    </form>
                </div>
                <c:if test="${status == 'admin'}">
                    <form method="post" action="${contextPath}/course/delete/${courselist.id}">
                        <button class="detailed" type="submit" value="Подробнее">Удалить</button>
                    </form>
                </c:if>
            </div>
        </section></div>
        </c:otherwise>
        </c:choose>

        </c:forEach>
</div>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>
