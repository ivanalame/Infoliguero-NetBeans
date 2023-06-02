
console.log('but'); //usar esto para ver hasta donde llega en la consola (SI ME DA ERROR)
      function disableButton() {
        console.log('button');      
        setTimeout(function() {
          location.reload();
          document.getElementById("boton1").disabled = true;
        }, 1000); // espera un segundo antes de recargar la página
      }
