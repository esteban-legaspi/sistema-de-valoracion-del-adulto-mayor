/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONObject;
 
import java.io.IOException;
import java.io.PrintWriter;
 
@WebServlet("/api/sesion")
public class SesionServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
 
        JSONObject json = new JSONObject();
        HttpSession session = req.getSession(false);
 
        if (session != null && session.getAttribute("usuario") != null) {
            Usuario u = (Usuario) session.getAttribute("usuario");
            json.put("activa",  true);
            json.put("rol",     u.getRol());
            json.put("nombre",  u.getNombre());
            json.put("id",      u.getId());
        } else {
            json.put("activa", false);
        }
 
        PrintWriter out = res.getWriter();
        out.print(json);
        out.flush();
    }
}