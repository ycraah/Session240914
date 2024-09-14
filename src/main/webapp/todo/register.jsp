<%--
  Created by IntelliJ IDEA.
  User: ktf_y
  Date: 2024-09-13
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/todo/register" method="POST">
        <div>
            <input type="text" name="title" placeholder="제목을 입력하세요.">
        </div>
        <div>
            <input type="date" name="dueDate">
        </div>
        <button type="reset">초기화</button>
        <button type="submit">등록 처리</button>
    </form>
</body>
</html>
