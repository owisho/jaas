<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
    <form action="/login.do" method="post">
        user:<input type="text" name="username"/>
        password:<input type="password" name="password"/>
        <input type="submit"/>
    </form>
  </body>
</html>