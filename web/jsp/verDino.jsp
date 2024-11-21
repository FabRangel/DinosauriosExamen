<%-- 
    Document   : verDino
    Created on : 13 nov 2024, 19:21:13
    Author     : fgmrr
--%>

<%@page import="Configuration.ConnectionBD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Dinosaurios</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        p{
            text-align: center;
        }
        th, td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            padding: 10px 15px;
            margin: 5px;
        }
    </style>
</head>
<body>
    <%
       String mensaje = (String) request.getAttribute("mensaje");
        %>
    <h1 style="text-align: center;">Registros de Dinosaurios</h1>
    <%
    if (mensaje != null && !mensaje.isEmpty()) {
        String color = "green"; 
        if (mensaje.contains("Error")) {
            color = "red"; 
        }
%>
        <p style="color:<%= color %>; text-align: center;"><%= mensaje %></p>
<%
    }
%>
    <table>
        <tr>
            <th>Nombre</th>
            <th>Especie</th>
            <th>Acciones</th>
        </tr>
        <%
            ConnectionBD conexion = new ConnectionBD();
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            
            try {
                connection = conexion.getConnectionBD();

                String query = "SELECT id, nombre, especie FROM Dinosaurios";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String especie = resultSet.getString("especie");
                    String nombre = resultSet.getString("nombre");
        %>
        <tr>
            <td><%= nombre %></td>
            <td><%= especie %></td>
            <td>
                <form action="/dinosaurios/beanservlet" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= id %>">
                    <button type="submit">Ver Bean</button>
                </form>
                <form action="/dinosaurios/jsp/xmlDino.jsp" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= id %>">
                    <button type="submit">Ver XML</button>
                </form>
            </td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<tr><td colspan='3'>Error al obtener los registros: " + e.getMessage() + "</td></tr>");
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </table>
</body>
</html>