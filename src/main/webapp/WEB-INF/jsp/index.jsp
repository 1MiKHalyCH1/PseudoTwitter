<!DOCTYPE html>

<html lang="en">

    <body>
        Hello world!
        <br>
        <input type="text" name="msg" size="40">
        <input type="button" onclick=twitt() value="Twitt" />
        <br>
        <a href="/messages">Show messages</a>
    </body>
    <script>
        function twitt() {
            var x = document.getElementsByName("msg")[0].value;
            window.location.assign("/sending?msg="+x);
        }
    </script>

</html>