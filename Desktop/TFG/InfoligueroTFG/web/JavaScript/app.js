const equipoButtons = document.querySelectorAll('.bg-danger button');
const selectedTeamSpan = document.getElementById('selected-team');
let selectedEquipo = null;

equipoButtons.forEach(button => {
  button.addEventListener('click', event => {
    const equipoId = event.currentTarget.dataset.id;
    // find the selected Equipo object from the equipos array
    selectedEquipo = equipos.find(equipo => equipo.getId() == equipoId);
    selectedTeamSpan.textContent = selectedEquipo.getNombre();
  });
});