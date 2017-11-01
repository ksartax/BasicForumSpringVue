var users = [];
var usersElements = null;

function usersSubscribe(frame) {
    console.log('Connected user: ' + frame);
    stompClient.subscribe('/user', function (data) {
        var obj = jQuery.parseJSON(data.body);
        usersElements.users.unshift(obj);
        if (usersElements.users.length > 5) {
            usersElements.users.pop();
        }
    });
}

function getUsers() {
    fetch(
        'http://localhost:8080/api/user/', {
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
            users = response;
            renderUsers();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderUsers() {
    usersElements = new Vue({
        el: '#usersComponent',
        data: {
            users: users
        }
    });
}