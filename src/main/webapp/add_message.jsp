<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="message.css">
<body>
    <form action="/add_message" method="Post">
        <textarea rows="4" placeholder="Ваше сообщение здесь..." maxlength="180" name="msg"></textarea>
        <button type="submit">Twitt!</button>
</form>
</body>
</html>