<%@ page import="ru.urfu.entities.Message" %>
<%@ page import="ru.urfu.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
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
<ul class="profile">
    <%
        List<Message> messages = (List<Message>) request.getAttribute("messages");
        User profileUser = (User)request.getAttribute("user");

        out.println("<h1>" + profileUser.getLogin() + "</h1>");
        String senderName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!senderName.equals(profileUser.getLogin())) {
            out.println("<form action=\"/send_dm\" method=\"POST\">");
            out.println("<textarea rows=\"4\" placeholder=\"Your message here...\" maxlength=\"180\" name=\"msg\"></textarea>");
            out.println("<input type=\"hidden\" name=\"sender\" value=\"" + senderName + "\" />");
            out.println("<input type=\"hidden\" name=\"client\" value=\"" + profileUser.getLogin() + "\" />");
            out.println("<button type=\"submit\">Send direct message!</button>");
            out.println("</form>");
        }

        out.println("<h3>My friends:</h3>");
        for (User friend : profileUser.getFriends())
            out.println("<a href=\"\\"+friend.getLogin()+"\">"+friend.getLogin()+"</a><br>");
        out.println("<br>");

        out.println("<h3>My messages:</h3>");
        if (messages.size() == 0)
            out.print("There are no messages!!!");
        else
            for (Message msg:messages) {
                out.println(msg.getMessage() + " " + msg.getLikes().size());
                if (msg.getRetwitedAuthor() != null)
                    out.println("Retwited from : " + msg.getRetwitedAuthor());

                if (profileUser.getLogin().equals(msg.getAuthorName())) {
                    out.println("<form action=\"/delete_message/" + msg.getId() + "\" method=\"POST\">");
                    out.println("<button type=\"submit\">Delete</button>");
                    out.println("</form>");
                }

                if (!msg.getLikes().contains(profileUser.getId())) {
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
                out.println("<p></p>");
            }
    %>
</ul>
<a href="/">back</a>
</body>
</html>