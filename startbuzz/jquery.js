$(function(){
  var left = 150;
  var top = 150;
  $('.ccc').click(function(){
    setTimeout(function (){
      $('.border2').css("top",left+=5);
      $('.border2').css("left",top+=5);
    },3000);
  });
});