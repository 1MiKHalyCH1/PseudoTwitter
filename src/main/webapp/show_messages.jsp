<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<link rel="stylesheet" type="text/css" href="twitter.css">
<body>
    <h1>twitter</h1>
    This is your twitter application
    <br>
    <ul class="messages">
        <%
            List<String> messages = (List<String>) request.getAttribute("messages");

            for (int i = 0; i < messages.size(); i++) {
                out.println("<form action=\"/delete_message/"+i+"\" method=\"POST\">");
                out.println(messages.get(i));
                out.println("<button type=\"submit\">Delete</button>");
                out.println("</form>");
            }
        %>
    </ul>
    <a href="/">Main</a>
</body>
</html>