var categories = [];
var categoriesElements = null;

function categoriesSubscribe(frame) {
    console.log('Connected category: ' + frame);
    stompClient.subscribe('/category', function (data) {
        console.log("g-p");
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



var categoriesGeneral = [];
var categoriesElementsGeneral = null;

function categoriesGeneralSubscribe(frame) {
    if ($("#categoriesGeneralComponent").attr("data-i") == undefined) {
        return;
    }

    console.log('Connected category/general: ' + frame);
    stompClient.subscribe('/category/general', function (data) {
        var obj = jQuery.parseJSON(data.body);
        categoriesElementsGeneral.categories.unshift(obj);
        if (categoriesElementsGeneral.categories.length > 5) {
            categoriesElementsGeneral.categories.pop();
        }
    });
}

function getCategoriesGeneral() {
    if ($("#categoriesGeneralComponent").attr("data-i") == undefined) {
        return;
    }

    fetch(
        'http://localhost:8080/api/category/generals', {
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
            categoriesGeneral = response;
            renderCategoriesGeneral();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderCategoriesGeneral() {
    categoriesElements = new Vue({
        el: '#categoriesGeneralComponent',
        data: {
            categoriesGeneral: categoriesGeneral
        }
    });
}




var categoriesBasic = [];
var categoriesElementsBasic = null;

function categoriesBasicSubscribe(frame) {
    if ($("#categoriesBasicComponent").attr("data-i") == undefined) {
        return;
    }

    console.log('Connected category/basic: ' + frame);
    stompClient.subscribe('/category/basic', function (data) {
        var obj = jQuery.parseJSON(data.body);
        categoriesElementsBasic.categories.unshift(obj);
        if (categoriesElementsBasic.categories.length > 5) {
            categoriesElementsBasic.categories.pop();
        }
    });
}

function getCategoriesBasic() {
    if ($("#categoriesBasicComponent").attr("data-i") == undefined) {
        return;
    }

    fetch(
        'http://localhost:8080/api/category/basics', {
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
            categoriesBasic = response;
            renderCategoriesBasic();
        })
        .catch(function (err) {
            console.log(err);
        });
}

function renderCategoriesBasic() {
    categoriesElements = new Vue({
        el: '#categoriesBasicComponent',
        data: {
            categoriesBasic: categoriesBasic
        }
    });
}

var formAddCategory = new Vue({
    el: '#form-add-category',
    data: {
        description: '',
        title: '',
        level: 1
    },
    methods: {
        submit: function () {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");

            $.ajax({
                url: 'http://localhost:8080/api/category/add',
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