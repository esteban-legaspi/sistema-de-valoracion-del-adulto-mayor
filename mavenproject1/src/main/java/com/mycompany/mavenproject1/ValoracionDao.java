package com.mycompany.mavenproject1;

import java.sql.*;

public class ValoracionDao {

    // ── Crea el registro raíz en valoraciones ────────────
    public int insertar(Valoracion v) {
        String sql = "INSERT INTO valoraciones (usuario_id, completada) VALUES (?, ?)";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, v.getUsuarioId());
            ps.setBoolean(2, v.isCompletada());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ── Marca una valoración como completada ─────────────
    public void marcarCompletada(int valoracionId) {
        String sql = "UPDATE valoraciones SET completada = 1 WHERE id = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, valoracionId);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ── Inserta o actualiza paciente_datos ───────────────
    public void insertarPacienteDatos(PacienteDatos p) {
        // Usa INSERT ... ON DUPLICATE KEY UPDATE para soportar guardado parcial
        String sql =
            "INSERT INTO paciente_datos " +
            "(valoracion_id, nombre, edad, genero, lugar_nacimiento, domicilio, " +
            " fecha_ingreso, religion, escolaridad, estado_civil, ocupacion, " +
            " dependencia_institucion, servicios_salud, cuando_acude_medico, " +
            " capaz_decisiones, responsable, lleva_tratamiento) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE " +
            " nombre=VALUES(nombre), edad=VALUES(edad), genero=VALUES(genero), " +
            " lugar_nacimiento=VALUES(lugar_nacimiento), domicilio=VALUES(domicilio), " +
            " fecha_ingreso=VALUES(fecha_ingreso), religion=VALUES(religion), " +
            " escolaridad=VALUES(escolaridad), estado_civil=VALUES(estado_civil), " +
            " ocupacion=VALUES(ocupacion), dependencia_institucion=VALUES(dependencia_institucion), " +
            " servicios_salud=VALUES(servicios_salud), cuando_acude_medico=VALUES(cuando_acude_medico), " +
            " capaz_decisiones=VALUES(capaz_decisiones), responsable=VALUES(responsable), " +
            " lleva_tratamiento=VALUES(lleva_tratamiento)";

        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getValoracionId());
            ps.setString(2, p.getNombre());

            if (p.getEdad() != null) ps.setInt(3, p.getEdad());
            else                     ps.setNull(3, Types.TINYINT);

            ps.setString(4, p.getGenero());
            ps.setString(5, p.getLugarNacimiento());
            ps.setString(6, p.getDomicilio());

            if (p.getFechaIngreso() != null && !p.getFechaIngreso().isEmpty())
                ps.setDate(7, Date.valueOf(p.getFechaIngreso()));
            else
                ps.setNull(7, Types.DATE);

            ps.setString(8,  p.getReligion());
            ps.setString(9,  p.getEscolaridad());
            ps.setString(10, p.getEstadoCivil());
            ps.setString(11, p.getOcupacion());
            ps.setString(12, p.getDependenciaInstitucion());
            ps.setString(13, p.getServiciosSalud());
            ps.setString(14, p.getCuandoAcudeMedico());

            if (p.getCapazDecisiones() != null) ps.setBoolean(15, p.getCapazDecisiones());
            else                                ps.setNull(15, Types.TINYINT);

            ps.setString(16, p.getResponsable());

            if (p.getLlevaTratamiento() != null) ps.setBoolean(17, p.getLlevaTratamiento());
            else                                 ps.setNull(17, Types.TINYINT);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ── Mapeo interno ─────────────────────────────────────
    private Valoracion mapear(ResultSet rs) throws SQLException {
        Valoracion v = new Valoracion();
        v.setId(rs.getInt("id"));
        v.setUsuarioId(rs.getInt("usuario_id"));
        v.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        v.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
        v.setCompletada(rs.getBoolean("completada"));
        return v;
    }
}
