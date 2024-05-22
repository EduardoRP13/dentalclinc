/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dentalclinc.modelo;

import dentalclinc.database.MedicoDAO;
import dentalclinc.database.PacienteDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eduar
 * Controlador FXML donde tengo todas la funciones y metodos importantes
 */
public class MedicosController implements Initializable {

    @FXML
    private TextField txtNifMedico;
    @FXML
    private TextField txtDireccionMedico;
    @FXML
    private TextField txtNombreMedico;
    @FXML
    private TextField txtApellidosMedico;
    @FXML
    private TextField txtTelefonoMedico;
    @FXML
    private ChoiceBox<String> choiceBoxSexMedico;
    @FXML
    private DatePicker txtFechaNascMedico;
    @FXML
    private TableView<Medico> TableMedico;
    @FXML
    private TableColumn<Medico, String> colNombreMedico;
    @FXML
    private TableColumn<Medico, String> colNifMedico;
    @FXML
    private TableColumn<Medico, String> colApellidosMedico;
    @FXML
    private TableColumn<Medico, String> colFechaNascMedico;
    @FXML
    private TableColumn<Medico, String> colGeneroMedico;
    @FXML
    private TableColumn<Medico, String> colTelMedico;
    @FXML
    private TableColumn<Medico, String> colDireccionMedico;
    
    private ObservableList<Medico> medicos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarDatosMedicos();
        
        choiceBoxSexMedico.getItems().addAll("Hombre", "Mujer");
        
        colNifMedico.setCellValueFactory(new PropertyValueFactory<>("nif"));
        colDireccionMedico.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colNombreMedico.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colApellidosMedico.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        colTelMedico.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colFechaNascMedico.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colGeneroMedico.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        
        TableMedico.setItems(medicos);
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
    
    private void cargarDatosMedicos() {
        // Limpiar la lista de medicos
        medicos.clear();
        // Consultar la base de datos para obtener todos los medicos
        List<Medico> listaMedicos = new MedicoDAO().obtenerTodosLosMedicos();
        // Agregar los pacientes a la lista observable
        medicos.addAll(listaMedicos);
        // Refrescar el TableView
        TableMedico.refresh();
    }

    @FXML
    private void btnSalvarMedico(ActionEvent event) {
        // Obtener la fecha del DatePicker
        LocalDate fechaNacimiento = txtFechaNascMedico.getValue();

        // Verificar si todos los campos están vacíos
        if (camposVacios()) {
            // Mostrar un mensaje de error si todos los campos están vacíos
            mostrarAlerta("Error", "\"Por favor, completa todos los campos.");
        } else if (fechaNacimiento == null) {
            // Mostrar un mensaje de error si la fecha de nacimiento es nula
            mostrarAlerta("Error", "Por favor, selecciona la fecha de nacimiento.");
        } else {
            // Formatear la fecha en el formato deseado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaNacimientoFormateada = fechaNacimiento.format(formatter);

            // Crear el objeto Paciente con la fecha formateada
            Medico medico = new Medico(txtNombreMedico.getText(), txtApellidosMedico.getText(), txtNifMedico.getText(),
                    txtDireccionMedico.getText(), txtTelefonoMedico.getText(), choiceBoxSexMedico.getValue(), fechaNacimientoFormateada);

            // Insertar el paciente en la base de datos
            new MedicoDAO().insertarMedico(medico);

            // Limpiar los campos y actualizar la tabla
            limpiarCampos();
            cargarDatosMedicos();
            TableMedico.refresh();
        }
    }

