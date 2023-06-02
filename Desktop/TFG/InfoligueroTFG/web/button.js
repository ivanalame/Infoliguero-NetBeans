
console.log('but'); //usar esto para ver hasta donde llega en la consola (SI ME DA ERROR)
    function desactivarBoton() {
  var miBoton = document.getElementById('boton1');
  setTimeout(function() {
    if (document.readyState === "complete") {
      miBoton.setAttribute("disabled", true); // set disabled attribute directly
    } else {
      setTimeout(arguments.callee, 100);
    }
  }, 200);
}

window.addEventListener('load', function() {
  var miBoton = document.getElementById('boton1');
  miBoton.addEventListener('click', desactivarBoton);
});
