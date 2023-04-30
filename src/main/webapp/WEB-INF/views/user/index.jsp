<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Управление пользователями</title>
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
            <c:if test="${users.isEmpty()}">
                <h4>Пользователей пока нет</h4>
            </c:if>

            <c:if test="${!users.isEmpty()}">
                <div class="table">
                <table>
                    <tr>
                        <th>id</th>
                        <th>Логин</th>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Отчество</th>
                        <th>Дата рождения</th>
                        <th>Блокировка</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.lastname}</td>
                            <td>${user.name}</td>
                            <td>${user.midname}</td>
                            <td>${user.dateOfBirth}</td>
                            <td>
                                <form method="post" action="${contextPath}/block/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input class="test" type="submit" value="Заблокировать">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
            </c:if>

            <c:if test="${!users.isEmpty()}">
                <form:form modelAttribute="checkRoles" method="POST" action="${pageContext.request.contextPath}/modifyRoles">
                    <div class="table">
                <table class="role">
                    <tr class="role">
                        <th>Логин</th>
                        <th>Назначить</th>
                        <th>Понизить</th>
                    </tr>
                    <form>
                    <c:forEach items="${users}" var="user">
                        <tr class="role">
                            <td>${user.username}</td>
                            <td>
                                <c:forEach items="${user.roles}" var="role">
                                    <c:if test="${role.id == 1}">
                                        <c:set var="student" value="true"/>
                                    </c:if>
                                    <c:if test="${role.id == 2}">
                                        <c:set var="teacher" value="true"/>
                                    </c:if>
                                    <c:if test="${role.id == 3}">
                                        <c:set var="admin" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${student != 'true'}">
                                    <div class="rowtable">
                                        <label>Студент</label>
                                        <form:checkbox path="addRoles.stuRoles" value="${user.id}" />
                                    </div>
                                </c:if>
                                <c:if test="${teacher != 'true'}">
                                    <div class="rowtable">
                                        <label>Преподаватель</label>
                                        <form:checkbox path="addRoles.teachRoles" value="${user.id}" />
                                    </div>
                                </c:if>
                                <c:if test="${admin != 'true'}">
                                    <div class="rowtable">
                                        <label>Админ</label>
                                        <form:checkbox path="addRoles.admRoles" value="${user.id}" />
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${student == 'true'}">
                                    <div class="rowtable">
                                        <label>Студент</label>
                                        <form:checkbox path="delRoles.stuRoles" value="${user.id}" />
                                    </div>
                                </c:if>
                                <c:set var="student" value="false"/>

                                <c:if test="${teacher == true}">
                                    <div class="rowtable">
                                        <label>Преподаватель</label>
                                        <form:checkbox path="delRoles.teachRoles" value="${user.id}" />
                                    </div>
                                </c:if>
                                <c:set var="teacher" value="false"/>

                                <c:if test="${admin == true}">
                                    <div class="rowtable">
                                        <label>Админ</label>
                                        <form:checkbox path="delRoles.admRoles" value="${user.id}" />
                                    </div>
                                </c:if>
                                <c:set var="admin" value="false"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </form>
                </table>
                        <button class="teach" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить</button>
                    </div>
                </form:form>
            </c:if>
            <form method="get" action="${pageContext.request.contextPath}/create">
                <button class="teach" type="submit" >Добавить пользователя</button>
            </form>
            <form method="get" action="${pageContext.request.contextPath}/profile">
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