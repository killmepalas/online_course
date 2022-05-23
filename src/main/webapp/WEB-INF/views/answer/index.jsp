<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Ответы на вопрос</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="/course/index/0">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
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
                        <form method="get" action="/answer/update/${answer.id}">
                            <input type="submit" class="test" value="Редактировать">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="/answer/delete/${answer.id}?${_csrf.parameterName}=${_csrf.token}">
                            <input type="submit" class="test" value="Удалить">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
            <form method="get" action="/answer/create/${question_id}">
                <button class="teach" type="submit" >Добавить ответ</button>
            </form>
            <form method="get" action="/question/${test}">
                <button class="teach" type="submit" >Назад</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
