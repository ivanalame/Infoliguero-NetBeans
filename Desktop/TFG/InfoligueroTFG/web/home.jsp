<%-- 
    Document   : home
    Created on : 08-may-2023, 10:33:11
    Author     : Iván Juárez
--%>

<%@page import="java.time.LocalTime"%>
<%@page import="entities.Respuesta"%>
<%@page import="entities.Pregunta"%>
<%@page import="entities.Jugador"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="entities.Equipo"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>
   
    <title>InfoLiguero</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
   <!-- <link rel="stylesheet" href="info.css">-->
    <link href="info.css" rel="stylesheet" type="text/css"/>
    <!-- Favicon -->
    <link rel="icon" href="img/favicon.jpg" type="image/x-icon">
    
</head>

<body class="fondo">
    
    
     <%
         List<Equipo> equipos = (List<Equipo>) session.getAttribute("equipos");
          List<Jugador> jugadores = (List<Jugador>) session.getAttribute("jugadores");
          List<Jugador> jugadoresfiltrados = (List<Jugador>) session.getAttribute("jugadoresfiltrados");
          List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");
          List<Respuesta> respuestas = (List<Respuesta>) session.getAttribute("respuestas");
           
        Equipo equipoSeleccionado = (Equipo) session.getAttribute("equiposelected");
         // Equipo idequipo = (Equipo) session.getAttribute("idequipo");  //ver 
        Pregunta preguntaseleccionada = (Pregunta) session.getAttribute("preguntaseleccionada");
         Respuesta escorrecta = (Respuesta) session.getAttribute("escorrecta"); 
         Usuario user = (Usuario) session.getAttribute("user");
        %>
    <nav class="navbar navbar-expand-sm navbar-dark bg-danger p-2 movemenu">
        <a class="navbar-brand" href="#"><img src="img/logo.png" class="w-25" alt=""></a>
        <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavId">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="Controller?op=inicio">Volver a la central </a>
                </li>
            </ul>
            <ul>
                 <li class="list-unstyled text-white " id="app">
                <h2><i>{{title}}</i></h2>

            </li>
            </ul>
           
            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                 <li >
                     <img src="img/LaLiga_Santander_logo__stacked_.svg-removebg-preview-removebg-preview.png" alt="" class="w-25">
                </li>

                <li class="nav-item dropdown  rounded-pill mr-5">
                    <a class="nav-link dropdown-toggle seleccionbutton" href="#" id="dropdownId" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">Selecciona Liga</a>
                    <div id="ligas" class="dropdown-menu" aria-labelledby="dropdownId">
                        <a class="dropdown-item movediv" href="Controller?op=inicio">{{laliga}}</a>
                        <a class="dropdown-item movediv" href="Controller?op=vapremier">{{premier}}</a>
                        <a class="dropdown-item movediv" href="Controller?op=vaitalia">{{serie}}</a>
                        <a class="dropdown-item movediv" href="Controller?op=vabundes">{{bundesliga}} </a>
                        <a class="dropdown-item movediv" href="Controller?op=vafrancia">{{ligue}}</a>
                    </div>
                </li>
                <li class="ms-auto">
                     <% if (user!=null) {%>
                     <h5 class="text-white">Bienvenido <%=user.getNick()%> </h5><a href="Controller?op=logout"><button class="cancelbutton ml-3 text-center">Logout</button></a>
                    <%} else {%>
                    <button class="loginbutton text-center " data-toggle="modal" data-target="#modallogin">
                        Login <img src="" alt="" class="w-25 pl-1"></button>
                         <%}
            %>
                </li>
            </ul>
        </div>
    </nav>
                
    <div class="bg-danger text-center  justify-content-center row ">
     <% for(Equipo equipo : equipos){%>   
        <!-- Esto nos lo hara un bucle que sacara las imagnenes de la bbdd -->    
              <a class="nav-link" class="active"  href="Controller?op=vaequipo&equipo=<%=equipo.getId()%>&nombre=<%=equipo.getNombre()%>"><img src="<%=equipo.getImagen()%>" class="w-100" alt=""></a>  
       
    <%}%>
      </div>  
      
      <% if (equipoSeleccionado != null) { %>
         <div class="text-center pt-3 movemenu text-danger plantilla muestranombreequipos">   
             <h2><strong>Plantilla del <%= session.getAttribute("nombre")%> </strong></h2>     
        </div> 
     <% } %>
      
    <div class="container  ">
     
        <div class="row justify-content-center mx-4 pt-3 movefiltro">
          
           <% if (equipoSeleccionado != null) { %>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaequipo&equipo=<%=equipoSeleccionado.getId()%>&nombre=<%=equipoSeleccionado.getNombre()%>">Todos</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicion&posicion=Portero&equipoId=<%= equipoSeleccionado.getId() %>">Porteros</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicion&posicion=Defensa&equipoId=<%= equipoSeleccionado.getId() %>">Defensas</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicion&posicion=Centrocampista&equipoId=<%= equipoSeleccionado.getId() %>">Centrocampistas</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicion&posicion=Delantero&equipoId=<%= equipoSeleccionado.getId() %>">Delanteros</a>
           <% } %>
        </div>
        
         <% if (escorrecta != null) {%>
            <div class="row justify-content-center mx-4 mt-3  respuesta ">
                <% if (escorrecta.getCorrecta()) { %>     
                       
                    <img src="img/7efs.gif" alt="" class="w-25">    
                <% } else { %>       
                    <img src="img/giphy.gif" alt="" class="w-25">
                <% } %>
            </div>
                   
            <% } %>

       

        <div class="row justify-content-center px-3 mt-5">
          
    
