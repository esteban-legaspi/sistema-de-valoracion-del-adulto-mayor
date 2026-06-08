/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValoracionDao {

    public int insertar(Valoracion v)
            throws SQLException, ClassNotFoundException {

        String sql =
            "INSERT INTO valoraciones " +
            "(usuario_id, completada) " +
            "VALUES (?, ?)";

        try (
            Connection con = Dbconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )
        ) {

            ps.setInt(1, v.getUsuarioId());
            ps.setBoolean(2, v.isCompletada());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

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
