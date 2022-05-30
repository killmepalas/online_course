<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
  <meta charset="utf-8">
  <title>Преподавание</title>
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
<div class="container">
  <main id="course">
    <c:if test="${course.isEmpty()}">
      <section class="help">
        <h4>У вас пока нет курсов. <a href="..">Начните обучение прямо сейчас</a></h4>
      </section>
    </c:if>

    <c:if test="${!course.isEmpty()}">
    <c:forEach items="${course}" var="courselist">
    <c:choose>
    <c:when test="${course.indexOf(courselist) % 3==0}"><div class="row"><section class="col-3">
    <div>
      <h4>${courselist.name}</h4>
      <img src="${courselist.photolink}">
      <div id="management">
        <form method="get" action="/course/${courselist.id}" >
          <button class="detailed" type="submit" value="Подробнее">Управление</button>
        </form>
      </div>
    </div>
  </section>
    </c:when>
    <c:when test="${course.indexOf(courselist) % 3==1}"><section class="col-3">
      <div>
        <h4>${courselist.name}</h4>
        <img src="${courselist.photolink}">
        <div id="management">
          <form method="get" action="/course/${courselist.id}" >
            <button class="detailed" type="submit" value="Подробнее">Управление</button>
          </form>
        </div>
      </div>
    </section>
      </c:when>
      <c:otherwise><section class="col-3">
      <div>
        <h4>${courselist.name}</h4>
        <img src="${courselist.photolink}">
        <div id="management">
          <form method="get" action="/course/${courselist.id}" >
            <button class="detailed" type="submit" value="Подробнее">Управление</button>
          </form>
        </div>
      </div>
    </section></div>
        </c:otherwise>
        </c:choose>

      </c:forEach>
      </c:if>
</div>
<div class="row">
    <form method="get" action="/course/create">
      <button class="detailed" type="submit" value="Подробнее">Создать новый курс</button>
    </form>
</div>

  </main>
  <footer>
    <p>Мы ничего не упеваем и ничего не понимаем.</p>
  </footer>
</div>
</body>

