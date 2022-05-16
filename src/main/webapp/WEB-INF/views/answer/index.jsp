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
            <li><a href="/course">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="course">
        <c:if test="${answers.isEmpty()}">
            <section>
                <h4>Ответов на вопрос пока нет.</h4>
            </section>
        </c:if>

        <c:if test="${!answers.isEmpty()}">
            <c:forEach items="${answers}" var="answer">
                <section>
                    <div>
                        <h4>№ ${answers.indexOf(answer)+1}. ${answer.text}</h4>

                        <c:if test="${answer.is_right == 'true'}">
                            <p>Правильный ответ</p>
                        </c:if>
                    </div>
                    <div>
                        <form method="get" action="/answer/update/${answer.id}">
                            <input type="submit" value="Редактировать">
                        </form>
                    </div>
                    <div>
                        <form method="post" action="/answer/delete/${answer.id}?${_csrf.parameterName}=${_csrf.token}">
                            <input type="submit"   value="Удалить">
                        </form>
                    </div>
                </section>
            </c:forEach>
        </c:if>
        <section id="center">
            <form method="get" action="/answer/create/${question_id}">
                <button class="detailed" type="submit" >Добавить ответ</button>
            </form>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
