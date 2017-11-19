var stompClient = null;
var socket = null;

$().ready(function () {
    disconnect();
    connect();
    connectSubscribes();
    initData();
});

function disconnect() {
    if (stompClient) {
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
        categoriesForumElements.subscribeSocket();
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
    categoriesForumElements.getData();
    usersMembersElements.getData();
    postsCategoryElements.getData();
    commentsForumElements.getData();
}

//back to top
$(function () {
    var backToTop = $('.back-to-top');

    $(window).scroll(function () {
        clearTimeout($.data(this, 'scrollTimer'));

        if ($(this).scrollTop() > 100) {
            backToTop.fadeIn();
            $.data(this, 'scrollTimer', setTimeout(function () {
                backToTop.fadeOut();
            }, 2000));
        } else {
            backToTop.fadeOut();
        }
    });

    backToTop.click(function () {
        $('body, html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });
});

//sticky podsumowanie koszyka
$(function () {
    var navHandle = $('#nav-handle');
    var fixedNavHandle = $('#fixed-nav-handle');

    if (navHandle.length) {
        $(window).scroll(function () {
            var windowTop = $(window).scrollTop();

            if (windowTop === 0) {

                fixedNavHandle.stop().animate({
                    opacity: 0.0
                }, 500, function () {
                    fixedNavHandle.css("display", "none")
                });

                navHandle.stop().animate({
                    opacity: 1
                }, 200, function () {
                });

            } else {

                fixedNavHandle.stop().animate({
                    opacity: 1
                }, 200, function () {
                    fixedNavHandle.css("display", "block")
                });

                navHandle.stop().animate({
                    opacity: 0
                }, 500, function () {
                });

            }
        });
    }
});







