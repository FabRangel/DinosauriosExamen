package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Configuration.ConnectionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author fgmrr
 */
@WebServlet("/dinoservlet")
public class DinoServlet extends HttpServlet {
    ConnectionBD conexion = new ConnectionBD();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        double longitud = Double.parseDouble(request.getParameter("longitud"));
        String periodo = request.getParameter("periodo");

        ConnectionBD conexion = new ConnectionBD();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conexion.getConnectionBD();

            String query = "INSERT INTO Dinosaurios (nombre, especie, longitud, periodo) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, especie);
            ps.setDouble(3, longitud);
            ps.setString(4, periodo);

            int filasAfectadas = ps.executeUpdate();

            response.setContentType("text/html;charset=UTF-8");
            if (filasAfectadas > 0) {
               request.setAttribute("mensaje", "Registro exitoso.");
               request.getRequestDispatcher("/jsp/verDino.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "Error al registrar.");
                request.getRequestDispatcher("/jsp/verDino.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
