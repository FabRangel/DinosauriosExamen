/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Bean.DinoBean;
import Configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fgmrr
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/beanservlet")
public class BeanServlet extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("atras") != null) {
            request.removeAttribute("dinosaurio");  
            response.sendRedirect("jsp/verDino.jsp");
            return;
        }

        ConnectionBD conexion = new ConnectionBD();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            conn = conexion.getConnectionBD();

            String query = "SELECT id, nombre, especie, longitud, periodo FROM Dinosaurios WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                DinoBean dino = new DinoBean();
                dino.setId(rs.getInt("id"));
                dino.setNombre(rs.getString("nombre"));
                dino.setEspecie(rs.getString("especie"));
                dino.setLongitud(rs.getDouble("longitud"));
                dino.setPeriodo(rs.getString("periodo"));

                request.setAttribute("dinosaurio", dino);

                request.getRequestDispatcher("/jsp/beanDino.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se encontró el dinosaurio.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error al obtener los datos: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}