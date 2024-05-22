/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dentalclinc.modelo;

import dentalclinc.database.CitaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eduar
 * Controlador del FXML citas donde incluyo todas las funciones y metodos importantes
 */
public class CitasController implements Initializable {

    @FXML
    private TableView<Cita> TableCitas;
    
    @FXML
    private TableColumn<Cita, String> colNifPaciente;
    @FXML
    private TableColumn<Cita, String> colMedico;
    @FXML
    private TableColumn<Cita, String> colDientes;
    @FXML
    private TableColumn<Cita, String> colHora;
    @FXML
    private TableColumn<Cita, String> colFecha;
    @FXML
    private TableColumn<Cita, String> colProcedimiento;

    private ObservableList<Cita> citasList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        citasList = FXCollections.observableArrayList(); // Aquí se inicializa
        // Inicializar columnas
        colNifPaciente.setCellValueFactory(new PropertyValueFactory<>("nifPaciente"));
        colMedico.setCellValueFactory(new PropertyValueFactory<>("medico"));
        colDientes.setCellValueFactory(new PropertyValueFactory<>("dientes"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colProcedimiento.setCellValueFactory(new PropertyValueFactory<>("procedimiento"));

        cargarCitas(); // Cargar las citas al iniciar la ventana
    }
    
    // Método para cargar las citas desde la base de datos
    private void cargarCitas() {
        CitaDAO citaDAO = new CitaDAO();
        citasList.setAll(citaDAO.obtenerTodasLasCitas());
        TableCitas.setItems(citasList);
    }
    
    // Evento para volver a la pantalla principal   
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

    // Evento para eliminar una cita seleccionada
    @FXML
    private void EliminarCitaPrevia(ActionEvent event) {
        Cita citaSeleccionada = TableCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            int idCitaSeleccionada = citaSeleccionada.getId(); // Asegúrate de tener un método getId() en tu clase Cita
            System.out.println("ID de la cita seleccionada: " + citaSeleccionada.getId());
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de eliminación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que deseas eliminar esta cita?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CitaDAO citaDAO = new CitaDAO();
                System.out.println("Intentando eliminar la cita con ID: " + citaSeleccionada.getId());
                citaDAO.eliminarCita(idCitaSeleccionada);
                cargarCitas();
                System.out.println("Cita eliminada correctamente.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No se ha seleccionado ninguna cita");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una cita para eliminar.");
            alert.showAndWait();
        }
    }

    // Evento para editar una cita seleccionada (a implementar)
    @FXML
    private void EditarCitaPrevia(ActionEvent event) {
        // tengo que implementar esta funcion
    }
    
}
