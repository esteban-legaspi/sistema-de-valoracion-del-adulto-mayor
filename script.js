
// =============================================
// NAVEGACIÓN ENTRE PANTALLAS
// =============================================
function mostrar(id) {
  document.querySelectorAll('.pantalla').forEach(p => p.classList.remove('activa'));
  document.getElementById(id).classList.add('activa');
}

// =============================================
// LOGIN
// =============================================
function iniciarSesion() {
  const rol  = document.getElementById('rolSelect').value;
  const id   = document.getElementById('inputId').value.trim();
  const conf = document.getElementById('checkConf').checked;

  if (!rol)  { alert('Por favor seleccione su rol.'); return; }
  if (!id)   { alert('Por favor ingrese su matrícula o ID.'); return; }
  if (!conf) { alert('Debe aceptar el aviso de confidencialidad para continuar.'); return; }

  // Actualizar dashboard con el rol
  const roles = {
    estudiante:   'Estudiante',
    profesor:     'Profesor',
    coordinador:  'Coordinación'
  };
  document.getElementById('badgeUsuario').textContent = roles[rol];
  document.getElementById('saludo').textContent = 'Bienvenido/a, ' + id;

  mostrar('dashboard');
}

function cerrarSesion() {
  mostrar('login');
}

// =============================================
// NAVEGACIÓN DASHBOARD → CONSENTIMIENTO → FORM
// =============================================
function irConsentimiento() {
  mostrar('consentimiento');
  iniciarFirma();
}

function irFormulario() {
  mostrar('formulario');
  cambiarSeccion(0);
  generarPreguntasMmse();
  generarPreguntasGds();
  generarPreguntasKatz();
}

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

// =============================================
// FILTRO POR GÉNERO (Sección 4)
// =============================================
function verificarGenero() {
  var genero = document.getElementById('generoPaciente').value;
  var fem = document.getElementById('bloque-femenino');
  var mas = document.getElementById('bloque-masculino');
  if (fem) fem.style.display = (genero === 'F') ? 'block' : 'none';
  if (mas) mas.style.display = (genero === 'M') ? 'block' : 'none';
}

// =============================================
// CASILLAS: marcar/desmarcar estilo
// =============================================
function toggleMarcada(input) {
  var label = input.closest('.casilla-item');
  if (input.type === 'radio') {
    // Para radio: desmarcar todos del mismo name
    document.querySelectorAll('input[name="' + input.name + '"]').forEach(function(r) {
      r.closest('.casilla-item').classList.remove('marcada');
    });
  }
  if (input.checked) {
    label.classList.add('marcada');
  } else {
    label.classList.remove('marcada');
  }
}

// =============================================
// MINI MENTAL (MMSE) — preguntas y puntaje
// =============================================
var preguntasMmse = [
  { texto: '¿En qué año estamos?',              pts: 1 },
  { texto: '¿En qué estación del año estamos?', pts: 1 },
  { texto: '¿En qué día (fecha) estamos?',      pts: 1 },
  { texto: '¿En qué mes estamos?',              pts: 1 },
  { texto: '¿En qué día de la semana?',         pts: 1 },
  { texto: '¿En qué hospital o lugar estamos?', pts: 1 },
  { texto: '¿En qué piso o planta estamos?',    pts: 1 },
  { texto: '¿En qué ciudad estamos?',           pts: 1 },
  { texto: '¿En qué provincia o estado?',       pts: 1 },
  { texto: '¿En qué país estamos?',             pts: 1 }
];

function generarPreguntasMmse() {
  var contenedor = document.getElementById('preguntas-mmse');
  if (!contenedor || contenedor.innerHTML !== '') return;
  preguntasMmse.forEach(function(p, i) {
    contenedor.innerHTML += `
      <div class="escala-pregunta">
        <span>${i+1}. ${p.texto}</span>
        <div class="resp-sinno">
          <label><input type="radio" name="mmse${i}" value="1" onchange="calcularMmse()"/> Correcto</label>
          <label><input type="radio" name="mmse${i}" value="0" onchange="calcularMmse()"/> Incorrecto</label>
        </div>
      </div>`;
  });
}

function calcularMmse() {
  var total = 0;
  for (var i = 0; i < preguntasMmse.length; i++) {
    var sel = document.querySelector('input[name="mmse' + i + '"]:checked');
    if (sel) total += parseInt(sel.value);
  }
  document.getElementById('puntaje-mmse').textContent = total;
  var inter = total >= 27 ? 'Normal' : total >= 24 ? 'Sospecha patológica' : total >= 12 ? 'Deterioro' : 'Demencia';
  var col   = total >= 27 ? 'var(--verde-ok)' : total >= 24 ? 'var(--dorado-uaa)' : 'var(--rojo-error)';
  var el = document.getElementById('inter-mmse');
  el.textContent = inter;
  el.style.color = col;
}

