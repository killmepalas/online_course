<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Содержание темы</title>
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
        <c:if test="${status == 'teacher'}">
            <section class="help">
                <div class="background">
                    <h1>${topic.name}</h1>
                    <h3>${topic.description}</h3>
                    <c:if test="${topic.status.id == 1}">
                        <h3 class="green">Тема открыта для обучающихся</h3>
                        <p>Чтобы отредактировать тему, пожалуйста, закройте её.</p>
                        <form method="post"
                              action="${contextPath}/topic/close/${topic.id}?${_csrf.parameterName}=${_csrf.token}">
                            <button class="detailed" type="submit">Закрыть тему</button>
                        </form>
                    </c:if>
                    <c:if test="${topic.status.id == 3}">
                        <h3 class="red">Тема в разработке</h3>
                        <form method="get" action="${contextPath}/topic/update/${topic.id}">
                            <button class="detailed" type="submit">Редактировать тему</button>
                        </form>
                        <p>Чтобы тема была видна обучающимся, просто откройте её.</p>
                        <form method="post"
                              action="${contextPath}/topic/open/${topic.id}?${_csrf.parameterName}=${_csrf.token}">
                            <button class="detailed" type="submit">Открыть тему</button>
                        </form>
                        <form method="post"
                              action="${contextPath}/topic/delete/${topic.id}?${_csrf.parameterName}=${_csrf.token}">
                            <input class="detailed" type="submit"
                                   onclick="return confirm('Вы действительно хотите удалить тему?')"
                                   value="Удалить тему">
                        </form>
                    </c:if>
                </div>
                <c:if test="${((tests.isEmpty())&&(lectures.isEmpty()))}">
                    <h4>У темы пока нет лекций и тестов. Добавьте элементы темы сейчас!</h4>
                </c:if>
                <h3>Найдено элементов курса: ${tests.size()+lectures.size()}</h3>
                <c:if test="${topic.status.id == 3}">
                    <div>
                        <form method="get" action="${contextPath}/test/create/${topic.id}">
                            <button class="detailed" type="submit">Создать тест</button>
                        </form>
                    </div>
                    <div>
                        <form method="get" action="${contextPath}/lecture/create/${topic.id}">
                            <button class="detailed" type="submit">Создать лекцию</button>
                        </form>
                    </div>
                </c:if>
                <div>
                    <form method="get" action="${contextPath}/topic/${course_id}">
                        <button class="detailed" type="submit">Вернуться к конструктору курса</button>
                    </form>
                </div>
                <c:if test="${!lectures.isEmpty()}">
                    <c:forEach items="${lectures}" var="lecture">
                        <c:if test="${lectures.indexOf(lecture) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h1>${lecture.name}</h1>
                                <h4>${lecture.description}</h4>
                                <form method="get" action="${contextPath}/lecture/show/${lecture.id}">
                                    <button class="detailed" type="submit" value="Управление">Управление</button>
                                </form>
                            </div>
                        </section>
                        <c:if test="${((lectures.indexOf(lecture) % 3==2)||(lectures.size()-1 == lectures.indexOf(lecture)))}"></div></c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${!tests.isEmpty()}">
                    <c:forEach items="${tests}" var="test">
                        <c:if test="${tests.indexOf(test) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h1>${test.name}</h1>
                                <h4>${test.description}</h4>
                                <c:if test="${test.status.id == 1}">
                                    <h3 class="green">Тест опубликован</h3>
                                </c:if>
                                <c:if test="${test.status.id == 3}">
                                    <h3 class="red">Тест в разработке</h3>
                                </c:if>
                                <form method="get" action="${contextPath}/test/show/${test.id}">
                                    <button class="detailed" type="submit" value="Управление">Управление</button>
                                </form>
                            </div>
                        </section>
                        <c:if test="${((tests.indexOf(test) % 3==2)||(tests.indexOf(test) == tests.size()-1))}"></div></c:if>
                    </c:forEach>
                </c:if>
            </section>
        </c:if>

        <jsp:useBean id="now" class="java.util.Date" scope="page"/>

        <c:if test="${status == 'student'}">
            <section class="help">
                <c:if test="${((tests.isEmpty())&&(lectures.isEmpty()))}">
                    <h4>У темы пока нет лекций и тестов. Возвращайтесь позже!</h4>
                </c:if>
                <h1>Найдено элементов курса: ${countActiveTests+lectures.size()}</h1>
                <c:if test="${!lectures.isEmpty()}">
                    <c:forEach items="${lectures}" var="lecture">
                        <c:if test="${lectures.indexOf(lecture) % 3==0}"><div class="row"></c:if>
                        <section class="col-3">
                            <div>
                                <h1>${lecture.name}</h1>
                                <h4>${lecture.description}</h4>

                                <form method="get" action="${contextPath}/lecture/show/${lecture.id}">
                                    <button class="detailed" type="submit">Изучить</button>
                                </form>
                            </div>
                        </section>
                        <c:if test="${((lectures.indexOf(lecture) % 3==2)||(lectures.size()-1 == lectures.indexOf(lecture)))}"></div></c:if>

                    </c:forEach>
                </c:if>
                <c:if test="${!tests.isEmpty()}">
                    <c:forEach items="${tests}" var="test">
                        <c:if test="${test.status.id == 1}">
                            <c:if test="${tests.indexOf(test) % 3==0}"><div class="row"></c:if>
                            <c:forEach items="${grades}" var="grade">
                                <c:if test="${grade.test.id == test.id}">
                                    <c:choose>
                                        <c:when test="${grade.grade <=40}">
                                            <section class="col-3, redBack">
                                        </c:when>
                                        <c:when test="${grade.grade <=80}">
                                            <section class="col-3, yellowBack">
                                        </c:when>
                                        <c:otherwise>
                                            <section class="col-3, greenBack">
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set var="hasGrade" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${hasGrade != 'true'}">
                                <section class="col-3, background">
                            </c:if>
                            <c:set var="hasGrade" value="false"/>
                            <div>
                                <h1>${test.name}</h1>
                                <h4>${test.description}</h4>
                                <c:forEach items="${grades}" var="grade">
                                    <c:if test="${grade.test.id == test.id}"><h4>Оценка: ${grade.grade}</h4></c:if>
                                </c:forEach>
                                <form method="get" action="${contextPath}/test/start/${test.id}/1">
                                    <button class="detailed" type="submit">Пройти</button>
                                </form>
                            </div>
                            </section>
                            <c:if test="${((tests.indexOf(test) % 3==2)||(tests.indexOf(test) == tests.size()-1))}"></div></c:if>
                        </c:if>
                    </c:forEach>
                </c:if>
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
