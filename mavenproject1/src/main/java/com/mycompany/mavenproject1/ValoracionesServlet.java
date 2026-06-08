/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject1;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/valoraciones")
public class ValoracionesServlet extends HttpServlet {

    private final ValoracionDao dao = new ValoracionDao();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {

        try {

            res.setContentType("application/json;charset=UTF-8");
            req.setCharacterEncoding("UTF-8");

            HttpSession s = req.getSession(false);

            if (s == null) {
                res.setStatus(401);
                return;
            }

            Usuario usuario =
                (Usuario) s.getAttribute("usuario");

            if (usuario == null) {
                res.setStatus(401);
                return;
            }

            Valoracion v = new Valoracion();

            v.setUsuarioId(usuario.getId());
            v.setCompletada(false);

            int idGenerado = dao.insertar(v);

            JSONObject json = new JSONObject();
            json.put("ok", true);
            json.put("id", idGenerado);

            imprimir(res, json.toString());

        } catch (SQLException | ClassNotFoundException e) {

            res.setStatus(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            e.printStackTrace();
        }
    }

    private void imprimir(
            HttpServletResponse res,
            String texto)
            throws IOException {

        PrintWriter out = res.getWriter();

        out.print(texto);
        out.flush();
    }
}