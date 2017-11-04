var comments = [];
var commentsElements = null;

function commentsSubscribe(frame) {
    var isActive = $("#commentsComponent").attr("data-i");
    if (isActive == undefined) {
        return;
    }

    console.log('Connected comment: ' + frame);
    stompClient.subscribe('/comment', function (data) {
        var obj = jQuery.parseJSON(data.body);
        commentsElements.comments.unshift(obj);
        if (commentsElements.comments.length > 5) {
            commentsElements.comments.pop();
        }
    });
}

function getComments() {
    var isActive = $("#commentsComponent").attr("data-i");
    if (isActive == undefined) {
        return;
    }

    fetch(
        'http://localhost:8080/api/comment/', {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Accept-Language': 'application/json'
            }
        })
        .then(function (response) {
            return response.json();
        })
        .then(function (response) {
            comments = response;
            renderComments();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderComments() {
    groupsElements = new Vue({
        el: '#commentsComponent',
        data: {
            comments: comments
        }
    });
}










var commentsForum = [];
var commentsFoumElements = null;

function forumSubscribe(frame) {
    var postId = $("#commentsForumComponent").attr("data-i");
    if (postId == undefined) {
        return;
    }

    console.log('Connected post/' + postId + '/comments' + frame);
    stompClient.subscribe('/post/' + postId + '/comments', function (data) {
        pushCommentData(data);
    });
}

function pushCommentData(data) {
    var obj = jQuery.parseJSON(data.body);
    commentsFoumElements.commentsForum.push(obj);
}

function getCommentsForum() {
    var postId = $("#commentsForumComponent").attr("data-i");
    if (postId == undefined) {
        return;
    }

    fetch(
        'http://localhost:8080/api/post/' + postId + "/comments", {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Accept-Language': 'application/json'
            }
        })
        .then(function (response) {
            return response.json();
        })
        .then(function (response) {
            commentsForum = response;
            renderCommentsForum();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderCommentsForum() {
    console.log(commentsForum);
    commentsFoumElements = new Vue({
        el: '#commentsForumComponent',
        data: {
            commentsForum: commentsForum
        }
    });
}

var formAddComment = new Vue({
    el: '#form-add-comment',
    data: {
        description: ''
    },
    methods: {
        submit: function () {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");

            $.ajax({
                url: 'http://localhost:8080/api/comment/add/post/' + $("#commentsForumComponent").attr("data-i"),
                type: 'POST',
                data: JSON.stringify(this.$data),
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                    xhr.setRequestHeader('Accept', 'application/json');
                    xhr.setRequestHeader('Content-Type', 'application/json');
                    xhr.setRequestHeader('Accept-Language', 'application/json');
                },
                success: function(data) {
                    console.log(data);
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status + ": " + thrownError);
                }
            });
        }
    }
});


