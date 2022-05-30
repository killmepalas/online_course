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
            <li><a href="/course/index/0">Каталог курсов</a></li>
            <li><a href="/course/teach">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="course">
        <section class="help">
            <form action="/user/find/" path="name">
                <input type="text" placeholder="Search" name="user" class="search">
            </form>
            <c:if test="${users.isEmpty()}">
                <h4>Пользователей пока нет</h4>
            </c:if>

            <c:if test="${!users.isEmpty()}">
                <table>
                    <tr>
                        <th>id</th>
                        <th>Логин</th>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Отчество</th>
                        <th>Дата рождения</th>
                        <th>Рейтинг</th>
                        <th>Удалить</th>
                        <th>Студент</th>
                        <th>Преподаватель</th>
                        <th>Админ</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.lastname}</td>
                            <td>${user.name}</td>
                            <td>${user.midname}</td>
                            <td>${user.dateOfBirth}</td>
                            <td>${user.rating}</td>
                            <td>
                                <form method="post" action="/delete/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                                    <input class="test" onclick="return confirm('Вы хотите удалить пользователя?')" type="submit"   value="Удалить">
                                </form>
                            </td>

                            <td>
                                <c:forEach items="${user.roles}" var="role">
                                    <c:if test="${role.id == 1}">
                                        <c:set var="student" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${student != 'true'}">
                                <form method="post" action="/setRole/1/${user.id}">
                                    <button class="test" type="submit"   name="${_csrf.parameterName}" value="${_csrf.token}">Назначить</button>
                                </form>
                                </c:if>
                                <c:if test="${student == 'true'}">
                                <form method="post" action="/deleteRole/1/${user.id}">
                                    <button class="test" type="submit"   name="${_csrf.parameterName}" value="${_csrf.token}">Понизить</button>
                                </form>
                                </c:if>
                                <c:set var="student" value="false"/>
                            </td>
                            <td>
                                <c:forEach items="${user.roles}" var="role">
                                    <c:if test="${role.id == 2}">
                                        <c:set var="teacher" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${teacher != 'true'}">
                                    <form method="post" action="/setRole/2/${user.id}">
                                        <button class="test" type="submit"   name="${_csrf.parameterName}" value="${_csrf.token}">Назначить</button>
                                    </form>
                                </c:if>
                                <c:if test="${teacher == true}">
                                    <form method="post" action="/deleteRole/2/${user.id}">
                                        <button class="test" type="submit"   name="${_csrf.parameterName}" value="${_csrf.token}">Понизить</button>
                                    </form>
                                </c:if>
                                <c:set var="teacher" value="false"/>
                            </td>
                            <td>
                                <c:forEach items="${user.roles}" var="role">
                                    <c:if test="${role.id == 3}">
                                        <c:set var="admin" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${admin != 'true'}">
                                    <form method="post" action="/setRole/3/${user.id}">
                                        <button class="test" type="submit"   name="${_csrf.parameterName}" value="${_csrf.token}">Назначить</button>
                                    </form>
                                </c:if>
                                <c:if test="${admin == true}">
                                    <form method="post" action="/deleteRole/3/${user.id}">
                                        <button class="test" type="submit"   name="${_csrf.parameterName}" value="${_csrf.token}">Понизить</button>
                                    </form>
                                </c:if>
                                <c:set var="admin" value="false"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <form method="get" action="/create">
                <button class="teach" type="submit" >Добавить пользователя</button>
            </form>
            <form method="get" action="/profile">
                <button class="teach" type="submit" >Назад</button>
            </form>
            <p>${model}</p>
        </section>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>