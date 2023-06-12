package controller;

import entities.Equipo;
import entities.Equipobu;
import entities.Equipofr;
import entities.Equipoit;
import entities.Equipop;
import entities.Jugador;
import entities.Jugadorbu;
import entities.Jugadorfr;
import entities.Jugadorit;
import entities.Jugadorp;
import entities.Pregunta;
import entities.Preguntap;
import entities.Respuesta;
import entities.Respuestap;
import entities.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import util.JPAUtil;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        /*
		 * Crear el singleton con
         */

         Query q;
         List <Equipo> equipos;
         List <Equipop> equipospremier;
         List <Equipoit> equipositalia;
         List <Equipobu> equiposbundes;
         List <Equipofr> equiposfrancia;
         
          Usuario user = null;
          
          Equipo equiposelected;
          Equipop equipopselected;
          Equipoit equipoitselected; 
          Equipobu equipobuselected;
          Equipofr equipofrselected;
          
          List <Jugador> jugadores ;
          List <Jugadorp> jugadoresp ;
          List <Jugadorit> jugadoresit;
          List <Jugadorbu> jugadoresbu;
          List <Jugadorfr> jugadoresfr;
           
          List <Pregunta> preguntas ;
          Pregunta preguntaselected;
            Preguntap       preguntapselected;
          List <Respuesta> respuestas; 
           Respuesta respuestaselected;
           Respuestap respuestapselecetd;
          EntityTransaction t;
         
        EntityManager em = (EntityManager) session.getAttribute("em");
        if (em == null) {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            session.setAttribute("em", em);
        }

        String op = request.getParameter("op");
        if (op.equals("inicio")) {
          q =  em.createNamedQuery("Equipo.findAll");   //me bajo todos los equipos
           equipos  = (List <Equipo>)q.getResultList();   //esto me devuelve un list por eso lo declaro como List, creo arriba el list de equipos 
            session.setAttribute("equipos", equipos);        
            
            session.removeAttribute("nombre");
             session.removeAttribute("jugadores");
            session.removeAttribute("jugadoresfiltrados");
            session.removeAttribute("equiposelected");         
            session.removeAttribute("jugadoresfiltradosp");
            
            session.removeAttribute("escorrecta");
            
            
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if (op.equals("vaequipo")) {
            String idequipo = request.getParameter("equipo");
            //lo busco en la bbdd
            equiposelected = em.find(Equipo.class, Integer.parseInt(idequipo));
            jugadores = equiposelected.getJugadorList();
            
            session.setAttribute("jugadores", jugadores);
            
            session.setAttribute("equiposelected", equiposelected);  //ver
            session.setAttribute("nombre", equiposelected.getNombre()); // agrego el nombre del equipo
            
              session.removeAttribute("jugadoresfiltrados");
              session.removeAttribute("jugadoresfiltradosp");
              session.removeAttribute("jugadoresfiltradosit");
               session.removeAttribute("preguntaseleccionada");
              session.removeAttribute("escorrecta");
             
            request.getRequestDispatcher("home.jsp").forward(request, response);
           
        } else if (op.equals("vaposicion")) { 
             List <Jugador> jugadoresfiltrados ;
             //posicion contiene Portero, por ejemplo
            String posicion = request.getParameter("posicion");
             String idequipo = request.getParameter("equipoId");
             
           q = em.createQuery("SELECT j FROM Jugador j WHERE j.posicion = :posicion AND j.idEquipo.id = :equipoId");          
            
          
            q.setParameter("posicion", posicion);
            q.setParameter("equipoId", Integer.parseInt(idequipo));
            jugadoresfiltrados = (List <Jugador>)q.getResultList();   
            
            session.setAttribute("jugadoresfiltrados", jugadoresfiltrados);   
            session.setAttribute("equipo", idequipo);
            session.removeAttribute("escorrecta");

            request.getRequestDispatcher("home.jsp").forward(request, response);
            
           } else if (op.equals("vaposicionp")) { 
             List <Jugadorp> jugadoresfiltradosp ;
             //posicion contiene Portero, por ejemplo
            String posicion = request.getParameter("posicion");
             String idequipo = request.getParameter("equipoId");
             
           q = em.createQuery("SELECT j FROM Jugadorp j WHERE j.posicion = :posicion AND j.idEquipo.id = :equipoId" , Jugadorp.class);          
            
          
            q.setParameter("posicion", posicion);
            q.setParameter("equipoId", Integer.parseInt(idequipo));
            jugadoresfiltradosp = (List <Jugadorp>)q.getResultList();   
            
            session.setAttribute("jugadoresfiltradosp", jugadoresfiltradosp);   
            session.setAttribute("equipo", idequipo);
            
             session.removeAttribute("escorrecta");
        

            request.getRequestDispatcher("Premier.jsp").forward(request, response);
              
           } else if (op.equals("vaposicionit")) { 
             List <Jugadorit> jugadoresfiltradosit ;
             //posicion contiene Portero, por ejemplo
            String posicion = request.getParameter("posicion");
             String idequipo = request.getParameter("equipoId");
             
           q = em.createQuery("SELECT j FROM Jugadorit j WHERE j.posicion = :posicion AND j.idEquipo.id = :equipoId" , Jugadorit.class);          
            
          
            q.setParameter("posicion", posicion);
            q.setParameter("equipoId", Integer.parseInt(idequipo));
            jugadoresfiltradosit = (List <Jugadorit>)q.getResultList();   
            
            session.setAttribute("jugadoresfiltradosit", jugadoresfiltradosit);   
            session.setAttribute("equipo", idequipo);

            request.getRequestDispatcher("italia.jsp").forward(request, response);
            
           } else if (op.equals("vaposicionbu")) { 
             List <Jugadorbu> jugadoresfiltradosbu ;
             //posicion contiene Portero, por ejemplo
            String posicion = request.getParameter("posicion");
             String idequipo = request.getParameter("equipoId");
             
           q = em.createQuery("SELECT j FROM Jugadorbu j WHERE j.posicion = :posicion AND j.idEquipo.id = :equipoId" , Jugadorbu.class);          
            
          
            q.setParameter("posicion", posicion);
            q.setParameter("equipoId", Integer.parseInt(idequipo));
            jugadoresfiltradosbu = (List <Jugadorbu>)q.getResultList();   
            
            session.setAttribute("jugadoresfiltradosbu", jugadoresfiltradosbu);   
            session.setAttribute("equipo", idequipo);

            request.getRequestDispatcher("bundes.jsp").forward(request, response);
            
            } else if (op.equals("vaposicionfr")) { 
             List <Jugadorfr> jugadoresfiltradosfr ;
             //posicion contiene Portero, por ejemplo
            String posicion = request.getParameter("posicion");
             String idequipo = request.getParameter("equipoId");
             
           q = em.createQuery("SELECT j FROM Jugadorfr j WHERE j.posicion = :posicion AND j.idEquipo.id = :equipoId" , Jugadorfr.class);          
            
          
            q.setParameter("posicion", posicion);
            q.setParameter("equipoId", Integer.parseInt(idequipo));
            jugadoresfiltradosfr = (List <Jugadorfr>)q.getResultList();   
            
            session.setAttribute("jugadoresfiltradosfr", jugadoresfiltradosfr);   
            session.setAttribute("equipo", idequipo);

            request.getRequestDispatcher("francia.jsp").forward(request, response);
               
        } else if (op.equals("login")) {
            
             String nick = request.getParameter("nick");     //aqui paso lo que me pasa el formulario 
             String pass = request.getParameter("pass");
             
             q = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = '"+nick+"' and u.pass ='"+pass+"'");
              try {
                user = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
            }
                     
           
            if (user == null) {                //si no encuetrno el usuarios, lo creo 
                  user = new Usuario(1);
                  user.setNick(nick);
 		  user.setPass(pass);
                
                //con estas lineas hacemos el insert, para inserta el dato en la base de datos 
                  t = em.getTransaction(); //lo inserto en la  base de datos
                  t.begin();
                  em.persist(user);          
                  t.commit();       
            }      
            
            
             session.setAttribute("user", user);
             session.removeAttribute("escorrecta");
             request.getRequestDispatcher("home.jsp").forward(request, response);
        }else if (op.equals("logout")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
             session.removeAttribute("escorrecta");
            //Recargamos la home.jsp
            request.getRequestDispatcher("home.jsp").forward(request, response);
         }else if (op.equals("logoutpremier")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
            //Recargamos la home.jsp
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
         }else if (op.equals("logoutitalia")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
            //Recargamos la home.jsp
            request.getRequestDispatcher("italia.jsp").forward(request, response);
         }else if (op.equals("logoutbundes")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
            //Recargamos la home.jsp
            request.getRequestDispatcher("bundes.jsp").forward(request, response);
            
         }else if (op.equals("logoutfrancia")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
            //Recargamos la home.jsp
            request.getRequestDispatcher("francia.jsp").forward(request, response);
         } else if (op.equals("vapregunta")) {
           String idpregunta = request.getParameter("pregunta");
           q = em.createQuery("SELECT p FROM Pregunta p WHERE p.id = :id");
           q.setParameter("id", Integer.parseInt(idpregunta));
            Pregunta preguntaseleccionada = (Pregunta)q.getSingleResult();
             
             session.setAttribute("preguntaseleccionada", preguntaseleccionada);
            
             session.removeAttribute("escorrecta");
            request.getRequestDispatcher("home.jsp").forward(request, response); 
            
          } else if (op.equals("vapreguntap")) {
           String idpregunta = request.getParameter("pregunta");
           q = em.createQuery("SELECT p FROM Preguntap p WHERE p.id = :id");
           q.setParameter("id", Integer.parseInt(idpregunta));
            Preguntap preguntapseleccionada = (Preguntap)q.getSingleResult();
             
             session.setAttribute("preguntapseleccionada", preguntapseleccionada);
            
             session.removeAttribute("escorrecta");
            request.getRequestDispatcher("Premier.jsp").forward(request, response);    
         } else if (op.equals("vapremier")) {
           q =  em.createNamedQuery("Equipop.findAll");   //me bajo todos los equipos
           equipospremier  = (List <Equipop>)q.getResultList();   //esto me devuelve un list por eso lo declaro como List, creo arriba el list de equipos 
            session.setAttribute("equiposp", equipospremier);  
            
             session.removeAttribute("nombre");
            session.removeAttribute("jugadores");
            session.removeAttribute("jugadoresp");
            session.removeAttribute("jugadoresit");
            session.removeAttribute("jugadoresbu");
            session.removeAttribute("jugadoresfr");

            session.removeAttribute("jugadoresfiltrados");
            session.removeAttribute("jugadoresfiltradosp");
            session.removeAttribute("jugadoresfiltradosit");
            session.removeAttribute("jugadoresfiltradosbu");
            session.removeAttribute("jugadoresfiltradosfr");
            session.removeAttribute("preguntaseleccionada");
            session.removeAttribute("escorrecta");
             
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
          } else if (op.equals("vaitalia")) {
           q =  em.createNamedQuery("Equipoit.findAll");   //me bajo todos los equipos
           equipositalia  = (List <Equipoit>)q.getResultList();   //esto me devuelve un list por eso lo declaro como List, creo arriba el list de equipos 
            session.setAttribute("equiposit", equipositalia);  
            
            session.removeAttribute("nombre");
            session.removeAttribute("jugadores");
            session.removeAttribute("jugadoresp");
            session.removeAttribute("jugadoresit");
            session.removeAttribute("jugadoresbu");
            session.removeAttribute("jugadoresfr");

            session.removeAttribute("jugadoresfiltrados");
            session.removeAttribute("jugadoresfiltradosp");
            session.removeAttribute("jugadoresfiltradosit");
            session.removeAttribute("jugadoresfiltradosbu");
            session.removeAttribute("jugadoresfiltradosfr");
            session.removeAttribute("preguntaseleccionada");
              session.removeAttribute("escorrecta");
             
            request.getRequestDispatcher("italia.jsp").forward(request, response);
          } else if (op.equals("vabundes")) {
           q =  em.createNamedQuery("Equipobu.findAll");   //me bajo todos los equipos
           equiposbundes  = (List <Equipobu>)q.getResultList();   //esto me devuelve un list por eso lo declaro como List, creo arriba el list de equipos 
            session.setAttribute("equiposbu", equiposbundes);  
            
            session.removeAttribute("nombre");
            session.removeAttribute("jugadores");
            session.removeAttribute("jugadoresp");
            session.removeAttribute("jugadoresit");
            session.removeAttribute("jugadoresbu");
            session.removeAttribute("jugadoresfr");

            session.removeAttribute("jugadoresfiltrados");
            session.removeAttribute("jugadoresfiltradosp");
            session.removeAttribute("jugadoresfiltradosit");
            session.removeAttribute("jugadoresfiltradosbu");
            session.removeAttribute("jugadoresfiltradosfr");
            session.removeAttribute("preguntaseleccionada");
              session.removeAttribute("escorrecta");
             
            request.getRequestDispatcher("bundes.jsp").forward(request, response);
          } else if (op.equals("vafrancia")) {
           q =  em.createNamedQuery("Equipofr.findAll");   //me bajo todos los equipos
           equiposfrancia  = (List <Equipofr>)q.getResultList();   //esto me devuelve un list por eso lo declaro como List, creo arriba el list de equipos 
            session.setAttribute("equiposfr", equiposfrancia);  
            
            session.removeAttribute("nombre");
            session.removeAttribute("jugadores");
            session.removeAttribute("jugadoresp");
            session.removeAttribute("jugadoresit");
            session.removeAttribute("jugadoresbu");
            session.removeAttribute("jugadoresfr");

            session.removeAttribute("jugadoresfiltrados");
            session.removeAttribute("jugadoresfiltradosp");
            session.removeAttribute("jugadoresfiltradosit");
            session.removeAttribute("jugadoresfiltradosbu");
            session.removeAttribute("jugadoresfiltradosfr");
            session.removeAttribute("preguntaseleccionada");
            session.removeAttribute("escorrecta");
        
             
            request.getRequestDispatcher("francia.jsp").forward(request, response);
           
         }else if (op.equals("loginpremier")) {
            
             String nick = request.getParameter("nick");     //aqui paso lo que me pasa el formulario 
             String pass = request.getParameter("pass");
             
             q = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = '"+nick+"' and u.pass ='"+pass+"'");
              try {
                user = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
            }
                     
           
            if (user == null) {                //si no encuetrno el usuarios, lo creo 
                  user = new Usuario(1);
                  user.setNick(nick);
 		  user.setPass(pass);
                
                //con estas lineas hacemos el insert, para inserta el dato en la base de datos 
                  t = em.getTransaction(); //lo inserto en la  base de datos
                  t.begin();
                  em.persist(user);          
                  t.commit();       
            }      
            
            
             session.setAttribute("user", user);
             request.getRequestDispatcher("Premier.jsp").forward(request, response);
             }else if (op.equals("loginitalia")) {
            
             String nick = request.getParameter("nick");     //aqui paso lo que me pasa el formulario 
             String pass = request.getParameter("pass");
             
             q = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = '"+nick+"' and u.pass ='"+pass+"'");
              try {
                user = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
            }
                     
           
            if (user == null) {                //si no encuetrno el usuarios, lo creo 
                  user = new Usuario(1);
                  user.setNick(nick);
 		  user.setPass(pass);
                
                //con estas lineas hacemos el insert, para inserta el dato en la base de datos 
                  t = em.getTransaction(); //lo inserto en la  base de datos
                  t.begin();
                  em.persist(user);          
                  t.commit();       
            }      
            
            
             session.setAttribute("user", user);
             request.getRequestDispatcher("italia.jsp").forward(request, response);
           
          }else if (op.equals("loginbundes")) {
            
             String nick = request.getParameter("nick");     //aqui paso lo que me pasa el formulario 
             String pass = request.getParameter("pass");
             
             q = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = '"+nick+"' and u.pass ='"+pass+"'");
              try {
                user = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
            }
                     
           
            if (user == null) {                //si no encuetrno el usuarios, lo creo 
                  user = new Usuario(1);
                  user.setNick(nick);
 		  user.setPass(pass);
                
                //con estas lineas hacemos el insert, para inserta el dato en la base de datos 
                  t = em.getTransaction(); //lo inserto en la  base de datos
                  t.begin();
                  em.persist(user);          
                  t.commit();       
            }      
            
            
             session.setAttribute("user", user);
             request.getRequestDispatcher("bundes.jsp").forward(request, response);
             
          }else if (op.equals("loginfrancia")) {
            
             String nick = request.getParameter("nick");     //aqui paso lo que me pasa el formulario 
             String pass = request.getParameter("pass");
             
             q = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = '"+nick+"' and u.pass ='"+pass+"'");
              try {
                user = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
            }
                     
           
            if (user == null) {                //si no encuetrno el usuarios, lo creo 
                  user = new Usuario(1);
                  user.setNick(nick);
 		  user.setPass(pass);
                
                //con estas lineas hacemos el insert, para inserta el dato en la base de datos 
                  t = em.getTransaction(); //lo inserto en la  base de datos
                  t.begin();
                  em.persist(user);          
                  t.commit();       
            }      
            
            
             session.setAttribute("user", user);
             request.getRequestDispatcher("francia.jsp").forward(request, response);
          } else if (op.equals("vaequipopremier")) {
            String idequipo = request.getParameter("equipo");
            //lo busco en la bbdd
            equipopselected = em.find(Equipop.class, Integer.parseInt(idequipo));
            jugadoresp = equipopselected.getJugadorpList();
            
            session.setAttribute("jugadoresp", jugadoresp);
            
            session.setAttribute("equipopselected", equipopselected);  //ver
            session.setAttribute("nombre", equipopselected.getNombre()); // agrego el nombre del equipo
            
              session.removeAttribute("jugadoresfiltrados");
              session.removeAttribute("jugadoresfiltradosp");
               session.removeAttribute("escorrecta");
             
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
            
          } else if (op.equals("vaequipoitalia")) {
            String idequipo = request.getParameter("equipo");
            //lo busco en la bbdd
            equipoitselected = em.find(Equipoit.class, Integer.parseInt(idequipo));
            jugadoresit = equipoitselected.getJugadoritList();
            
            session.setAttribute("jugadoresit", jugadoresit);
            
            session.setAttribute("equipoitselected", equipoitselected);  //ver
            session.setAttribute("nombre", equipoitselected.getNombre()); // agrego el nombre del equipo
            
              session.removeAttribute("jugadoresfiltrados");
              session.removeAttribute("jugadoresfiltradosit");
             
            request.getRequestDispatcher("italia.jsp").forward(request, response);
           } else if (op.equals("vaequipobundes")) {
            String idequipo = request.getParameter("equipo");
            //lo busco en la bbdd
            equipobuselected = em.find(Equipobu.class, Integer.parseInt(idequipo));
            jugadoresbu = equipobuselected.getJugadorbuList();
            
            session.setAttribute("jugadoresbu", jugadoresbu);
            
            session.setAttribute("equipobuselected", equipobuselected);  //ver
            session.setAttribute("nombre", equipobuselected.getNombre()); // agrego el nombre del equipo
            
             session.removeAttribute("jugadoresfiltrados");
             session.removeAttribute("jugadoresfiltradosbu");
             
            request.getRequestDispatcher("bundes.jsp").forward(request, response);  
           } else if (op.equals("vaequipofrancia")) {
            String idequipo = request.getParameter("equipo");
            //lo busco en la bbdd
            equipofrselected = em.find(Equipofr.class, Integer.parseInt(idequipo));
            jugadoresfr = equipofrselected.getJugadorfrList();
            
            session.setAttribute("jugadoresfr", jugadoresfr);
            
            session.setAttribute("equipofrselected", equipofrselected);  //ver
            session.setAttribute("nombre", equipofrselected.getNombre()); // agrego el nombre del equipo
            
            session.removeAttribute("jugadoresfiltrados");
            session.removeAttribute("jugadoresfiltradosfr");
             
            request.getRequestDispatcher("francia.jsp").forward(request, response);     
           
        }else if (op.equals("allrespuestas")) {
 
            String idpregunta = request.getParameter("idpregunta");         //esto es lo que mando desde el myjs
            preguntaselected = em.find(Pregunta.class, Integer.parseInt(idpregunta));              // 
                
           session.setAttribute("respuestas", preguntaselected.getRespuestaList());
             
            
            request.getRequestDispatcher("respuesta.jsp").forward(request, response);
            
         }else if (op.equals("allrespuestasp")) {
 
            String idpregunta = request.getParameter("idpregunta");         //esto es lo que mando desde el myjs
            preguntapselected = em.find(Preguntap.class, Integer.parseInt(idpregunta));              // 
                
           session.setAttribute("respuestas", preguntapselected.getRespuestapList());
             
            
            request.getRequestDispatcher("respuestap.jsp").forward(request, response);
        
        }else if (op.equals("varespuesta")) {
 
            String idrespuesta = request.getParameter("respuesta");         //esto es lo que mando desde el myjs
            respuestaselected = em.find(Respuesta.class, Integer.parseInt(idrespuesta));        
                
           session.setAttribute("escorrecta", respuestaselected);

            
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }else if (op.equals("varespuestap")) {
 
            String idrespuesta = request.getParameter("respuesta");         //esto es lo que mando desde el myjs
            respuestapselecetd = em.find(Respuestap.class, Integer.parseInt(idrespuesta));        
                
           session.setAttribute("escorrecta", respuestapselecetd);

            
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
        }

        
        
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
