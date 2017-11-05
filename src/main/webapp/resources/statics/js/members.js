var activityElement = $(".activity-element");
var profilElement = $(".profil-element");
var friendElement = $(".friend-element");

var activityElementButon = $(".activity-element-button");
var profilElementButon = $(".profil-element-button");
var friendElementButon = $(".friend-element-button");

function activeA() {
    activityElement.css('display', "inline");
    profilElement.css('display', "none");
    friendElement.css('display', "none");

    activityElementButon.addClass("active");
    profilElementButon.removeClass("active");
    friendElementButon.removeClass("active");
}

function activeB() {
    profilElement.css('display', "inline");
    activityElement.css('display', "none");
    friendElement.css('display', "none");

    profilElementButon.addClass("active");
    activityElementButon.removeClass("active");
    friendElementButon.removeClass("active");
}

function activeC() {
    activityElement.css('display', "none");
    profilElement.css('display', "none");
    friendElement.css('display', "inline");

    friendElementButon.addClass("active");
    profilElementButon.removeClass("active");
    activityElementButon.removeClass("active");
}

