$(document).ready(function() {
    console.log('ready'); 
    init();
});

function init(){
   onshowpregunta();

}

function onshowpregunta() { 
  $('#modalquiz').on('show.bs.modal', function (event) {
    console.log('ESTOY AQUI');
    var button = $(event.relatedTarget); 
    var pregunta = button.data('pregunta');
    var idpregunta = button.data('idpregunta');
    var modal = $(this);
    modal.find('h4').text(pregunta);
    //AQUI LLAMO A AJAX
    $.ajax({
      type: "GET",
      url: "Controller?op=allrespuestas&idpregunta="+idpregunta, //le paso las respuesta y el pregunta que me traje antes 
      success : function(info) {
        modal.find("#respuesta").html(info); //busca dentro de la modal respuesta //en la modal tengo un div con id = respuesta y dentro ajax pinta 
      }
    });
  });
} 

onshowpregunta();
