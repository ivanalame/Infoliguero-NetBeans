<%-- 
    Document   : bundes
    Created on : 30-may-2023, 11:54:19
    Author     : Iván Juárez
--%>

<%@page import="entities.Pregunta"%>
<%@page import="entities.Jugadorbu"%>
<%@page import="entities.Equipobu"%>
<%@page import="java.util.List"%>
<%@page import="entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>InfoLiguero</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="info.css">
    <link rel="icon" href="img/favicon.jpg" type="image/x-icon">
</head>

<body class="fondo">
     <%
         List<Equipobu> equiposbundes = (List<Equipobu>) session.getAttribute("equiposbu");
          List<Jugadorbu> jugadoresbu = (List<Jugadorbu>) session.getAttribute("jugadoresbu");
           Equipobu equipoSeleccionado = (Equipobu) session.getAttribute("equipobuselected");
            List<Jugadorbu> jugadoresfiltradosbu = (List<Jugadorbu>) session.getAttribute("jugadoresfiltradosbu");
       //   List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");
        //  List<Respuesta> respuestas = (List<Respuesta>) session.getAttribute("respuestas");
        Pregunta preguntaseleccionada = (Pregunta) session.getAttribute("preguntaseleccionada");
     
         Usuario user = (Usuario) session.getAttribute("user");
        %>
    
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark p-2 movemenu">
        <a class="navbar-brand" href="#"><img src="img/logo.png" class="w-25" alt=""></a>
        <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavId">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="Controller?op=vabundes">Volver a la central <span class="sr-only">(current)</span></a>
                </li>

            </ul>
            <ul >
                <li class="list-unstyled text-white " id="app">
                <h2><i>{{title}}</i></h2>
                </li>

            </ul>
           
            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                <li >
                    <img src="img/logobundes.png" alt="" class="w-25">
                </li>
                
                <li class="nav-item dropdown rounded-pill">
                    <a class="nav-link dropdown-toggle seleccionbutton" href="#" id="dropdownId" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">Selecciona Liga</a>
                    <div id="ligas" class="dropdown-menu" aria-labelledby="dropdownId">
                        <a class="dropdown-item movediv" href="Controller?op=vaequipo&equipo=1">{{laliga}}</a>
                        <a class="dropdown-item movediv" href="Controller?op=vapremier">{{premier}}</a>
                        <a class="dropdown-item movediv" href="Controller?op=vaitalia">{{serie}}</a>
                        <a class="dropdown-item movediv" href="Controller?op=vabundes">{{bundesliga}} </a>
                        <a class="dropdown-item movediv" href="Controller?op=vafrancia">{{ligue}}</a>
                    </div>
                </li>
               <li class="ms-auto pl-5">
                     <% if (user!=null) {%>
                     <h5 class="text-white">Bienvenido <%=user.getNick()%> </h5><a href="Controller?op=logoutbundes"><button class="cancelbutton ml-3">Logout</button></a>
                    <%} else {%>
                    <button class="loginbutton " data-toggle="modal" data-target="#modallogin">
                        Login <img src="" alt="" class="w-25 pl-1"></button>
                         <%}
            %>
                </li>
            </ul>
        </div>
    </nav>
    <div class="bg-dark text-center  justify-content-center row">
  <% for(Equipobu equipobundes : equiposbundes){%>   
         <a class="nav-link " class="active" href="Controller?op=vaequipobundes&equipo=<%=equipobundes.getId()%>&nombre=<%=equipobundes.getNombre() %>"><img src="<%=equipobundes.getImagen()%>" alt=""></a>  
     <%}%>      
    </div>
    
    <div class="text-center pt-3 movemenu text-danger plantilla">  
        <% if (jugadoresbu!=null) {%> 
              <h2><strong>Plantilla del <%= session.getAttribute("nombre")%> </strong></h2>  
        <%}%>      
                
      
    </div>
    <div class="container">
 
        <div class="row justify-content-center mx-4 pt-3">
            <% if (jugadoresbu!=null) {%>      
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaequipobundes&equipo=<%=equipoSeleccionado.getId()%>&nombre=<%=equipoSeleccionado.getNombre() %>">Todos</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicionbu&posicion=Portero&equipoId=<%= equipoSeleccionado.getId() %>">Porteros</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicionbu&posicion=Defensa&equipoId=<%= equipoSeleccionado.getId() %>">Defensas</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicionbu&posicion=Centrocampista&equipoId=<%= equipoSeleccionado.getId() %>">Centrocampistas</a>
           <a class="btn loginbutton nav-link mx-3" class="active" href="Controller?op=vaposicionbu&posicion=Delantero&equipoId=<%= equipoSeleccionado.getId() %>">Delanteros</a>
          <%}%>  
           
        </div>
          
        <div class="row justify-content-center px-3">

           <% if (equipoSeleccionado != null) { %>
    <% if (jugadoresfiltradosbu != null) { %>
      <% for (Jugadorbu jugador : jugadoresfiltradosbu) { %>
        <div class="col-md-6 col-lg-4 p-3 movediv">
          <div class="card h-100 colorborde">
            <div class="card-body">
              <img src="<%=jugador.getFoto()%>" class="float-left w-50" alt="">
              <div class="text-right">
                <h3 class="ml-"><%=jugador.getNombre()%></h3>
                <h5>Edad: <%=jugador.getEdad()%></h5>
                <h5><%=jugador.getPosicion()%></h5>
                <p>Dorsal: <%=jugador.getDorsal()%></p>
                <p><%=jugador.getFechaNacimiento()%></p>
                <p><%=jugador.getPais()%></p>
              </div>
            </div>
          </div>
        </div>
      <% } %>
    <% } else if (jugadoresbu != null) { %>
      <% for (Jugadorbu jugador : jugadoresbu) { %>
        <div class="col-md-6 col-lg-4 p-3 movediv">
          <div class="card h-100 colorborde">
            <div class="card-body">
              <img src="<%=jugador.getFoto()%>" class="float-left w-50" alt="">
              <div class="text-right">
                <h3 class="ml-"><%=jugador.getNombre()%></h3>
                <h5>Edad: <%=jugador.getEdad()%></h5>
                <h5><%=jugador.getPosicion()%></h5>
                <p>Dorsal: <%=jugador.getDorsal()%></p>
                <p><%=jugador.getFechaNacimiento()%></p>
                <p><%=jugador.getPais()%></p>
              </div>
            </div>
          </div>
        </div>
      <% } %>
     <% } %>
     <% } %>
           
       

    </div>

    <div class="mt-4 ">
        <div class=" float-right mb-5 mr-4 ">
            <a class="twitter-timeline" data-lang="es" data-width="400" data-height="500" data-theme="dark" 
             href="https://twitter.com/ivanJua16758008?ref_src=twsrc%5Etfw">Tweets by ivanJua16758008</a> 
          <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

        </div>
       <% if (user!=null) {%>
        <!-- Este Div solo aparecera si estas logeado -->
        <div class=" float-left text-center ml-4 ">
            <h2 class="plantilla text-center"> <strong>¡Atrevete con el quiz!</strong></h2>
            <h2 class="text-danger text-center"> ¡Desactivado Temporalmente!</h2>
            <div id="contador" class="simply-countdown-inline">
            
            </div>
            <div class="mt-3">
                
         <div class="row disabled no-gutters">
                <div class="col-4">
                  <button id="boton1" onclick="desactivarBoton()" class="rounded buttonquizandinfo">1</button>
                </div>
                <div class="col-4">
                  <button class="rounded buttonquizandinfo">4</button>
                </div>
                <div class="col-4">
                  <button class="rounded buttonquizandinfo">7</button>
                </div>
                <div class="col-4 mt-3">
                  <button class="rounded buttonquizandinfo">2</button>
                </div>
                <div class="col-4 mt-3">
                  <button class="rounded buttonquizandinfo">5</button>
                </div>
                <div class="col-4 mt-3">
                  <button class="rounded buttonquizandinfo">8</button>
                </div>
                <div class="col-4 mt-3">
                  <button class="rounded buttonquizandinfo">3</button>
                </div>
                <div class="col-4 mt-3">
                  <button class="rounded buttonquizandinfo">6</button>
                </div>
                <div class="col-4 mt-3">
                  <button class="rounded buttonquizandinfo">9</button>
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
             
        <footer class="bg-dark p-3 text-center text-white d-flex align-items-center">
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
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Login & Register</h5>
                </div>
                <form action="Controller?op=loginbundes" method="post">
                    <div class="modal-body text-center">
                        <p>
                            <input type="text" name="nick" id="" placeholder="Usuario">
                        </p>
                        <p>
                            <input type="password" name="pass" id="" placeholder="Contraseña">
                        </p>


                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn loginbutton">Login & Register</button>
                        <button type="button" class="btn cancelbutton" data-dismiss="modal"> &times; Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal Quiz-->
    <div class="modal fade" id="modalquiz" tabindex="-1" role="dialog" aria-labelledby="modelTitleId"
        aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">La pregunta del dia: ¿Cual es el Primer nombre de Le Normand, Jugador de la
                        Real Sociedad?</h5>
                </div>
                <form action="Controller?op=varespuesta" method="post">
                    <div class="modal-body d-flex">
                        <div class="flex-fill quizbutton">
                            <button class="quizbutton" type="submit">Robin</button>
                            <button class="quizbutton" type="submit">Olivier</button>
                        </div>

                    </div>
                    <div class="modal-body d-flex">
                        <div class="flex-fill  quizbutton">
                            <button class="quizbutton" type="submit">Unai</button>
                            <button class="quizbutton" type="submit">Mathieu</button>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success sendbutton">Enviar Respuesta</button>
                        <button type="button" class="btn cancelbutton" data-dismiss="modal">Cancelar</button>
                    </div>
                </form>
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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

   <script type="text/JavaScript" src="JavaScript/vue.js"> </script>
        <script type="text/JavaScript" src="JavaScript/my.js"> </script>
    <script>
        const myApp = app.mount("#app");
        const myliga = liga.mount("#ligas");
    </script>

    <script src="JavaScript/simplyCountdown.min.js"></script>
    <script src="JavaScript/contador.js"></script>
</body>

</html>
