<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Содержание лекции</title>
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
        <c:if test="${status == 2}">
            <section class="help">
                <div class="background">
                    <h1>${lecture.name}</h1>
                    <h3>${lecture.description}</h3>
                    <div>
                        <p>${lecture.text}</p>
                    </div>
                    <form method="get" action="${contextPath}/lecture/update/${lecture.id}">
                        <button class="detailed" type="submit">Редактировать лекцию</button>
                    </form>
                    <form method="post"
                          action="${contextPath}/lecture/delete/${lecture.id}?${_csrf.parameterName}=${_csrf.token}">
                        <input class="detailed" type="submit"
                               onclick="return confirm('Вы действительно хотите удалить лекцию?')"
                               value="Удалить лекцию">
                    </form>
                </div>
            </section>
        </c:if>

        <c:if test="${status == 1}">
            <section class="help">
                <div class="background">
                    <h1>${lecture.name}</h1>
                    <h3>${lecture.description}</h3>
                    <div>
                        <p>${lecture.text}</p>
                    </div>
                </div>
            </section>
        </c:if>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>
