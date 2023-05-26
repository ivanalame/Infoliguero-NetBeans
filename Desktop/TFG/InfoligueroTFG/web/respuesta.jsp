<%-- 
    Document   : respuesta
    Created on : 24-may-2023, 12:42:32
    Author     : Iván Juárez
--%>

<%@page import="entities.Respuesta"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Respuesta> respuestas = (List<Respuesta>) session.getAttribute("respuestas");
    %>
<div class="row justify-content-center px-3">
    <% for(Respuesta respuesta: respuestas){%>
        <div class="col-12">
        <div class="card p-2 h-100">
            <div class="row">
                <div class="modal-body d-flex">
                        <div class="flex-fill quizbutton">
                            <button class="quizbutton" type="text"><%=respuesta.getTexto()%></button>
                            <
                        </div>

                    </div>
            </div>
        </div>
    </div>
    <%} %>
    
</div>
