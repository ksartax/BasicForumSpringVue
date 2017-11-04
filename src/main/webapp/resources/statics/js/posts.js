var posts = [];
var postsElements = null;

function postsSubscribe(frame) {
    var isActive = $("#postsComponent").attr("data-i");
    if (isActive == undefined) {
        return;
    }

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
    var isActive = $("#postsComponent").attr("data-i");
    if (isActive == undefined) {
        return;
    }

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


var formAddPost = new Vue({
    el: '#form-add-post',
    data: {
        title: '',
        description: ''
    },
    methods: {
        submit: function () {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");

            $.ajax({
                url: 'http://localhost:8080/api/post/add/category/' + $("#postsCategoryComponent").attr("data-c"),
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




var postsCategory = [];
var postsCategoryElements = null;

function postsCategorySubscribe(frame) {
    var categoryId = $("#postsCategoryComponent").attr("data-c");
    if (categoryId == undefined) {
        return;
    }

    console.log('Connected postCategory: ' + frame);
    stompClient.subscribe('/category/posts', function (data) {
        var obj = jQuery.parseJSON(data.body);
        postsCategoryElements.posts.unshift(obj);
    });
}

function getPostsCategory() {
    var categoryId = $("#postsCategoryComponent").attr("data-c");
    if (categoryId == undefined) {
        return;
    }

    fetch(
        'http://localhost:8080/api/category/' + categoryId + "/posts", {
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




function a() {

}