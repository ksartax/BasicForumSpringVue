var commentsElements = new Vue({
    el: '#commentsComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        active: '#postsComponent',
        subscribe: '/comment',
        url: 'http://localhost:8080/api/comment/'
    },
    methods: {
        getData: function () {
            var self = this;
            if ($(self.active).attr("data-active") === undefined) {
                return;
            }

            fetch(self.url, {
                method: self.method,
                headers: self.headers
            })
                .then(function (response) {
                    return response.json();
                })
                .then(function (response) {
                    self.reloadData(response);
                });
        },
        reloadData: function (data) {
            while (this.dataArray.length > 0) {
                this.dataArray.pop();
            }
            for (var i = 0; i < data.length; i++) {
                this.dataArray.push(data[i]);
            }
        },
        subscribeSocket: function () {
            var self = this;
            if ($(self.active).attr("data-active") === undefined) {
                return;
            }

            stompClient.subscribe(self.subscribe, function (data) {
                self.getData();
            });
        }
    }
});


var commentsForumElements = new Vue({
    el: '#commentsForumComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        active: '#commentsForumComponent',
        postId: $(this.active).attr("post-id"),
        subscribe: '/post/' + this.postId + '/comments',
        url: 'http://localhost:8080/api/post/' + this.postId + '/comments'
    },
    created: function () {
        this.postId = $(this.active).attr("post-id");
        this.subscribe = '/post/' + this.postId + '/comments';
        this.url = 'http://localhost:8080/api/post/' + this.postId + '/comments';
    },
    methods: {
        getData: function () {
            var self = this;
            if ($(self.active).attr("data-active") === undefined) {
                return;
            }

            fetch(self.url, {
                method: self.method,
                headers: self.headers
            })
                .then(function (response) {
                    return response.json();
                })
                .then(function (response) {
                    self.reloadData(response);
                });
        },
        reloadData: function (data) {
            while (this.dataArray.length > 0) {
                this.dataArray.pop();
            }
            for (var i = 0; i < data.length; i++) {
                this.dataArray.push(data[i]);
            }
        },
        subscribeSocket: function () {
            var self = this;
            if ($(self.active).attr("data-active") === undefined) {
                return;
            }

            stompClient.subscribe(self.subscribe, function (data) {
                self.getData();
            });
        }
    }
});


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
                url: 'http://localhost:8080/api/comment/add/post/' + $("#commentsForumComponent").attr("post-id"),
                type: 'POST',
                data: JSON.stringify(this.$data),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                    xhr.setRequestHeader('Accept', 'application/json');
                    xhr.setRequestHeader('Content-Type', 'application/json');
                    xhr.setRequestHeader('Accept-Language', 'application/json');
                },
                success: function (data) {
                    console.log(data);
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status + ": " + thrownError);
                }
            });
        }
    }
});


