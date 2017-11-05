var usersTemplateElements = new Vue({
    el: '#usersComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        subscribe: '/user',
        url: 'http://localhost:8080/api/user/?limit=5'
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

var usersMembersElements = new Vue({
    el: '#usersMembersComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        active: '#usersMembersComponent',
        subscribe: '/user',
        url: 'http://localhost:8080/api/user/'
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
