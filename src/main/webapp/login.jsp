<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head></head>
<body>
<h1>Log in</h1>
<form name='f' action="login" method='POST'>
    <table>
        <tr>
            <td>Login:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
<a href="register">Register</a>
</body>
</html>
