var height = 200;
var width = 500;

var canvas = document.getElementById("myCanvas");
canvas.height = height;
canvas.width = width;
canvas.style = "border:1px solid #c3c3c3;";

var length = 20;
var r = 200 - length;
var x = 0;
var y = 0;
var dx = 1;
var dy = 0;
var dy = (x) => dx * Math.cos((x / r) * Math.PI);
var siwtchMode = true;
var minus = -1;

var ctx = canvas.getContext("2d");

setInterval(() => {
  ctx.clearRect(x, y, 200, 200);

  if (r < x || x < 0) {
    dx = -1 * dx;
    minus *= minus;
    siwtchMode != siwtchMode;
    dy = siwtchMode
      ? (x) => -1 * Math.sin((x / r) * Math.PI)
      : (x) => Math.cos((x / r) * Math.PI);
  }

  x += dx;
  y += dx * dy(x);

  ctx.fillStyle = "red";
  ctx.fillRect(x, y, length, length);
}, 10);
