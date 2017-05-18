<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.urfu.entities.User" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>Twitter</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/app.js"></script>
</head>
<body>
<%
    try {
        User user = (User) request.getAttribute("user");
        out.println("<h1><a href=\"\\"+user.getLogin()+"\">"+user.getLogin()+"</a></h1>");
    }
    catch (Exception ex) {
        out.println();
    }
    out.println();
%>
<p></p>
Boards: <br>
<a href="/my_board">My board</a> <br>
<a href="/friends_board">Friends board</a> <br>
<a href="/general_board">General board</a> <br><br>

<a href="/direct">My direct</a> <br><br>

<a href="/users">Show users</a> <br>
<a href="/logout">Log out</a>
</body>
</html>