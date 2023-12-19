<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>注册</title>
</head>
<link rel="icon" href="images/search.gif" type="img/x-ico" />
<link href="css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<body>
<div id="top"></div>
<div id="main">
  <img src="images/111.jpg" id="main_bg"/>
  <div id="login_block">
    <form action="/register" method="post" id="loginForm">
      <table border="0">
        <tr>
          <td class="title">用户名:</td>
          <td class="input">
            <input type="text" name="username" id="username" class="login_input"/>
          </td>
        </tr>
        <tr>
          <td class="title">密码:</td>
          <td class="input">
            <input type="password" name="password" id="password" class="login_input"/>
          </td>
        </tr>
        <tr>
        <td class="title">确认密码:</td>
          <td class="input">
            <input type="password" name="confirmPassword" id="confirmPassword" class="login_input"/>
          </td>
        </tr>
        <tr>
          <td class="title">姓名:</td>
          <td class="input">
            <input type="text" name="name" id="name" class="login_input"/>
          </td>
        </tr>
        <tr>
          <td class="title">电话号码:</td>
          <td class="input">
            <input type="text" name="tel" id="tel" class="login_input"/>
          </td>
        </tr>
        <tr>
          <td class="title">卡号:</td>
          <td class="input">
            <input type="text" name="cardId" id="cardId" class="login_input"/>
          </td>
        </tr>
        <tr>
          <td class="title">性别:</td>
          <td class="input">
            <input type="radio" name="gender" value="男"/>&nbsp;&nbsp;男&nbsp;&nbsp;
            <input type="radio" name="gender" value="女"/>&nbsp;&nbsp;女
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input class="btn" type="submit" value="注册"/>
            <div class="btn" id="reset">重&nbsp;&nbsp;置</div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <a href="login.jsp">已有账号？点击登录</a>
          </td>
        </tr>

      </table>
    </form>
  </div>
</div>
<div id="footer">

</div>
</body>
</html>