    @FXML
    private void btnEditarMedico(ActionEvent event) {
        // Obtener el paciente seleccionado en la tabla
        Medico medicoSeleccionado = TableMedico.getSelectionModel().getSelectedItem();
        if (medicoSeleccionado != null) {
            // Llenar los campos de texto con la información del paciente seleccionado
            txtNombreMedico.setText(medicoSeleccionado.getNombre());
            txtApellidosMedico.setText(medicoSeleccionado.getApellidos());
            txtNifMedico.setText(medicoSeleccionado.getNif());
            txtDireccionMedico.setText(medicoSeleccionado.getDireccion());
            txtTelefonoMedico.setText(medicoSeleccionado.getTelefono());
            choiceBoxSexMedico.setValue(medicoSeleccionado.getGenero());
            txtFechaNascMedico.setValue(LocalDate.parse(medicoSeleccionado.getFechaNacimiento()));
        } else {
            mostrarAlerta("Advertencia", "Por favor, selecciona un paciente para editar.");
        }
    }

    @FXML
    private void btnConfimarEdicionMedico(ActionEvent event) {
        // Obtener el paciente seleccionado en la tabla
        Medico medicoSeleccionado = TableMedico.getSelectionModel().getSelectedItem();
        if (medicoSeleccionado != null) {
            // Actualizar la información del paciente con los datos ingresados en los campos de texto
            medicoSeleccionado.setNombre(txtNombreMedico.getText());
            medicoSeleccionado.setApellidos(txtApellidosMedico.getText());
            medicoSeleccionado.setNif(txtNifMedico.getText());
            medicoSeleccionado.setDireccion(txtDireccionMedico.getText());
            medicoSeleccionado.setTelefono(txtTelefonoMedico.getText());
            medicoSeleccionado.setGenero((String) choiceBoxSexMedico.getValue());
            medicoSeleccionado.setFechaNacimiento(txtFechaNascMedico.getValue().toString());
            
            new MedicoDAO().actualizarMedico(medicoSeleccionado);

            cargarDatosMedicos();
            // Actualizar la tabla
            TableMedico.refresh();

            // Limpiar los campos de texto después de la edición
            limpiarCampos();
        }
    }

    @FXML
    private void btnEliminarMedico(ActionEvent event) {
        // Obtener el paciente seleccionado en la tabla
        Medico medicoSeleccionado = TableMedico.getSelectionModel().getSelectedItem();
        if (medicoSeleccionado != null) {
            // Confirmar la eliminación con una ventana de diálogo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar este medico?");
            alert.setContentText("Esta acción no se puede deshacer.");
            // Mostrar la ventana de diálogo y esperar la respuesta del usuario
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Eliminar el paciente de la base de datos
                    new MedicoDAO().eliminarMedico(medicoSeleccionado.getNif());
                    // Eliminar el paciente de la lista observable vinculada a la tabla
                    ObservableList<Medico> listaMedicos = TableMedico.getItems();
                    listaMedicos.remove(medicoSeleccionado);
                    // Mostrar un mensaje de confirmación
                    mostrarAlerta("Información", "El Medico ha sido eliminado correctamente.");
                    limpiarCampos();
                    TableMedico.refresh();
                }
            });
        } else {
            // Si no se selecciona ningún paciente, mostrar un mensaje de advertencia
            mostrarAlerta("Advertencia", "Por favor, selecciona un Medico para eliminar.");
        }
    }

    
    
     /*
    limpiarCampos()
    Este método limpia todos los campos de entrada del formulario.
    */
    private void limpiarCampos() {
        // Limpia los campos de texto
        txtNifMedico.clear();
        txtDireccionMedico.clear();
        txtNombreMedico.clear();
        txtApellidosMedico.clear();
        txtTelefonoMedico.clear();
        txtFechaNascMedico.setValue(null); // Establecer el DatePicker a null para limpiarlo
    }
    
    /*
    camposVacios()
    Este método verifica si todos los campos de entrada están vacíos.
    */
    
    private boolean camposVacios() {
        return txtNombreMedico.getText().isEmpty()
                && txtApellidosMedico.getText().isEmpty()
                && txtNifMedico.getText().isEmpty()
                && txtDireccionMedico.getText().isEmpty()
                && txtTelefonoMedico.getText().isEmpty();
    }

   
    
    // muestra una alerta con un título y mensaje específicos.
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
