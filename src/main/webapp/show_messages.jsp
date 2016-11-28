<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<body>
<form action="/add_message" method="Post">
    <textarea rows="4" placeholder="Your message hear..." maxlength="180" name="msg"></textarea>
    <button type="submit">Twitt!</button>
</form>
<ul class="messages">
    <%
        List<String> messages = (List<String>) request.getAttribute("messages");
        if (messages.size() == 0)
            out.print("There are no messages!!!");
        else
            for (int i = 0; i < messages.size(); i++) {
                out.println("<form action=\"/delete_message/"+i+"\" method=\"POST\">");
                out.println(messages.get(i));
                out.println("<button type=\"submit\">Delete</button>");
                out.println("</form>");
            }
    %>
</ul>
</body>
</html>