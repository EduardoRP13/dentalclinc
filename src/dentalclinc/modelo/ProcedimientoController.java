/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dentalclinc.modelo;

import dentalclinc.database.CitaDAO;
import dentalclinc.database.MedicoDAO;
import dentalclinc.database.PacienteDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class ProcedimientoController implements Initializable {

    @FXML
    private TextField txtDientesPaciente;
    @FXML
    private TextField txtHora;
    @FXML
    private DatePicker DatePickerFecha;
    @FXML
    private TextField txtProcedimiento;
    @FXML
    private ComboBox<String> comboNifPaciente;
    @FXML
    private ComboBox<String> comboMedico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarPacientes();
        cargarMedicos();
    }    

    private void cargarPacientes() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> pacientes = pacienteDAO.obtenerTodosLosPacientes();
        ObservableList<String> nifList = FXCollections.observableArrayList();
        for (Paciente paciente : pacientes) {
            nifList.add(paciente.getNif());
        }
        comboNifPaciente.setItems(nifList);
    }
    
    private void cargarMedicos() {
        MedicoDAO medicoDAO = new MedicoDAO();
        List<Medico> medicos = medicoDAO.obtenerTodosLosMedicos();
        ObservableList<String> medicoList = FXCollections.observableArrayList();
        for (Medico medico : medicos) {
            medicoList.add(medico.getNombre() + " " + medico.getApellidos());
        }
        comboMedico.setItems(medicoList);
    }
    
    @FXML
    private void btnVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/Principal.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = new Stage(); // Crea una nueva ventana para la vista de cadastro
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana actual de login si es necesario
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void guardarProcedimientoDental(ActionEvent event) {
        String nifPaciente = comboNifPaciente.getValue();
        String medico = comboMedico.getValue();
        String dientes = txtDientesPaciente.getText();
        String hora = txtHora.getText();
        LocalDate fecha = DatePickerFecha.getValue();
        String procedimiento = txtProcedimiento.getText();

        if (nifPaciente == null || medico == null || dientes.isEmpty() || hora.isEmpty() || fecha == null || procedimiento.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al guardar");
            alert.setHeaderText(null);
            alert.setContentText("Todos los campos son obligatorios.");
            alert.showAndWait();
        } else {
            Cita cita = new Cita(nifPaciente, medico, dientes, hora, fecha.toString(), procedimiento);
            CitaDAO citaDAO = new CitaDAO();
            citaDAO.insertarCita(cita);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cita guardada");
            alert.setHeaderText(null);
            alert.setContentText("Cita guardada exitosamente.");
            alert.showAndWait();
        }
    }
    
}
