<%--
  Created by IntelliJ IDEA.
  User: ktf_y
  Date: 2024-09-14
  Time: 오전 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정 페이지</title>
</head>
<body>
    <form id="form1" action="/todo/modify" method="POST">
        <div>
            <input type="text" name="tno" value="${todoDTO.tno}" readonly>
        </div>
        <div>
            <input type="text" name="title" value="${todoDTO.title}">
        </div>
        <div>
            <input type="date" name="dueDate" value="${todoDTO.dueDate}">
        </div>
        <div>
            <input type="checkbox" name="finished" ${todoDTO.finished ? "완료" : "미완료"} readonly">
        </div>

        <div>
            <button type="submit">수정</button>
        </div>
    </form>

    <form id="form2" action="/todo/remove" method="POST">
        <input type="hidden" name="tno" value="${todoDTO.tno}" readonly>
        <div>
            <button type="submit">제거</button>
        </div>
    </form>
</body>
</html>
