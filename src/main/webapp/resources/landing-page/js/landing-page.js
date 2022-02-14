"use strict";

$(document).ready(function () {
  setTimeout(function () {
    $('.text').fadeIn();
  }, 500);
});
setTimeout(function () {
  $('.container-loading').fadeOut();
  $('.container-fluid').fadeIn();
}, 6000);
