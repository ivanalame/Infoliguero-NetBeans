$(document).ready(function() {
    console.log('ready');
    init();
});

function init(){
   onshowdetail();
}

function onshowdetail(){
	$('#modalDetail').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  var nombre = button.data('nombre')
	  var id = button.data('id')
	  var modal = $(this)
	  modal.find('h5').text(nombre)
	})
}
