<%--
  Created by IntelliJ IDEA.
  User: ktf_y
  Date: 2024-09-12
  Time: 오후 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List Page</h1>
    <h2>${loginInfo}</h2>
    <h3>${loginInfo.mname}</h3>

    <ul>
        <c:forEach var="dto" items="${dtoList}">
            <li>
                <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
                <span>${dto.title}</span>
                <span>${dto.dueDate}</span>
                <span>${dto.finished? "완료" : "미완료"}</span>
            </li>
        </c:forEach>
    </ul>

    <form action="/logout" method="POST">
        <button>로그아웃</button>
    </form>
</body>
</html>
