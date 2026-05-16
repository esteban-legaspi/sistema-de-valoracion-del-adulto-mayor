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
