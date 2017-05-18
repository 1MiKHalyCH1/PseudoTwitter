<%@ page import="ru.urfu.entities.User" %>
<%@ page import="java.util.List" %>
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
<ul class="users">
<%
    List<User> users = (List<User>) request.getAttribute("users");
    User currentUser = (User) request.getAttribute("user");
    for (User user : users) {
        if (!currentUser.getFriends().contains(user)) {
            out.println("<form action=\"/add_friend/" + user.getId() + "\" method=\"POST\">");
            out.println("<a href=\"\\"+user.getLogin()+"\">"+user.getLogin()+"</a>");
            out.println("<button type=\"submit\">Add Friend</button>");
        } else {
            out.println("<form action=\"/remove_friend/" + user.getId() + "\" method=\"POST\">");
            out.println("<a href=\"\\"+user.getLogin()+"\">"+user.getLogin()+"</a>");
            out.println("<button type=\"submit\">Remove Friend</button>");
        }
        out.println("</form>");
    }
%>
</ul>
<a href="/">back</a>
</body>
</html>