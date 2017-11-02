var groups = [];
var groupsElements = null;

function groupsSubscribe(frame) {
    console.log('Connected group: ' + frame);
    stompClient.subscribe('/group', function (data) {
        pushGroupsData(data);
    });
}

function getGroups() {
    fetch(
        'http://localhost:8080/api/group/?limit=5', {
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
            groups = response;
            renderGroups();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderGroups() {
    groupsElements = new Vue({
        el: '#groupsComponent',
        data: {
            groups: groups
        }
    });
}

function pushGroupsData(data) {
    var obj = jQuery.parseJSON(data.body);
    groups.unshift(obj);
    if (groups.length > 5) {
        groups.pop();
    }
    if (groupsElements) {
        groupsElements.groups = groups;

        return;
    }
    renderGroups();
}