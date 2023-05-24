$(document).ready(function() {
    console.log('ready'); //usar esto para ver hasta donde llega en la consola (SI ME DA ERROR)
    init();
});

function init(){
   onshowpregunta();
    
}

function onshowpregunta(){
	$('#modalquiz').on('show.bs.modal', function (event) {
            console.log('ESTOY AQUI');        //  LO PONGO ASI POR EJEMPLO PARA VER SI LLEGO AHI 
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  var pregunta = button.data('pregunta')
           var idpregunta = button.data('idpregunta')
	  var modal = $(this)
	  modal.find('h4').text("La pregunta es: " + pregunta);
          //AQUI LLAMOS A AJAX
          // $.ajax({
           //  type: "GET",
           //   url: "Controller?op=allrespuestas&idpregunta="+idpregunta,    //le paso las peliculas y el idactor que me traje antes 
             // success : function(info) {
              //        modal.find("#respuesta").html(info);          //busca dentro de la modal a movies//en la modal tendo un div con id = movies y dentro ajax pinta 
             //     }
            //  });
	})
}

