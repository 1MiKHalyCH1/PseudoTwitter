var stompClient = null;

function connect() {
    var socket = new SockJS('/simple_websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/push', function (messsage) {
            showPushMessage(JSON.parse(message.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendPush() {
    stompClient.send("/app/push", {}, JSON.stringify({'content': 'New message!'}));
}

function showPushMessage(message) {
    document.title = message;
}

$(function(){
    connect();
});