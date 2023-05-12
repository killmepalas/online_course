<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Лучшие онлайн-курсы</title>
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
    <main id="main">
        <h1>Learnability - ваш проводник в мир знаний.</h1>
        <p>Найдите интересующий вас курс в нашем <a href="${contextPath}/course/category/index">каталоге курсов</a>.</p>
        <h3>Не знаете, с чего начать?</h3>
        <p>Пройдите наш <a href="${contextPath}/career_test/index">тест на профориентацию</a>, а в конце мы подберём подходящие для вас курсы.</p>

        <h3>А может вы хотите попробовать себя в роли преподавателя?</h3>
        <form method="get" action="${contextPath}/course/teach">
            <button class="logout" type="submit" value="Удалить">Попробовать преподавание</button>
        </form>
    </main>
    <footer>
        <p>Телефон: +6(666)-666-66-66</p>
        <p>E-mail: thebestonlinecoursesintheworld@the.best</p>
    </footer>
</div>
</body>

</html>

