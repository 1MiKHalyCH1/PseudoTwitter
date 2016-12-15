<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Log in</h1>
<c:if test="${param.error != null}">
    <div> Authentication error! Try again </div>
</c:if>
<c:if test="${param.logout != null}">
    <div> You have successfully logged out! </div>
</c:if>
<form name='f' action="/login" method='POST'>
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
<a href="/register">Register</a>
</body>
</html>
