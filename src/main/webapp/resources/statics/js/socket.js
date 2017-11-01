var stompClient = null;
var socket = null;

$().ready(function () {
    connect();
    getGroups();
    getUsers();
    getComments();
    getPosts();
    getGlobalStatistics();
    getCategories();
});

function connect() {
    socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    connectSubscribes();
}

function connectSubscribes() {
    stompClient.connect({}, function (frame) {
        usersSubscribe(frame);
        groupsSubscribe(frame);
        commentsSubscribe();
        postsSubscribe();
        globalStatisticsSubscribe();
        categoriesSubscribe();
    });
}









