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