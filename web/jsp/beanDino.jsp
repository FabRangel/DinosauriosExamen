<%-- 
    Document   : beanDino.jsp
    Created on : 13 nov 2024, 20:11:26
    Author     : fgmrr
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Detalles del Dinosaurio</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 25px 0;
            text-align: left;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
        }
    </style>
</head>
<body>
    <h1>Detalles del Dinosaurio</h1>
    <table>
        <tr>
            <th>ID</th>
            <td>${dinosaurio.id}</td>
        </tr>
        <tr>
            <th>Nombre</th>
            <td>${dinosaurio.nombre}</td>
        </tr>
        <tr>
            <th>Especie</th>
            <td>${dinosaurio.especie}</td>
        </tr>
        <tr>
            <th>Longitud (m)</th>
            <td>${dinosaurio.longitud}</td>
        </tr>
        <tr>
            <th>Periodo</th>
            <td>${dinosaurio.periodo}</td>
        </tr>
    </table>
        <form action="beanservlet" method="post">
        <input type="hidden" name="id" value="${dinosaurio.id}" />
        <input type="submit" value="Atrás" name="atras" />
    </form>
</body>
</html>