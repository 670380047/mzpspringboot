<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/28
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/WEB-INF/statics/js/jquery-2.1.1.js"></script>
    <title>ModelAttribute展示</title>
    <script type="text/javascript">
       $(function () {
           alert(1);
          $("passwordId").hide();
       });
    </script>
</head>
<body>
ModelAttribute展示：==========<br>
用户名：${UserInfo123.username}<br>
年龄：${UserInfo123.age}<br>
性别：${UserInfo123.sex}
<p id ="passwordId" >密码：${UserInfo123.password}</p>
</body>
</html>