// =============================================
// GDS-15 — preguntas y puntaje automático
// =============================================
// puntua=true → un punto si responde "Sí"
// puntua=false → un punto si responde "No"
var preguntasGds = [
  { texto: '¿Está satisfecho/a con su vida en general?',                               puntaSi: false },
  { texto: '¿Ha abandonado muchas de sus actividades e intereses?',                    puntaSi: true  },
  { texto: '¿Siente que su vida está vacía?',                                          puntaSi: true  },
  { texto: '¿Se siente aburrido/a con frecuencia?',                                    puntaSi: true  },
  { texto: '¿Se siente de buen humor la mayor parte del tiempo?',                      puntaSi: false },
  { texto: '¿Tiene miedo de que le pase algo malo?',                                   puntaSi: true  },
  { texto: '¿Se siente feliz la mayor parte del tiempo?',                              puntaSi: false },
  { texto: '¿Se siente a menudo desesperanzado/a?',                                    puntaSi: true  },
  { texto: '¿Prefiere quedarse en casa en lugar de salir e intentar cosas nuevas?',    puntaSi: true  },
  { texto: '¿Siente que tiene más problemas de memoria que la mayoría?',               puntaSi: true  },
  { texto: '¿Cree que estar vivo/a en este momento es maravilloso?',                   puntaSi: false },
  { texto: '¿Siente que su vida en este momento es bastante inútil?',                  puntaSi: true  },
  { texto: '¿Se siente lleno/a de energía?',                                           puntaSi: false },
  { texto: '¿Considera que su situación no tiene remedio?',                            puntaSi: true  },
  { texto: '¿Cree que la mayoría de las personas son mejores que usted?',              puntaSi: true  }
];

function generarPreguntasGds() {
  var contenedor = document.getElementById('preguntas-gds');
  if (!contenedor || contenedor.innerHTML !== '') return;
  preguntasGds.forEach(function(p, i) {
    contenedor.innerHTML += `
      <div class="escala-pregunta">
        <span>${i+1}. ${p.texto}</span>
        <div class="resp-sinno">
          <label><input type="radio" name="gds${i}" value="si" onchange="calcularGds()"/> Sí</label>
          <label><input type="radio" name="gds${i}" value="no" onchange="calcularGds()"/> No</label>
        </div>
      </div>`;
  });
}

function calcularGds() {
  var total = 0;
  preguntasGds.forEach(function(p, i) {
    var sel = document.querySelector('input[name="gds' + i + '"]:checked');
    if (!sel) return;
    if (p.puntaSi  && sel.value === 'si') total++;
    if (!p.puntaSi && sel.value === 'no') total++;
  });
  document.getElementById('puntaje-gds').textContent = total;
  var inter, col;
  if (total <= 5)      { inter = 'Normal';          col = 'var(--verde-ok)'; }
  else if (total <= 10){ inter = 'Depresión leve';  col = 'var(--dorado-uaa)'; }
  else                 { inter = 'Depresión grave'; col = 'var(--rojo-error)'; }
  var el = document.getElementById('inter-gds');
  el.textContent = inter;
  el.style.color = col;
}

// =============================================
// ÍNDICE DE KATZ — actividades y puntaje
// =============================================
var actividadesKatz = [
  'Baño',
  'Vestido',
  'Uso del WC',
  'Movilidad',
  'Continencia',
  'Alimentación'
];

function generarPreguntasKatz() {
  var contenedor = document.getElementById('preguntas-katz');
  if (!contenedor || contenedor.innerHTML !== '') return;
  actividadesKatz.forEach(function(a, i) {
    contenedor.innerHTML += `
      <div class="escala-pregunta">
        <span>${i+1}. ${a}</span>
        <div class="resp-sinno">
          <label><input type="radio" name="katz${i}" value="0" onchange="calcularKatz()"/> Independiente</label>
          <label><input type="radio" name="katz${i}" value="1" onchange="calcularKatz()"/> Dependiente</label>
        </div>
      </div>`;
  });
}

function calcularKatz() {
  var total = 0;
  for (var i = 0; i < actividadesKatz.length; i++) {
    var sel = document.querySelector('input[name="katz' + i + '"]:checked');
    if (sel) total += parseInt(sel.value);
  }
  document.getElementById('puntaje-katz').textContent = total;
  var inter, col;
  if (total === 0)     { inter = 'Independiente';       col = 'var(--verde-ok)'; }
  else if (total <= 2) { inter = 'Dependencia leve';    col = 'var(--dorado-uaa)'; }
  else                 { inter = 'Dependencia severa';  col = 'var(--rojo-error)'; }
  var el = document.getElementById('inter-katz');
  el.textContent = inter;
  el.style.color = col;
}

// =============================================
// FINALIZAR VALORACIÓN
// =============================================
function finalizarValoracion() {
  alert('✅ Valoración guardada exitosamente.\n\nEl resumen estará disponible en el dashboard y podrá exportarse como PDF.');
  mostrar('dashboard');
  // Reiniciar sección
  seccionActual = 0;
}