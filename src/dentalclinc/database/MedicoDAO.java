/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.database;

/**
 *
 * @author pereiras-house
 */
import dentalclinc.modelo.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private static final String INSERTAR_MEDICO_SQL = "INSERT INTO medicos (nombre, apellidos, nif, direccion, telefono, genero, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_MEDICO_SQL = "UPDATE medicos SET nombre=?, apellidos=?, direccion=?, telefono=?, genero=?, fecha_nacimiento=? WHERE nif=?";
    private static final String ELIMINAR_MEDICO_SQL = "DELETE FROM medicos WHERE nif=?";
    private static final String SELECCIONAR_MEDICO_SQL = "SELECT * FROM medicos WHERE nif=?";
    
    public void insertarMedico(Medico medico) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERTAR_MEDICO_SQL)) {
            statement.setString(1, medico.getNombre());
            statement.setString(2, medico.getApellidos());
            statement.setString(3, medico.getNif());
            statement.setString(4, medico.getDireccion());
            statement.setString(5, medico.getTelefono());
            statement.setString(6, medico.getGenero());
            statement.setString(7, medico.getFechaNacimiento());
            statement.executeUpdate();
            System.out.println("Médico insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar médico: " + e.getMessage());
        }
    }
    
    public void actualizarMedico(Medico medico) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(ACTUALIZAR_MEDICO_SQL)) {
            statement.setString(1, medico.getNombre());
            statement.setString(2, medico.getApellidos());
            statement.setString(3, medico.getDireccion());
            statement.setString(4, medico.getTelefono());
            statement.setString(5, medico.getGenero());
            statement.setString(6, medico.getFechaNacimiento());
            statement.setString(7, medico.getNif());
            
            statement.executeUpdate();
            System.out.println("Médico actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar médico: " + e.getMessage());
        }
    }
    
    public void eliminarMedico(String nif) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(ELIMINAR_MEDICO_SQL)) {
            statement.setString(1, nif);
            statement.executeUpdate();
            System.out.println("Médico eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar médico: " + e.getMessage());
        }
    }
    
    public Medico seleccionarMedico(String nif) {
        Medico medico = null;
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECCIONAR_MEDICO_SQL)) {
            statement.setString(1, nif);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String apellidos = resultSet.getString("apellidos");
                    String direccion = resultSet.getString("direccion");
                    String telefono = resultSet.getString("telefono");
                    String genero = resultSet.getString("genero");
                    String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                    medico = new Medico(nombre, apellidos, nif, direccion, telefono, genero, fechaNacimiento);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar médico: " + e.getMessage());
        }
        return medico;
    }

    public List<Medico> obtenerTodosLosMedicos() {
        List<Medico> listaMedicos = new ArrayList<>();
        try (Connection conn = new ConexionDB().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM medicos")) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String nif = resultSet.getString("nif");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String genero = resultSet.getString("genero");
                String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                Medico medico = new Medico(nombre, apellidos, nif, direccion, telefono, genero, fechaNacimiento);
                listaMedicos.add(medico);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener médicos: " + e.getMessage());
        }
        return listaMedicos;
    }
}

