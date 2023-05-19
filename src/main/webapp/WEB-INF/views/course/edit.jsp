<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование курса</title>
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
    <main>
        <section class="help">
            <form:form method="POST" modelAttribute="course" >
                <h1 class="form-signin-heading">Редактирование курса</h1>

                <spring:bind path="textCategory">
                    <div id="select" class="form-group ${status.error ? 'has-error' : ''}">
                        <form:label
                                path="textCategory">К какой категории относится размещаемый курс?</form:label><br>
                        <form:select path="textCategory" items="${categories}">
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control" value="${course.name}"
                                    placeholder="Название" field="${name}"></form:input>
                        <form:errors path="name"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="description">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="description" class="form-control" value="${course.description}" placeholder="Описание"></form:input>
                        <form:errors path="description"></form:errors>
                    </div>
                </spring:bind>


                <spring:bind path="photolink">
                    <div class="form-group">
                        <form:input type="text" path="photolink" class="form-control" value="${course.photolink}" placeholder="Ссылка на фото"></form:input>
                        <form:errors path="photolink"></form:errors>
                    </div>
                </spring:bind>
                <br>
                <div class="form-group">
                    <form method="post" action="${contextPath}/course/update/${course.id}">
                        <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить</button>
                    </form>
                </div>
            </form:form>
            <form method="get" action="${contextPath}/course/${course.id}">
                <button class="formcource" type="submit" >Вернуться к курсу</button>
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

