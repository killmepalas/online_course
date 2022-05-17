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
<div class="container">
    <main id="course">
        <form action="/test/find/" path="name">
            <input type="text" placeholder="Search" name="courses" class="search">
        </form>
        <c:if test="${status == 'teacher'}">
            <c:if test="${tests.isEmpty()}">
                <section class="help">
                <h4>У курса пока нет тестов. Создайте свой первый тест прямо сейчас!</h4>
            </c:if>
                <div>
                    <form method="get" action="/test/create/${id_course}">
                        <button class="detailed" type="submit" >Создать тест</button>
                    </form>
                </div></section>
            <c:if test="${!tests.isEmpty()}">
                <c:forEach items="${tests}" var="test">
                    <c:choose>
                        <c:when test="${tests.indexOf(test) % 3==0}"><div class="row"><section class="col-3">
                            <div>
                                <h4>Название: ${test.name}</h4>
                                <h4>Описание: ${test.description}</h4>
                                <form method="get" action="/test/show/${test.id}">
                                    <button class="detailed" type="submit" value="Управление">Управление</button>
                                </form>
                            </div>
                        </section>
                        </c:when>
                        <c:when test="${tests.indexOf(test) % 3==1}"><section class="col-3">
                            <div>
                                <h4>Название: ${test.name}</h4>
                                <h4>Описание: ${test.description}</h4>
                                <form method="get" action="/test/show/${test.id}">
                                    <button class="detailed" type="submit" value="Управление">Управление</button>
                                </form>
                            </div>
                        </section>
                        </c:when>
                        <c:otherwise><section class="col-3">
                            <div>
                                <h4>Название: ${test.name}</h4>
                                <h4>Описание: ${test.description}</h4>
                                <form method="get" action="/test/show/${test.id}">
                                    <button class="detailed" type="submit" value="Управление">Управление</button>
                                </form>
                            </div>
                        </section></div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
        </c:if>

        <c:if test="${status == 'student'}">
            <c:if test="${tests.isEmpty()}">
                <section class="help">
                    <h4>У курса пока нет тестов.</h4>
                </section>
            </c:if>
            <c:if test="${!tests.isEmpty()}">
                <c:forEach items="${tests}" var="test">
                    <c:choose>
                        <c:when test="${tests.indexOf(test) % 3==0}"><div class="row"><section class="col-3">
                            <div>
                                <h4>Название: ${test.name}</h4>
                                <h4>Описание: ${test.description}</h4>
                                <form method="get" action="/test/execute/${test.id}">
                                    <button class="detailed" type="submit" value="Пройти">Пройти</button>
                                </form>
                            </div>
                        </section>
                        </c:when>
                        <c:when test="${tests.indexOf(test) % 3==1}"><section class="col-3">
                            <div>
                                <h4>Название: ${test.name}</h4>
                                <h4>Описание: ${test.description}</h4>
                                <form method="get" action="/test/execute/${test.id}">
                                    <button class="detailed" type="submit" value="Пройти">Пройти</button>
                                </form>
                            </div>
                        </section>
                        </c:when>
                        <c:otherwise><section class="col-3">
                            <div>
                                <h4>Название: ${test.name}</h4>
                                <h4>Описание: ${test.description}</h4>
                                <form method="get" action="/test/execute/${test.id}">
                                    <button class="detailed" type="submit" value="Пройти">Пройти</button>
                                </form>
                            </div>
                        </section></div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
        </c:if>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>
