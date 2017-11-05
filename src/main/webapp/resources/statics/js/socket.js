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
        categoriesTemplateElements.subscribeSocket();
        usersTemplateElements.subscribeSocket();
        postsElements.subscribeSocket();
        commentsElements.subscribeSocket();
        categoriesGeneralElements.subscribeSocket();
        categoriesBasicElements.subscribeSocket();
        usersMembersElements.subscribeSocket();
        postsCategoryElements.subscribeSocket();
        commentsForumElements.subscribeSocket();

    });
}

function initData() {
    globalStatisticsElements.getData();
    categoriesTemplateElements.getData();
    usersTemplateElements.getData();
    postsElements.getData();
    commentsElements.getData();
    categoriesGeneralElements.getData();
    categoriesBasicElements.getData();
    usersMembersElements.getData();
    postsCategoryElements.getData();
    commentsForumElements.getData();

}










