var categories = [];
var categoriesElements = null;

function categoriesSubscribe(frame) {
    console.log('Connected category: ' + frame);
    stompClient.subscribe('/category', function (data) {
        var obj = jQuery.parseJSON(data.body);
        categoriesElements.categories.unshift(obj);
        if (categoriesElements.categories.length > 5) {
            categoriesElements.categories.pop();
        }
    });
}

function getCategories() {
    fetch(
        'http://localhost:8080/api/category/', {
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
            categories = response;
            renderCategories();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderCategories() {
    categoriesElements = new Vue({
        el: '#categoriesComponent',
        data: {
            categories: categories
        }
    });
}