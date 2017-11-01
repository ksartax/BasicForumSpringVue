var stompClient = null;
var socket = null;

$().ready(function () {
    connect();
    getGroups();
    getGlobalStatistics();
    getUsers();
    getComments();
    getPosts();
    getCategories();
    getCategoriesGeneral();
    getCategoriesBasic();
    getPostsCategory();
});

function connect() {
    socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    connectSubscribes();
}

function connectSubscribes() {
    stompClient.connect({}, function (frame) {
        groupsSubscribe(frame);
        globalStatisticsSubscribe(frame);
        usersSubscribe(frame);
        commentsSubscribe(frame);
        postsSubscribe(frame);
        categoriesSubscribe(frame);
        categoriesGeneralSubscribe(frame);
        categoriesBasicSubscribe(frame);
        postsCategorySubscribe(frame);
    });
}









