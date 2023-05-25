package controller;

import entities.Equipo;
import entities.Equipop;
import entities.Jugador;
import entities.Jugadorp;
import entities.Pregunta;
import entities.Respuesta;
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
          Usuario user = null;
          Equipo equiposelected;
          Equipop equipopselected;
          List <Jugador> jugadores ;
           List <Jugadorp> jugadoresp ;
           List <Pregunta> preguntas ;
           Pregunta preguntaselected;
            List <Respuesta> respuestas ;
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

            request.getRequestDispatcher("home.jsp").forward(request, response);
              
            
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
             request.getRequestDispatcher("home.jsp").forward(request, response);
        }else if (op.equals("logout")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
            //Recargamos la home.jsp
            request.getRequestDispatcher("home.jsp").forward(request, response);
         }else if (op.equals("logoutpremier")) {
            //Para salir de la sesion hay que borrar el atributo de la sesion de usuario
             session.removeAttribute("user");
            //Recargamos la home.jsp
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
            
         } else if (op.equals("vapregunta")) {
           String idpregunta = request.getParameter("pregunta");
           q = em.createQuery("SELECT p FROM Pregunta p WHERE p.id = :id");
           q.setParameter("id", Integer.parseInt(idpregunta));
            Pregunta preguntaseleccionada = (Pregunta)q.getSingleResult();
             
             session.setAttribute("preguntaseleccionada", preguntaseleccionada);
            
             
            request.getRequestDispatcher("home.jsp").forward(request, response);    
         } else if (op.equals("vapremier")) {
           q =  em.createNamedQuery("Equipop.findAll");   //me bajo todos los equipos
           equipospremier  = (List <Equipop>)q.getResultList();   //esto me devuelve un list por eso lo declaro como List, creo arriba el list de equipos 
            session.setAttribute("equiposp", equipospremier);  
            
            session.removeAttribute("nombre");
            session.removeAttribute("jugadores");
            session.removeAttribute("jugadoresfiltrados");
             
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
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
          } else if (op.equals("vaequipopremier")) {
            String idequipo = request.getParameter("equipo");
            //lo busco en la bbdd
            equipopselected = em.find(Equipop.class, Integer.parseInt(idequipo));
            jugadoresp = equipopselected.getJugadorpList();
            
            session.setAttribute("jugadoresp", jugadoresp);
            
            session.setAttribute("equipopselected", equipopselected);  //ver
            session.setAttribute("nombre", equipopselected.getNombre()); // agrego el nombre del equipo
            
              session.removeAttribute("jugadoresfiltrados");
             
            request.getRequestDispatcher("Premier.jsp").forward(request, response);
            
            
           
        }else if (op.equals("allrespuestas")) {
 
            String idpregunta = request.getParameter("idpregunta");         //esto es lo que mando desde el myjs
            preguntaselected = em.find(Pregunta.class, Integer.parseInt(idpregunta));              //dentro del actor (Person) ya tengo la list de sus peliculas 
                
           session.setAttribute("respuestas", preguntaselected.getRespuestaList());     //le paso a la session la lista de peliculas bajo el nombre de movies y lo pintamos en otra jsp 
            
            request.getRequestDispatcher("respuesta.jsp").forward(request, response);
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
