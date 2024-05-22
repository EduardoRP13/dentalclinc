/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.database;

import dentalclinc.modelo.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pereiras-house
 * Clase para gestionar las operaciones de base de datos relacionadas con los pacientes
 */
public class PacienteDAO {
    // Consultas SQL para operaciones CRUD
    private static final String INSERTAR_PACIENTE_SQL = "INSERT INTO pacientes (nombre, apellidos, nif, direccion, telefono, genero, alergia, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_PACIENTE_SQL = "UPDATE pacientes SET nombre=?, apellidos=?, direccion=?, telefono=?, genero=?, alergia=?, fecha_nacimiento=? WHERE nif=?";
    private static final String ELIMINAR_PACIENTE_SQL = "DELETE FROM pacientes WHERE nif=?";
    private static final String SELECCIONAR_PACIENTE_SQL = "SELECT * FROM pacientes WHERE nif=?";
    
    // Método para insertar un nuevo paciente en la base de datos
    public void insertarPaciente(Paciente paciente) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERTAR_PACIENTE_SQL)) {
            // Establecer los valores de los parámetros en la consulta
            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellidos());
            statement.setString(3, paciente.getNif());
            statement.setString(4, paciente.getDireccion());
            statement.setString(5, paciente.getTelefono());
            statement.setString(6, paciente.getGenero());
            statement.setString(7, paciente.getAlergia());
            statement.setString(8, paciente.getFechaNacimiento());
            // Ejecutar la consulta
            statement.executeUpdate();
            // Formatear la fecha de nacimiento al formato esperado por mariadb
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaNacimiento = LocalDate.parse(paciente.getFechaNacimiento(), formatter);
            statement.setString(8, fechaNacimiento.toString());
           
            System.out.println("Paciente insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
        }
    }
    
    // Método para actualizar los datos de un paciente en la base de datos
    public void actualizarPaciente(Paciente paciente) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(ACTUALIZAR_PACIENTE_SQL)) {
            // Establecer los valores de los parámetros en la consulta
            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellidos());
            statement.setString(3, paciente.getDireccion());
            statement.setString(4, paciente.getTelefono());
            statement.setString(5, paciente.getGenero());
            statement.setString(6, paciente.getAlergia());
            statement.setString(7, paciente.getFechaNacimiento());
            statement.setString(8, paciente.getNif()); // Condición WHERE
            // Ejecutar la consulta
            statement.executeUpdate();
            System.out.println("Paciente actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }
    
     // Método para eliminar un paciente de la base de datos por su NIF
    public void eliminarPaciente(String nif) {
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(ELIMINAR_PACIENTE_SQL)) {
            // Establecer el valor del parámetro en la consulta
            statement.setString(1, nif); // Condición WHERE
            // Ejecutar la consulta
            statement.executeUpdate();
            System.out.println("Paciente eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
        }
    }
    
    // Método para obtener un paciente de la base de datos por su NIF
    public Paciente seleccionarPaciente(String nif) {
        Paciente paciente = null;
        try (Connection conn = new ConexionDB().getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECCIONAR_PACIENTE_SQL)) {
            // Establecer el valor del parámetro en la consulta
            statement.setString(1, nif); // Condición WHERE
            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
                // Verificar si se encontró un resultado
                if (resultSet.next()) {
                    // Obtener los datos del paciente
                    String nombre = resultSet.getString("nombre");
                    String apellidos = resultSet.getString("apellidos");
                    String direccion = resultSet.getString("direccion");
                    String telefono = resultSet.getString("telefono");
                    String genero = resultSet.getString("genero");
                    String alergia = resultSet.getString("alergia");
                    String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                    // Crear un objeto Paciente con los datos obtenidos
                    paciente = new Paciente(nombre, apellidos, nif, direccion, telefono, genero, alergia, fechaNacimiento);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar paciente: " + e.getMessage());
        }
        return paciente;
    }

    // Método para obtener todos los pacientes de la base de datos
    public List<Paciente> obtenerTodosLosPacientes() {
        List<Paciente> listaPacientes = new ArrayList<>();
        try (Connection conn = new ConexionDB().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM pacientes")) {

            while (resultSet.next()) {
                // Obtener los datos de cada paciente
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String nif = resultSet.getString("nif");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String genero = resultSet.getString("genero");
                String alergia = resultSet.getString("alergia");
                String fechaNacimiento = resultSet.getString("fecha_nacimiento");

                // Crear un objeto Paciente con los datos obtenidos y agregarlo a la lista
                Paciente paciente = new Paciente(
                        nombre,
                        apellidos,
                        nif,
                        direccion,
                        telefono,
                        genero,
                        alergia,
                        fechaNacimiento
                );
                listaPacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pacientes: " + e.getMessage());
        }
        return listaPacientes;
    }
}
