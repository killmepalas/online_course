<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>${category.name}</title>
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
        <h1>Вам подходит ${category.name}</h1>

        <h3>${category.description}</h3>

        <p>Мы подобрали для вас несколько курсов в данной категории:</p>
        <c:if test="${!courses.isEmpty()}">
        <c:forEach items="${courses}" var="course">
        <c:if test="${courses.indexOf(course) % 3==0}"><div class="row"></c:if>
            <section class="col-3">
                <div>
                    <h4>${course.name}</h4>
                    <img src="${course.photolink}">
                    <div id="management">
                        <form method="get" action="${contextPath}/course/${course.id}">
                            <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                        </form>
                    </div>
                </div>
            </section>
            <c:if test="${courses.indexOf(course) % 3==2}"></div></c:if>
        </c:forEach>

        <p>Больше курсов в нашем <a href="${contextPath}/course/index/0">каталоге курсов</a>.</p>
</div>
</c:if>
</main>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
</footer>
</div>
</body>

</html>