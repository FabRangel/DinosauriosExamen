<%-- 
    Document   : xmlDino
    Created on : 13 nov 2024, 20:37:42
    Author     : fgmrr
--%>
<%@page import="javax.xml.transform.dom.DOMSource"%>
<%@page import="Configuration.ConnectionBD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="javax.xml.parsers.*" %>
<%@ page import="javax.xml.transform.*" %>
<%@ page import="javax.xml.transform.stream.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles del Dinosaurio</title>
</head>
<body>
    <h1 style="text-align: center;">Detalles del Dinosaurio</h1>

    <%
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        
        ConnectionBD conexion = new ConnectionBD();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = conexion.getConnectionBD();
            String query = "SELECT id, nombre, especie, longitud, periodo FROM Dinosaurios WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String especie = resultSet.getString("especie");
                String longitud = resultSet.getString("longitud");
                String periodo = resultSet.getString("periodo");
    %>

    <h2 style="text-align: center;">XML de Dinosaurio</h2>
    <pre style="white-space: pre-wrap; word-wrap: break-word;">
        <%
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("Dino");
            document.appendChild(root);

            Element nombreElement = document.createElement("Nombre");
            nombreElement.appendChild(document.createTextNode(nombre));
            root.appendChild(nombreElement);

            Element especieElement = document.createElement("Especie");
            especieElement.appendChild(document.createTextNode(especie));
            root.appendChild(especieElement);

            Element longitudElement = document.createElement("Longitud");
            longitudElement.appendChild(document.createTextNode(longitud));
            root.appendChild(longitudElement);

            Element periodoElement = document.createElement("Periodo");
            periodoElement.appendChild(document.createTextNode(periodo));
            root.appendChild(periodoElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);
        %>
    </pre>

    <form action="/dinosaurios/xmlservlet" method="post">
        <input type="hidden" name="nombre" value="<%= nombre %>">
        <input type="hidden" name="especie" value="<%= especie %>">
        <input type="hidden" name="longitud" value="<%= longitud %>">
        <input type="hidden" name="periodo" value="<%= periodo %>">
        <button type="submit">Descargar XML</button>
    </form>

    <%
        } else {
            out.println("<p>No se encontró el dinosaurio con el ID proporcionado.</p>");
        }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error al obtener los datos: " + e.getMessage() + "</p>");
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

</body>
</html>