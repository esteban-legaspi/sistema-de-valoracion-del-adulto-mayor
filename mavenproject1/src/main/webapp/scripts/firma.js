// =============================================
// FIRMA DIGITAL EN CANVAS
// =============================================
var dibujando = false;
var ctx;

function iniciarFirma() {
  var canvas = document.getElementById('firmaCanvas');
  if (!canvas) return;
  ctx = canvas.getContext('2d');
  ctx.strokeStyle = '#003087';
  ctx.lineWidth = 2;
  ctx.lineCap = 'round';

  canvas.onmousedown = function(e) { dibujando = true; ctx.beginPath(); ctx.moveTo(e.offsetX, e.offsetY); };
  canvas.onmousemove = function(e) { if (!dibujando) return; ctx.lineTo(e.offsetX, e.offsetY); ctx.stroke(); };
  canvas.onmouseup   = function()  { dibujando = false; };
  canvas.onmouseleave= function()  { dibujando = false; };

  // Soporte táctil
  canvas.ontouchstart = function(e) {
    e.preventDefault();
    dibujando = true;
    var r = canvas.getBoundingClientRect();
    ctx.beginPath();
    ctx.moveTo(e.touches[0].clientX - r.left, e.touches[0].clientY - r.top);
  };
  canvas.ontouchmove = function(e) {
    e.preventDefault();
    if (!dibujando) return;
    var r = canvas.getBoundingClientRect();
    ctx.lineTo(e.touches[0].clientX - r.left, e.touches[0].clientY - r.top);
    ctx.stroke();
  };
  canvas.ontouchend = function() { dibujando = false; };
}

function limpiarFirma() {
  var canvas = document.getElementById('firmaCanvas');
  ctx.clearRect(0, 0, canvas.width, canvas.height);
}