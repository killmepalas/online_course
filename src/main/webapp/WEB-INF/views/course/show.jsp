<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>О курсе</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">
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
    <main id="show">
        <section class="help">
            <div class="show">
                <h2>${course.name}</h2>
                <h4>Автор - ${teacher.username}</h4>
                <h4>Обучающихся - ${sCount} чел.</h4>
                <p class="description">${course.description}</p>
            </div>
            <img src="${course.photolink}"/>
            <c:choose>
                <c:when test="${teach_course == 'teacher'}">
                    <div class="profile">
                        <form method="get" action="${contextPath}/course/update/${course.id}">
                            <input class="logout" type="submit" value="Редактировать">
                        </form>
                        <form method="post" action="${contextPath}/course/delete/${course.id}?${_csrf.parameterName}=${_csrf.token}">
                            <input class="logout" type="submit" onclick="return confirm('Вы действительно хотите удалить данный курс?')" value="Удалить">
                        </form>
                        <form method="get" action="${contextPath}/test/${course.id}">
                            <input class="logout" type="submit" value="Конструктор">
                        </form>
                    </div>
                </c:when>

                <c:when test="${teach_course == 'added'}">
                    <div class="profile">
                        <form method="get" action="${contextPath}/topic/${course.id}">
                            <input class="logout" type="submit" value="Обучение">
                        </form>
                        <c:choose>
                            <c:when test="${grade != null}">
                                <p>Шкала прогресса:</p>
                                <p>
                                    <progress id="progressbar" value="${grade.grade}" max="100"></progress>
                                    <span class="progress-value">${grade.grade}%</span>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p>Вы пока не начали проходить этот курс</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:when>

                <c:when test="${teach_course == 'not_auth'}">
                    <div class="profile">
                       <p>Хотите начать обучение?</p>
                       <p><a href="${contextPath}/login">Войдите</a></p>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="profile">
                        <div class="not-teach">
                            <form method="post" action="${contextPath}/course/add/${course.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input  type="submit" value="Добавить в Моё обучение">
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>


    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>
