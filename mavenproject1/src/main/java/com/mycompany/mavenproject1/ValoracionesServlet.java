package com.mycompany.mavenproject1;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/valoraciones")
public class ValoracionesServlet extends HttpServlet {

    private final ValoracionDao dao = new ValoracionDao();

    // ── POST: crear valoración + datos del paciente ──────
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");

        JSONObject json = new JSONObject();

        // 1. Verificar sesión
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            res.setStatus(401);
            json.put("ok", false);
            json.put("mensaje", "Sesión no válida.");
            imprimir(res, json.toString());
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        try {
            // 2. Crear registro raíz en valoraciones
            Valoracion v = new Valoracion();
            v.setUsuarioId(usuario.getId());
            v.setCompletada(false);
            int valoracionId = dao.insertar(v);

            if (valoracionId == 0) {
                res.setStatus(500);
                json.put("ok", false);
                json.put("mensaje", "Error al crear la valoración.");
                imprimir(res, json.toString());
                return;
            }

            // 3. Armar PacienteDatos desde los parámetros del form
            PacienteDatos p = new PacienteDatos();
            p.setValoracionId(valoracionId);
            p.setNombre(req.getParameter("nombrePaciente"));

            String edadStr = req.getParameter("edadPaciente");
            if (edadStr != null && !edadStr.isBlank()) {
                try { p.setEdad(Integer.parseInt(edadStr.trim())); }
                catch (NumberFormatException ignored) {}
            }

            p.setGenero(req.getParameter("generoPaciente"));
            p.setLugarNacimiento(req.getParameter("lugarNacimiento"));
            p.setDomicilio(req.getParameter("domicilio"));
            p.setFechaIngreso(req.getParameter("fechaIngreso"));
            p.setReligion(req.getParameter("religion"));
            p.setEscolaridad(req.getParameter("escolaridad"));
            p.setEstadoCivil(req.getParameter("estadoCivil"));
            p.setOcupacion(req.getParameter("ocupacion"));
            p.setDependenciaInstitucion(req.getParameter("dependencia"));
            p.setCuandoAcudeMedico(req.getParameter("cuandoAcude"));
            p.setResponsable(req.getParameter("responsable"));

            String capaz = req.getParameter("capazDecisiones");
            if (capaz != null) p.setCapazDecisiones("Sí".equals(capaz));

            String lleva = req.getParameter("llevaTratamiento");
            if (lleva != null) p.setLlevaTratamiento("Sí".equals(lleva));

            // Servicios de salud → JSON array
            String[] servicios = req.getParameterValues("serviciosSalud");
            JSONArray arr = new JSONArray();
            if (servicios != null) {
                for (String s : servicios) arr.put(s);
            }
            p.setServiciosSalud(arr.toString());
            
            // 4. Armar Entorno desde los parámetros del formulario

entorno e = new entorno();
e.setValoracionId(valoracionId);

// Campos JSON
e.setTipoPiso(
    new JSONArray(req.getParameterValues("tipoPiso") != null ?
        req.getParameterValues("tipoPiso") : new String[0]
    ).toString()
);

e.setTipoPared(
    new JSONArray(req.getParameterValues("tipoPared") != null ?
        req.getParameterValues("tipoPared") : new String[0]
    ).toString()
);

e.setTipoTecho(
    new JSONArray(req.getParameterValues("tipoTecho") != null ?
        req.getParameterValues("tipoTecho") : new String[0]
    ).toString()
);

e.setTipoLuz(
    new JSONArray(req.getParameterValues("tipoLuz") != null ?
        req.getParameterValues("tipoLuz") : new String[0]
    ).toString()
);

e.setAbastecimientoAgua(
    new JSONArray(req.getParameterValues("abastecimientoAgua") != null ?
        req.getParameterValues("abastecimientoAgua") : new String[0]
    ).toString()
);

e.setPurificacionAgua(
    new JSONArray(req.getParameterValues("purificacionAgua") != null ?
        req.getParameterValues("purificacionAgua") : new String[0]
    ).toString()
);

e.setDrenaje(
    new JSONArray(req.getParameterValues("drenaje") != null ?
        req.getParameterValues("drenaje") : new String[0]
    ).toString()
);

e.setTratamientoBasura(
    new JSONArray(req.getParameterValues("tratamientoBasura") != null ?
        req.getParameterValues("tratamientoBasura") : new String[0]
    ).toString()
);

e.setFaunaNociva(
    new JSONArray(req.getParameterValues("faunaNociva") != null ?
        req.getParameterValues("faunaNociva") : new String[0]
    ).toString()
);

e.setAnimalesDomesticos(
    new JSONArray(req.getParameterValues("animalesDomesticos") != null ?
        req.getParameterValues("animalesDomesticos") : new String[0]
    ).toString()
);

// ENUMS

e.setNumAnimales(req.getParameter("numAnimales"));
e.setAnimalesVacunados(req.getParameter("animalesVacunados"));

// 5. Guardar entorno

dao.insertarEntorno(e);

            // 4. Guardar paciente_datos
            dao.insertarPacienteDatos(p);

            // 5. Responder con el ID para que el JS lo use al guardar secciones siguientes
            json.put("ok", true);
            json.put("valoracionId", valoracionId);

        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json.put("ok", false);
            json.put("mensaje", "Error interno: " + e.getMessage());
        }

        imprimir(res, json.toString());
    }

    // ── PUT: marcar valoración como completada ───────────
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            res.setStatus(401);
            imprimir(res, "{\"ok\":false}");
            return;
        }

        JSONObject json = new JSONObject();
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            try (java.io.BufferedReader br = req.getReader()) {
                while ((line = br.readLine()) != null) sb.append(line);
            }
            java.util.Map<String,String> params = parseParams(sb.toString());
            int valoracionId = Integer.parseInt(params.getOrDefault("valoracionId", "0"));

            if (valoracionId > 0) {
                dao.marcarCompletada(valoracionId);
                json.put("ok", true);
            } else {
                res.setStatus(400);
                json.put("ok", false);
                json.put("mensaje", "ID inválido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            json.put("ok", false);
        }
        imprimir(res, json.toString());
    }

    // ── Helpers ──────────────────────────────────────────
    private void imprimir(HttpServletResponse res, String texto) throws IOException {
        PrintWriter out = res.getWriter();
        out.print(texto);
        out.flush();
    }

    private java.util.Map<String, String> parseParams(String body) {
        java.util.Map<String, String> map = new java.util.LinkedHashMap<>();
        if (body == null || body.isBlank()) return map;
        for (String par : body.split("&")) {
            String[] kv = par.split("=", 2);
            if (kv.length == 2) {
                try {
                    map.put(
                        java.net.URLDecoder.decode(kv[0], "UTF-8"),
                        java.net.URLDecoder.decode(kv[1], "UTF-8")
                    );
                } catch (Exception ignored) {}
            }
        }
        return map;
    }
}