// =============================================
// NAVEGACIÓN ENTRE PANTALLAS
// =============================================
function mostrar(id) {
  document.querySelectorAll('.pantalla').forEach(p => p.classList.remove('activa'));
  document.getElementById(id).classList.add('activa');
}

function navegar(pagina) {
  window.location.href = pagina;
}

// =============================================
// NAVEGACIÓN ENTRE SECCIONES DEL FORMULARIO
// =============================================
var seccionActual = 0;
var totalSecciones = 7;

function cambiarSeccion(n) {
  // Ocultar sección actual
  document.getElementById('sec-' + seccionActual).classList.remove('activa');
  document.getElementById('paso-' + seccionActual).classList.remove('activo');
  document.getElementById('paso-' + seccionActual).classList.add('completado');

  seccionActual = n;

  // Mostrar nueva sección
  document.getElementById('sec-' + seccionActual).classList.add('activa');
  document.getElementById('paso-' + seccionActual).classList.remove('completado');
  document.getElementById('paso-' + seccionActual).classList.add('activo');

  // Scroll al inicio del formulario
  window.scrollTo({ top: 0, behavior: 'smooth' });
}