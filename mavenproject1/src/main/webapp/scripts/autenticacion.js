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


  // 💾 guardar datos para la siguiente página
  localStorage.setItem('rol', roles[rol]);
  localStorage.setItem('id', id);


  navegar('pages/dashboard.html');
}

function cerrarSesion() {
  navegar('../index.html');
}