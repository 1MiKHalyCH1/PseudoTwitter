<%@ page import="java.util.List" %>
<%@ page import="org.springframework.data.util.Pair" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<body>
<form action="/add_message" method="Post">
    <textarea rows="4" placeholder="Your message here..." maxlength="180" name="msg"></textarea>
    <button type="submit">Twitt!</button>
</form>
<ul class="messages">
    <%
        List<Pair<String,Integer>> messages = (List<Pair<String,Integer>>) request.getAttribute("messages");
        if (messages.size() == 0)
            out.print("There are no messages!!!");
        else
            for (Pair<String,Integer>msg:messages) {
                out.println("<form action=\"/delete_message/"+msg.getSecond()+"\" method=\"POST\">");
                out.println(msg.getFirst());
                out.println("<button type=\"submit\">Delete</button>");
                out.println("</form>");
            }
    %>
</ul>
<a href="/">back</a>
</body>
</html>