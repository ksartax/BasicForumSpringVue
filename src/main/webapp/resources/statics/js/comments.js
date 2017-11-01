var comments = [];
var commentsElements = null;

function commentsSubscribe(frame) {
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