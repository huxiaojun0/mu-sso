<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/15
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Shiro验证登陆</h3>
<form id="form" action="/dologin" method="post">
    <input type="text" name="username" /><br/>
    <input type="password" name="password" /><br/>
    <button type="submit" name="button">登陆</button>
</form>
<%--用于输入后台返回的验证错误信息 --%>
<P><c:out value="${message }" /></P>
</body>
</html>