<%      
    if (jugadoresfiltrados == null && jugadores != null) {
            for(Jugador jugador : jugadores){%>   
            <div class="col-md-6 col-lg-4 p-3 movediv">
                <div class="card h-100 colorborde">
                    <div class="card-body ">
                        <img src="<%=jugador.getFoto()%>" class="float-left w-50" alt="">    
                        <div class="text-right">
                            <h3 class="ml-"><%=jugador.getNombre()%></h3>
                            <h5> Edad: <%=jugador.getEdad()%></h5>
                            <h5><%=jugador.getPosicion()%></h5>
                            <p> Dorsal: <%=jugador.getDorsal()%></p>
                            <p><%=jugador.getFechaNacimiento()%></p>
                            <p><%=jugador.getPais()%></p>

                        </div>
                    </div>
                </div>
            </div>
<%}%>    
   
       <%} else if (jugadoresfiltrados != null) {
            for(Jugador jugador : jugadoresfiltrados){ %> 
        <div class="col-md-6 col-lg-4 p-3 movediv">
                <div class="card h-100 colorborde">
                    <div class="card-body ">
                        <img src="<%=jugador.getFoto()%>" class="float-left w-50" alt="">    
                        <div class="text-right">
                            <h3 class="ml-"><%=jugador.getNombre()%></h3>
                            <h5> Edad: <%=jugador.getEdad()%></h5>
                            <h5><%=jugador.getPosicion()%></h5>
                            <p> Dorsal: <%=jugador.getDorsal()%></p>
                            <p><%=jugador.getFechaNacimiento()%></p>
                            <p><%=jugador.getPais()%></p>

                        </div>
                    </div>
                </div>
            </div>
  <% } %>    
    <% } %>

        </div>

    </div>

    <div class="mt-4 ">
        <div class=" float-right mb-5 mr-4 ">
          <a class="twitter-timeline" data-lang="es" data-width="400" data-height="500" data-theme="dark" 
             href="https://twitter.com/ivanJua16758008?ref_src=twsrc%5Etfw">Tweets by ivanJua16758008</a> 
          <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
        </div>

           <% if (user!=null) {%>
        <!-- Este Div solo aparecera si estas logeado -->
        <div class=" float-left  text-center ml-4 ">
            <h2 class="plantilla"> <strong>¡Atrevete con el quiz!</strong></h2>
            <div id="contador" class="simply-countdown-inline">
            
            </div>
            <div class="mt-3">
                
         <div class="row">
                    <div class="col">
                       <a class="nav-link" href="Controller?op=vapregunta&pregunta=1"><button  id="boton1" onclick="desactivarBoton()" class="rounded buttonquizandinfo">1</button>
                      <a class="nav-link" href="Controller?op=vapregunta&pregunta=4"><button class="rounded buttonquizandinfo">4</button></a>
                      <a class="nav-link" href="Controller?op=vapregunta&pregunta=7"><button class="rounded buttonquizandinfo">7</button></a>
                    </div>
                    <div class="col">
                      <a class="nav-link" href="Controller?op=vapregunta&pregunta=2"><button class="rounded buttonquizandinfo">2</button></a>
                      <a class="nav-link" href="Controller?op=vapregunta&pregunta=5"><button class="rounded buttonquizandinfo">5</button></a>
                      <a class="nav-link" href="Controller?op=vapregunta&pregunta=8"><button class="rounded buttonquizandinfo">8</button></a>
                     
                    </div>
                    <div class="col">
                        <a class="nav-link" href="Controller?op=vapregunta&pregunta=3"><button class="rounded buttonquizandinfo">3</button></a>
                        <a class="nav-link" href="Controller?op=vapregunta&pregunta=6"><button class="rounded buttonquizandinfo">6</button></a>
                        <a class="nav-link" href="Controller?op=vapregunta&pregunta=9"><button class="rounded buttonquizandinfo">9</button></a>
               
                      </div>
                  </div>

                <button class=" rounded buttonquizandinfo mt-3" data-toggle="modal" data-target="#modalinfo">
                    Informacion Quiz</button>        
            </div>

        </div>
         <%}%>
            
            <% if (user!=null&&preguntaseleccionada!=null) {%>
            <div class="d-flex justify-content-center mb-5 mr-4 ">            
               <button class="rounded buttonquizandinfo mt-3" data-toggle="modal" data-target="#modalquiz" data-pregunta="<%=preguntaseleccionada.getTexto()%>" data-idpregunta="<%=preguntaseleccionada.getId()%>">RESPONDER PREGUNTA</button>
            </div>
             <%}%>
            
            
            
     <footer class="bg-danger p-3 text-center text-white d-flex align-items-center">
        <div class="d-flex justify-content-between w-100">
          <div class="flex-grow-1 text-center">
            <h2>&copy; Iván Juárez-S2DAM InfoLiguero-TFG</h2>
          </div>
          <div class="tamañoLetra text-white">Numero de soporte técnico: 717 71 26 99</div>
        </div>
    </footer>

   </div>


    <!-- Modal login-->
    <div class="modal fade" id="modallogin" tabindex="-1" role="dialog" aria-labelledby="modelTitleId"
    aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title"><i class="fa fa-sign-in-alt mr-2"></i>Login & Register</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="Controller?op=login" method="post">
                <div class="modal-body">
                    <p class="mb-3">
                        <label for="nick" class="mr-2">Usuario:</label>
                        <input type="text" name="nick" id="nick" class="form-control" placeholder="Ingresa tu nombre de usuario">
                    </p>
                    <p class="mb-3">
                        <label for="pass" class="mr-2">Contraseña:</label>
                        <input type="password" name="pass" id="pass" class="form-control" placeholder="Ingresa tu contraseña">
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn loginbutton">Iniciar sesión</button>
                    <button type="button" class="btn cancelbutton" data-dismiss="modal">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal Quiz-->
    <div class="modal fade " id="modalquiz" tabindex="-1" role="dialog" aria-labelledby="modelTitleId"
        aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog " role="document">
            <div class="modal-content">
                <div class="modal-header bg-gris justify-content-center">
                    
                    <h4>La pregunta es: </h4>                   
                 
                </div>
                       
                    <div class="modal-body quizbutton" id="respuesta">
                   
                    </div>
            
                
                    <div class="modal-footer">
                        <!--<button type="submit" class="btn btn-success sendbutton">Enviar Respuesta</button> --> 
                        <button type="button" class="btn cancelbutton" data-dismiss="modal">Cancelar</button>
                    </div>
               
            </div>
        </div>
    </div>

    <!-- Modal InfoQUIZ-->
    <div class="modal fade" id="modalinfo" tabindex="-1" role="dialog" aria-labelledby="modelTitleId"
        aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Informacion de nuestro Quiz</h5>
                </div>
                <form action="Controller?op=login" method="post">
                    <div class="modal-body">
                        <p>
                            El quiz consiste en adivinar el maximo numero de preguntas.
                        </p>
                        <p>
                             Tendrás que pulsar en uno de los botones y tras hacerlo se te desbloqueara la opcion de responder la pregunta. 
                          </p>
                          <p>
                           Las preguntas seran renovadas cada semana.
                        </p>
                          <p>
                            Si aciertas aparecera un tick y si fallas una X.
                        </p>
                        <p>
                            -Si aciertas menos  de 5 tendrás que seguir estudiando.
                        </p>
                        <p>
                            -Si aciertas mas de 5 estas aprobado.
                        </p>
                        <p>
                            -Si aciertas mas de 8 eres un experto.              
                        </p>
   
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn cancelbutton" data-dismiss="modal">Entendido</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    

     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
   
   
    <script src="JavaScript/app.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@latest/dist/vue.min.js"></script>


    <script type="text/JavaScript" src="JavaScript/vue.js"> </script>
    <script type="text/JavaScript" src="JavaScript/my.js"> </script>
    
    <script src="JavaScript/myjs.js" type="text/javascript"></script>
   
    <script type="text/javascript" src="JavaScript/button.js"></script>
   
    <script>
        const myApp = app.mount("#app");
        const myliga = liga.mount("#ligas");
    </script>

    <script src="JavaScript/simplyCountdown.min.js"></script>
    <script src="JavaScript/contador.js"></script> 

</body>

</html>