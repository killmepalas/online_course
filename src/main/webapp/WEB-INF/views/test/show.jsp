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
            <div class="background">
                <h1>Название: ${test.name}</h1>
                <h3>Описание: ${test.description}</h3>
                <div>
                    <c:choose>
                        <c:when test="${test.status.id == 1}">
                            <p>Закройте тест, чтобы добавить вопросы.</p>
                            <div>
                                <form method="post" action="${contextPath}/test/close/${test.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input class="test" type="submit" value="Закрыть тест">
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <form method="get" action="${contextPath}/test/update/${test.id}">
                                    <input class="test" type="submit" value="Редактировать">
                                </form>
                            </div>
                            <div>
                                <form method="get" action="${contextPath}/question/${test.id}">
                                    <input class="test" type="submit" value="Конструктор">
                                </form>
                            </div>
                            <p>Откройте тест, чтобы он стал доступен пользователям.</p>
                            <div>
                                <form method="post" action="${contextPath}/test/open/${test.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input class="test" type="submit" value="Открыть тест">
                                </form>
                            </div>
                            <div>
                                <form method="post" action="${contextPath}/test/delete/${test.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input class="test" type="submit" onclick="return confirm('Вы действительно хотите удалить данный тест?')" value="Удалить">
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div>
                <form method="get" action="${contextPath}/topic/show/${test.topic.id}">
                    <input class="detailed" type="submit" value="Назад">
                </form>
            </div>
        </section>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>
