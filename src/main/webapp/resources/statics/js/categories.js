var categoriesTemplateElements = new Vue({
    el: '#categoriesComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        subscribe: '/category',
        url: 'http://localhost:8080/api/category/?limit=5'
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

var categoriesForumElements = new Vue({
    el: '#categoriesForumComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        active: '#categoriesForumComponent',
        subscribe: '/category/level/0',
        url: 'http://localhost:8080/api/category/'
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

            $( this.active ).stop().animate({scrollTop:999999999}, 500, 'swing', function() {});
        },
        subscribeSocket: function () {
            var self = this;
            if ($(self.active).attr("data-active") === undefined) {
                return;
            }

            stompClient.subscribe(self.subscribe, function (data) {
                self.getData();
            });
        },
        remove: function (id) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            var self = this;

            $.ajax({
                url: 'http://localhost:8080/api/category/remove/' + id,
                type: 'DELETE',
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

new Vue({
    el: '.form-add-category',
    data: {
        description: '',
        title: '',
        level: 1
    },
    methods: {
        submit: function () {
            if (!this.validate()) {
                return false;
            }

            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");

            $.ajax({
                url: 'http://localhost:8080/api/category/add',
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
        },
        validate: function () {
            var element1 = $("#f-a-c-t-e");
            var element2 = $("#f-a-c-d-e");

            element1.hide();
            element2.hide();

            if (this.title === "") {
                element1.show();

                return false;
            }

            if (this.description === "") {
                element2.show();

                return false;
            }

            return true;
        }
    }
});
