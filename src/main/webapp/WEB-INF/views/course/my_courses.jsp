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
    <title>Моё обучение</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="/course">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div class="container">
    <main id="course">
        <form action="/course/find/" path="name">
            <input type="text" placeholder="Search" name="courses" class="search">
        </form>
        <c:if test="${course.isEmpty()}">
         <section>
             <h4>У вас пока нет курсов. <a href="../course">Начните обучение прямо сейчас</a></h4>
         </section>
        </c:if>

        <c:if test="${!course.isEmpty()}">
        <c:forEach items="${course}" var="courselist">
        <c:choose>
        <c:when test="${course.indexOf(courselist) % 3==0}"><section id="left"></c:when>
        <c:when test="${course.indexOf(courselist) % 3==1}"><section id="center"></c:when>
            <c:otherwise><section id="right"></c:otherwise>
                </c:choose>
                <div>
                    <h4>${courselist.name}</h4>
                    <img src="${courselist.photolink}">
                    <article>
                        <c:if test="${courselist.price==0}">
                            <p id="free">Бесплатно</p>
                        </c:if>
                        <c:if test="${courselist.price!=0}">
                            <p id="money">Цена: ${courselist.price} ₽</p>
                        </c:if>
                    </article>
                    <form method="get" action="/course/${courselist.id}" >
                        <button class="detailed" type="submit" value="Подробнее">Подробнее</button>
                    </form>
                </div>
            </section>
            </c:forEach>
            </c:if>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
