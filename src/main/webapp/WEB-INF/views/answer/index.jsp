<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Ответы на вопрос</title>
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
        <c:if test="${answers.isEmpty()}">
            <h4>Ответов на вопрос пока нет.</h4>
        </c:if>
        <c:if test="${!answers.isEmpty()}">
            <table>
                <tr>
                    <th>№</th>
                    <th>Ответ</th>
                    <th>Редактирование</th>
                    <th>Удаление</th>
                </tr>
            <c:forEach items="${answers}" var="answer">
                <c:choose>
                    <c:when test="${answer.is_right == 'true'}"><tr id="true"></c:when>
                    <c:when test="${answer.is_right != 'true'}"><tr id="false"></c:when>
                </c:choose>
                    <td>${answers.indexOf(answer)+1}</td>
                    <td>${answer.text}</td>
                    <td>
                        <form method="get" action="${contextPath}/answer/update/${answer.id}">
                            <input type="submit" class="test" value="Редактировать">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${contextPath}/answer/delete/${answer.id}?${_csrf.parameterName}=${_csrf.token}">
                            <input type="submit" class="test" value="Удалить">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
            <form method="get" action="${contextPath}/answer/create/${question_id}">
                <button class="teach" type="submit" >Добавить ответ</button>
            </form>
            <form method="get" action="${contextPath}/question/${test}">
                <button class="teach" type="submit" >Назад</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>
