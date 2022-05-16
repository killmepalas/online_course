<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>О курсе</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="../course">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="show">
        <section class="help">
            <div class="show">
                <h2>${course.name}</h2>
                <h4>Автор - ${teacher.username}</h4>
            </div>
            <img src="${course.photolink}"/>
            <div class="show">
                <p id="description">${course.description}</p>
            </div>
            <c:choose>
                <c:when test="${teach_course != 'teacher'}">
                    <div class="not-teach">
                        <form method="post" action="/course/add/${course.id}">
                            <c:if test="${course.price != 0}">
                                <input type="submit" value=${course.price}>
                            </c:if>
                            <c:if test="${course.price==0}">
                                <input type="submit" value="Бесплатно">
                            </c:if>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="button-teach">
                        <div class="updatediv">
                            <form method="get" action="/course/update/${course.id}">
                                <input type="submit" value="Редактировать">
                            </form>
                        </div>
                        <div class="deletediv">
                            <form method="post" action="/course/delete/${course.id}?${_csrf.parameterName}=${_csrf.token}">
                                <input type="submit" onclick="return confirm('Вы действительно хотите удалить данный курс?')" value="Удалить">
                            </form>
                        </div>
                        <div class="detaileddiv">
                            <form method="get" action="/test/${course.id}">
                                <input type="submit" value="Конструктор">
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>


    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
