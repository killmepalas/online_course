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
    <title>Моё обучение</title>
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
        <section class="help">
            <div class="navbar">
                <a id="1" onclick="changeBar(0)">В процессе ${active.size()}</a>
                <a id="2" onclick="changeBar(1)">Завершённые ${over.size()}</a>
                <a id="3" onclick="changeBar(2)">Не начатые ${not_begin.size()}</a>
            </div>
            <c:if test="${is_empty}">
                <section class="help">
                    <h4>У вас пока нет курсов. <a href="${contextPath}/course/category/index">Начните обучение прямо
                        сейчас</a></h4>
                </section>
            </c:if>
            <c:if test="${!is_empty}">
                <div id="active">
                    <c:forEach items="${active}" var="course">
                        <c:if test="${active.indexOf(course) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h4>${course.name}</h4>
                                <img src="${course.photolink}">
                                <c:choose>
                                    <c:when test="${course.status.id == 3}"><h3>Курс находится в
                                        разработке</h3></c:when>
                                    <c:otherwise>
                                        <div id="management">
                                            <form method="get" action="${contextPath}/course/${course.id}">
                                                <button class="detailed" type="submit" value="Подробнее">Подробнее
                                                </button>
                                            </form>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </section>
                        <c:if test="${active.indexOf(course) % 3==2 ||active.indexOf(course) == active.size()-1}"></div></c:if>
                    </c:forEach>
                </div>

                <div id="over">
                    <c:forEach items="${over}" var="course">
                        <c:if test="${over.indexOf(course) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h4>${course.name}</h4>
                                <img src="${course.photolink}">
                                <c:choose>
                                    <c:when test="${course.status.id == 3}"><h3>Курс находится в
                                        разработке</h3></c:when>
                                    <c:otherwise>
                                        <div id="management">
                                            <form method="get" action="${contextPath}/course/${course.id}">
                                                <button class="detailed" type="submit" value="Подробнее">Подробнее
                                                </button>
                                            </form>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </section>
                        <c:if test="${over.indexOf(course) % 3==2 ||over.indexOf(course) == over.size()-1}"></div></c:if>
                    </c:forEach>
                </div>

                <div id="not_begin">
                    <c:forEach items="${not_begin}" var="course">
                        <c:if test="${not_begin.indexOf(course) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h4>${course.name}</h4>
                                <img src="${course.photolink}">
                                <c:choose>
                                    <c:when test="${course.status.id == 3}"><h3>Курс находится в
                                        разработке</h3></c:when>
                                    <c:otherwise>
                                        <div id="management">
                                            <form method="get" action="${contextPath}/course/${course.id}">
                                                <button class="detailed" type="submit" value="Подробнее">Подробнее
                                                </button>
                                            </form>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </section>
                        <c:if test="${not_begin.indexOf(course) % 3==2 ||not_begin.indexOf(course) == not_begin.size()-1}"></div></c:if>
                    </c:forEach>
                </div>
            </c:if>


        </section>
    </main>

</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
</footer>
<script src="${pageContext.request.contextPath}/resources/js/indexCourse.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>


</html>
