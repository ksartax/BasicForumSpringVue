var globalStatistics = [];
var globalStatisticsElements = null;

function globalStatisticsSubscribe(frame) {
    console.log('Connected globalStatistic: ' + frame);
    stompClient.subscribe('/globalStatistic', function (data) {
        getGlobalStatistics();
    });
}

function getGlobalStatistics() {
    fetch(
        'http://localhost:8080/api/global-statistic/', {
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
            globalStatistics = response;
            renderGlobalStatistics();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderGlobalStatistics() {
    globalStatisticsElements = new Vue({
        el: '#globalStatisticsComponent',
        data: {
            globalStatistics: globalStatistics
        }
    });
}