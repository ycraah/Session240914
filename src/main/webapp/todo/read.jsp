<%--
  Created by IntelliJ IDEA.
  User: ktf_y
  Date: 2024-09-13
  Time: 오후 7:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <input type="text" name="tno" value="${todoDTO.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${todoDTO.title}" readonly>
    </div>
    <div>
        <input type="date" name="dueDate" value="${todoDTO.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" ${todoDTO.finished ? "완료" : "미완료"} readonly">
    </div>

    <div>
        <a href="/todo/modify?tno=${todoDTO.tno}">수정/삭제</a>
        <a href="/todo/list">리스트</a>
    </div>
</body>
</html>
