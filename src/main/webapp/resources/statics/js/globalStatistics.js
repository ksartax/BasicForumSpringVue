var globalStatisticsElements = new Vue({
    el: '#globalStatisticsComponent',
    data: {
        dataArray: [],
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Accept-Language': 'application/json'
        },
        subscribe: '/globalStatistic',
        url: 'http://localhost:8080/api/global-statistic/'
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
