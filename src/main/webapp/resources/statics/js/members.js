var activityElement = $(".activity-element");
var profilElement = $(".profil-element");
var friendElement = $(".friend-element");
// var groupElement = $(".group-element");

var activityElementButon = $(".activity-element-button");
var profilElementButon = $(".profil-element-button");
var friendElementButon = $(".friend-element-button");
// var groupElementButon = $(".group-element-button");

function activeA() {
    activityElement.css('display', "inline");
    profilElement.css('display', "none");
    friendElement.css('display', "none");
    // groupElement.css('display', "none");

    activityElementButon.addClass("active");
    profilElementButon.removeClass("active");
    friendElementButon.removeClass("active");
    // groupElementButon.removeClass("active");
}

function activeB() {
    profilElement.css('display', "inline");
    activityElement.css('display', "none");
    friendElement.css('display', "none");
    // groupElement.css('display', "none");

    profilElementButon.addClass("active");
    activityElementButon.removeClass("active");
    friendElementButon.removeClass("active");
    // groupElementButon.removeClass("active");
}

function activeC() {
    activityElement.css('display', "none");
    profilElement.css('display', "none");
    friendElement.css('display', "inline");
    // groupElement.css('display', "none");

    friendElementButon.addClass("active");
    profilElementButon.removeClass("active");
    // groupElementButon.removeClass("active");
    activityElementButon.removeClass("active");
}

// function activeD() {
//     groupElement.css('display', "inline");
//     profilElement.css('display', "none");
//     friendElement.css('display', "none");
//     activityElement.css('display', "none");
//
//     groupElementButon.addClass("active");
//     profilElementButon.removeClass("active");
//     friendElementButon.removeClass("active");
//     activityElementButon.removeClass("active");
// }
