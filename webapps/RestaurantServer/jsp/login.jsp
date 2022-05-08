 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="login-page">
    <div class="form">
         
        <form class="login-form" action="${pageContext.request.contextPath}/AdminLoginServelt" method="post">
        	<p>${msg}</p>
            <input  type="text"  placeholder="Username" name="a_username" value="">
            <input type="password" placeholder="Password" name="a_password" value="" >
            <input type="submit" value="Login"/>
            
        </form>
    </div>
</div>
<script src="js/login.min.js"></script>
<script src="js/login1.js"></script>

</body>

</html>