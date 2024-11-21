<%-- 
    Document   : agregarDino.jsp
    Created on : 13 nov 2024, 19:12:46
    Author     : fgmrr
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Dinosaurio</title>
</head>
<body>
    <h1>Agregar Dinosaurio</h1>
    <form action="/dinosaurios/dinoservlet" method="post" accept-charset="UTF-8">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>

        <label for="especie">Especie:</label>
        <input type="text" id="especie" name="especie" required><br><br>

        <label for="longitud">Longitud (en metros):</label>
        <input type="number" id="longitud" name="longitud" step="0.1" min="0.1" required><br><br>

        <label for="periodo">Periodo:</label>
        <input type="text" id="periodo" name="periodo" pattern="^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$" required><br><br>
 
        <button type="submit">Agregar Dinosaurio</button>
    </form>
</body>
</html>