<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Конструктор курса</title>
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
        <form action="/question/find/" path="name">
            <input type="text" placeholder="Search" name="question" class="search">
        </form>
        <c:if test="${questions.isEmpty()}">
            <section>
                <h4>У теста пока нет вопросов (к вам). Добавьте первый вопрос прямо сейчас!</h4>
            </section>
        </c:if>

        <c:if test="${!questions.isEmpty()}">
        <c:forEach items="${questions}" var="question">
            <section>
                <div>
                    <h4>№ ${questions.indexOf(question)+1}. ${question.text}</h4>
                </div>
                <div>
                    <form method="get" action="/question/update/${question.id}">
                        <input type="submit" value="Редактировать">
                    </form>
                </div>
                <div>
                    <form method="post" action="/question/delete/${question.id}/${question.test.id}?${_csrf.parameterName}=${_csrf.token}">
                        <input type="submit"   value="Удалить">
                    </form>
                </div>
                <div>
                    <form method="get" action="/answer/${question.id}">
                        <input type="submit" value="Добавить ответы">
                    </form>
                </div>
            </section>
            </c:forEach>
            </c:if>
            <section id="center">
                <form method="get" action="/question/create/${test}">
                    <button class="detailed" type="submit" >Добавить вопрос</button>
                </form>
            </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
