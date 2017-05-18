<%@ page import="ru.urfu.entities.Message" %>
<%@ page import="ru.urfu.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
<form action="/add_message" method="Post">
    <textarea rows="4" placeholder="Your message here..." maxlength="180" name="msg"></textarea>
    <button type="submit" onclick="sendPush()">Twitt!</button>
</form>
<ul class="messages">
    <%
        List<Message> messages = (List<Message>) request.getAttribute("messages");
        User currentUser = (User)request.getAttribute("user");
        if (messages.size() == 0)
            out.print("There are no messages!!!");
        else
            for (Message msg:messages) {
                out.print("<a href=\"\\"+msg.getAuthorName()+"\">"+msg.getAuthorName()+"</a>");
                out.println(": " + msg.getMessage() + " <br>Likes:" + msg.getLikes().size());
                if (msg.getRetwitedAuthor() != null)
                    out.println("Retwited from : " + msg.getRetwitedAuthor());

                if (currentUser.getLogin().equals(msg.getAuthorName())) {
                    out.println("<form action=\"/delete_message/" + msg.getId() + "\" method=\"POST\">");
                    out.println("<button type=\"submit\">Delete</button>");
                    out.println("</form>");
                }

                if (!msg.getLikes().contains(currentUser.getId())) {
                    out.println("<form action=\"/like_message/" + msg.getId() + "\" method=\"POST\">");
                    out.println("<button type=\"submit\">Like</button>");
                }
                else {
                    out.println("<form action=\"/dislike_message/" + msg.getId() + "\" method=\"POST\">");
                    out.println("<button type=\"submit\">Disike</button>");
                }
                out.println("</form>");

                out.println("<form action=\"/retwit_message/" + msg.getId() + "\" method=\"POST\">");
                out.println("<button type=\"submit\">Retwit</button>");
                out.println("</form>");
                out.println("<br>");
            }
    %>
</ul>
<a href="/">back</a>
</body>
</html>