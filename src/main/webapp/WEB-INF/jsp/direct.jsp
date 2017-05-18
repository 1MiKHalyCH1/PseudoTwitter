<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ru.urfu.entities.DirectMessage" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
<ul class="messages">
    <%
        List<DirectMessage> messages = (List<DirectMessage>) request.getAttribute("messages");
        if (messages.size() == 0)
            out.print("There are no messages!!!");
        else
            for (DirectMessage msg:messages) {
                out.print("<a href=\"\\" + msg.getSenderName() + "\">" + msg.getSenderName() + "</a>");
                out.println(": " + msg.getMessage());

                out.println("<form action=\"/delete_dm/" + msg.getId() + "\" method=\"POST\">");
                out.println("<button type=\"submit\">Delete</button>");
                out.println("</form>");
            }

    %>
</ul>
<a href="/">back</a>
</body>
</html>