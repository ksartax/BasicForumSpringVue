var stompClient = null;
var socket = null;

$().ready(function () {
    disconnect();
    connect();
    connectSubscribes();
    initData();
});

function disconnect() {
    if(stompClient) {
        stompClient.disconnect();
    }
}

function connect() {
    socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
}

function connectSubscribes() {
    stompClient.connect({}, function (frame) {
        globalStatisticsElements.subscribeSocket();
    });
}

function initData() {
    globalStatisticsElements.getData();
}










