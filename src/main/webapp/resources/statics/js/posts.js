var posts = [];
var postsElements = null;

function postsSubscribe(frame) {
    console.log('Connected post: ' + frame);
    stompClient.subscribe('/post', function (data) {
        var obj = jQuery.parseJSON(data.body);
        postsElements.posts.unshift(obj);
        if (postsElements.posts.length > 5) {
            postsElements.posts.pop();
        }
    });
}

function getPosts() {
    fetch(
        'http://localhost:8080/api/post/', {
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
            posts = response;
            renderPosts();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderPosts() {
    postsElements = new Vue({
        el: '#postsComponent',
        data: {
            posts: posts
        }
    });
}




var postsCategory = [];
var postsCategoryElements = null;

function postsCategorySubscribe(frame) {
    var category = $("#postsCategoryComponent");
    if (!category) {
        return;
    }

    console.log('Connected postCategory: ' + frame);
    stompClient.subscribe('/category/posts', function (data) {
        var obj = jQuery.parseJSON(data.body);
        postsCategoryElements.posts.unshift(obj);
    });
}

function getPostsCategory() {
    var category = $("#postsCategoryComponent");
    if (!category) {
        return;
    }

    fetch(
        'http://localhost:8080/api/category/' + category.attr("data-c") + "/posts", {
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
            postsCategory = response;
            renderPostsCategory();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderPostsCategory() {
    postsCategoryElements = new Vue({
        el: '#postsCategoryComponent',
        data: {
            postsCategory: postsCategory
        }
    });
}