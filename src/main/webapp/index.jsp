<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <a href="index.jsp"><img src="../../../resources/img/logo.png" width="400"/></a>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="/course">Каталог курсов</a></li>
            <li><a href="../teaching.jsp">Преподавание</a></li>
            <li><a href="/course/my_courses">Моё обучение</a></li>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="main">
        <p>Добро пожаловать на наш наипрекраснейший сайт с различными увлекательными курсами, способными
            научить вас чему угодно: от программирования до кулинарии (я бы лучше научилась печь тортики, честно говоря)
            /не забудь это вырезать/.</p>
        <p>А ещё хотелось бы обратить ваше внимание на этих чудесных котиков</p>
        <img width="300" height="400" src="resources/img/rizhik.jpg"/>
        <img width="400" height="400" src="resources/img/musya.jpg"/>
        <img width="450" height="400" src="resources/img/sinya.jpg"/>
    </main>
    <footer>
        <p>Мы ничего не упеваем и ничего не понимаем.</p>
    </footer>
</div>
</body>

</html>

