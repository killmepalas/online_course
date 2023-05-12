<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
            <c:if test="${questions.isEmpty()}">
                <h4>У теста пока нет вопросов. Добавьте первый вопрос прямо сейчас!</h4>
            </c:if>

            <c:if test="${!questions.isEmpty()}">

                <c:if test="${test.mix}">
                    <h3>Включено перемешивание вопросов и ответов теста.</h3>
                    <h3>Количество вопросов в одном тесте: ${test.count}</h3>
                </c:if>
                <c:if test="${!test.mix}">
                    <h3>Перемешивание вопросов и ответов теста выключено.</h3>
                </c:if>

                <form method="get" action="${contextPath}/test/mixEdit/${test.id}">
                    <button class="teach" type="submit" >Настройка теста</button>
                </form>

                <table>
                    <tr>
                        <th>№</th>
                        <th>Вопрос</th>
                        <th>Редактирование</th>
                        <th>Удаление</th>
                        <th>Добавление ответов</th>
                    </tr>
                    <c:forEach items="${questions}" var="question">
                        <tr>
                            <td>${questions.indexOf(question)+1}</td>
                            <td>${question.text}</td>
                            <td>
                                <form method="get" action="${contextPath}/question/update/${question.id}">
                                    <input class="test" type="submit" value="Редактировать">
                                </form>
                            </td>
                            <td>
                                <form method="post"
                                      action="${contextPath}/question/delete/${question.id}/${question.test.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input class="test" onclick="return confirm('Вы хотите удалить вопрос?')"
                                           type="submit" value="Удалить">
                                </form>
                            </td>
                            <td>
                                <form method="get" action="${contextPath}/answer/${question.id}">
                                    <input class="test" type="submit" value="Добавить ответы">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <form method="get" action="${contextPath}/question/create/${test.id}">
                <button class="teach" type="submit">Добавить вопрос</button>
            </form>
            <form method="get" action="${contextPath}/test/show/${test.id}">
                <button class="teach" type="submit">Назад</button>
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
