/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author fgmrr
 */
@WebServlet("/xmlservlet")
public class XMLServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        String longitud = request.getParameter("longitud");
        String periodo = request.getParameter("periodo");

        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=dinosaurio.xml");

        try {
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
            StreamResult result = new StreamResult(response.getOutputStream());
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error al generar el archivo XML: " + e.getMessage() + "</p>");
        }
    }
}