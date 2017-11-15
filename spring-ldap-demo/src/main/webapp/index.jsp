<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/9
  Time: 14:05
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
  <input type="text" name="username" />${error }<br/>
  <input type="password" name="password" /><br/>
  <button type="submit" name="button">登陆</button>
</form>

</body>
</html>

