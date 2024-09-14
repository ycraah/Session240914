<%--
  Created by IntelliJ IDEA.
  User: ktf_y
  Date: 2024-09-14
  Time: 오후 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>로그인창</title>
</head>
<body>
    <c:if test="${param.result == 'error'}">
        <h1>로그인 에러</h1>
    </c:if>

    <form action="/login" method="POST">
        <input type="text" name="mid">
        <input type="text" name="mpw">
        <button type="submit">로그인</button>
        <button type="reset">초기화</button>
    </form>
</body>
</html>
