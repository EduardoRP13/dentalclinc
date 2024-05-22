/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.database;

/**
 *
 * @author pereiras-house
 */
import dentalclinc.modelo.Cita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private static final String INSERTAR_CITA_SQL = "INSERT INTO citas (nif_paciente, medico, dientes, hora, fecha, procedimiento) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_TODAS_LAS_CITAS_SQL = "SELECT * FROM citas";
    private static final String ELIMINAR_CITA_SQL = "DELETE FROM citas WHERE id = ?";
    
    public void insertarCita(Cita cita) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERTAR_CITA_SQL)) {
            statement.setString(1, cita.getNifPaciente());
            statement.setString(2, cita.getMedico());
            statement.setString(3, cita.getDientes());
            statement.setString(4, cita.getHora());
            statement.setString(5, cita.getFecha());
            statement.setString(6, cita.getProcedimiento());
            statement.executeUpdate();
            System.out.println("Cita insertada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar cita: " + e.getMessage());
        }
    }
    
    public List<Cita> obtenerTodasLasCitas() {
        List<Cita> citas = new ArrayList<>();
        try (Connection conn = new ConexionDB().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(OBTENER_TODAS_LAS_CITAS_SQL)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nifPaciente = resultSet.getString("nif_paciente");
                String medico = resultSet.getString("medico");
                String dientes = resultSet.getString("dientes");
                String hora = resultSet.getString("hora");
                String fecha = resultSet.getString("fecha");
                String procedimiento = resultSet.getString("procedimiento");
                citas.add(new Cita(id, nifPaciente, medico, dientes, hora, fecha, procedimiento));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    public void eliminarCita(int id) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(ELIMINAR_CITA_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Cita eliminada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


