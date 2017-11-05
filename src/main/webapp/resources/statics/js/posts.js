var postsElements = new Vue({
    el: '#postsComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        subscribe: '/post',
        url: 'http://localhost:8080/api/post/'
    },
    methods: {
        getData: function () {
            var self = this;
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
            stompClient.subscribe(self.subscribe, function (data) {
                self.getData();
            });
        }
    }
});


var postsCategoryElements = new Vue({
    el: '#postsCategoryComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        categoryId: null,
        subscribe: '/category/' + this.categoryId + '/posts',
        url: 'http://localhost:8080/api/category/' + this.categoryId + '/posts'
    },
    methods: {
        getData: function () {
            var self = this;
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
            stompClient.subscribe(self.subscribe, function (data) {
                self.getData();
            });
        }
    }
});


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

