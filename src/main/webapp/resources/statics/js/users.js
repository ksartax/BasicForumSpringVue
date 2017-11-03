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
        'http://localhost:8080/api/user/?limit=5', {
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



var usersMembers = [];
var usersMembersElements = null;

function usersMembersSubscribe(frame) {
    var isActive = $("#usersMembersComponent").attr("data-i");
    if (isActive == undefined) {
        return;
    }

    console.log('Connected usersMembers: ' + frame);
    stompClient.subscribe('/user/members', function (data) {
        var obj = jQuery.parseJSON(data.body);
        usersMembersElements.users.unshift(obj);
    });
}

function getUsersMembers() {
    var isActive = $("#usersMembersComponent").attr("data-i");
    if (isActive == undefined) {
        return;
    }

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
            usersMembers = response;
            renderUsersMembers();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderUsersMembers() {
    usersMembersElements = new Vue({
        el: '#usersMembersComponent',
        data: {
            users: usersMembers
        }
    });
}