/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dentalclinc.modelo;

import dentalclinc.database.PacienteDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
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
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField txtNifPaciente;
    @FXML
    private TextField txtDireccionPaciente;
    @FXML
    private TextField txtNombrePaciente;
    @FXML
    private TextField txtApellidosPaciente;
    @FXML
    private TextField txtTelPaciente;
    @FXML
    private ChoiceBox<String> choiceBoxSexPaciente;
    @FXML
    private TextField txtAlergiaPaciente;
    @FXML
    private DatePicker txtFechaNascPaciente;
    @FXML
    private TableView<Paciente> TablePaciente;
    @FXML
    private TableColumn<Paciente, String> colNifPaciente;
    @FXML
    private TableColumn<Paciente, String> colDireccionPaciente;
    @FXML
    private TableColumn<Paciente, String> colNombrePaciente;
    @FXML
    private TableColumn<Paciente, String> colApellidosPaciente;
    @FXML
    private TableColumn<Paciente, String> colTelPaciente;
    @FXML
    private TableColumn<Paciente, String> colAlergiaPaciente;
    @FXML
    private TableColumn<Paciente, String> colFechaNascPaciente;
    @FXML
    private TableColumn<Paciente, String> colGeneroPaciente;
    
    private ObservableList<Paciente> pacientes = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarDatosPacientes();
        
        choiceBoxSexPaciente.getItems().addAll("Hombre", "Mujer");
        
        colNifPaciente.setCellValueFactory(new PropertyValueFactory<>("nif"));
        colDireccionPaciente.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colNombrePaciente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidosPaciente.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        colTelPaciente.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colAlergiaPaciente.setCellValueFactory(new PropertyValueFactory<>("Alergia"));
        colFechaNascPaciente.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colGeneroPaciente.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        
        TablePaciente.setItems(pacientes);
        
        
        
    }    

    /*
    obtiene los datos de todos los pacientes desde la base de datos y los carga en la lista observable pacientes.
    */
    private void cargarDatosPacientes() {
        // Limpiar la lista de pacientes
        pacientes.clear();
        // Consultar la base de datos para obtener todos los pacientes
        List<Paciente> listaPacientes = new PacienteDAO().obtenerTodosLosPacientes();
        // Agregar los pacientes a la lista observable
        pacientes.addAll(listaPacientes);
        // Refrescar el TableView
        TablePaciente.refresh();
    }

    @FXML
    private void btnCitas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/Citas.fxml"));
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
    private void btnProcedimientoDental(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/Procedimiento.fxml"));
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
    private void btnGestionDeMedicos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/Medicos.fxml"));
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
    
    /*
    btnSalvarPaciente(ActionEvent event)
    Estemetodo guarda un nuevo paciente en la base de datos. 
    Primero verifica que todos los campos estén completos y que la fecha de nacimiento no sea nula.
    */
    
    @FXML
    private void btnSalvarPaciente(ActionEvent event) {
        // Obtener la fecha del DatePicker
        LocalDate fechaNacimiento = txtFechaNascPaciente.getValue();

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
            Paciente paciente = new Paciente(txtNombrePaciente.getText(), txtApellidosPaciente.getText(), txtNifPaciente.getText(),
                    txtDireccionPaciente.getText(), txtTelPaciente.getText(), choiceBoxSexPaciente.getValue(),
                    txtAlergiaPaciente.getText(), fechaNacimientoFormateada);

            // Insertar el paciente en la base de datos
            new PacienteDAO().insertarPaciente(paciente);

            // Limpiar los campos y actualizar la tabla
            limpiarCampos();
            cargarDatosPacientes();
            TablePaciente.refresh();
        }
        /*
        //Metodo con arrayList
        String nombre = txtNombrePaciente.getText();
        String apellidos = txtApellidosPaciente.getText();
        String nif = txtNifPaciente.getText();
        String direccion = txtDireccionPaciente.getText();
        String telefono = txtTelPaciente.getText();
        String genero = (String) choiceBoxSexPaciente.getValue();
        String alergia = txtAlergiaPaciente.getText();
        String fechaNacimiento = txtFechaNascPaciente.getText();
        Paciente paciente = new Paciente(nombre, apellidos, nif, direccion, telefono, genero, alergia, fechaNacimiento);
        pacientes.add(paciente);
        limpiarCampos();
        TablePaciente.refresh();
         */
    }

    /*
    camposVacios()
    Este método verifica si todos los campos de entrada están vacíos.
    */
    
    private boolean camposVacios() {
        return txtNombrePaciente.getText().isEmpty()
                && txtApellidosPaciente.getText().isEmpty()
                && txtNifPaciente.getText().isEmpty()
                && txtDireccionPaciente.getText().isEmpty()
                && txtTelPaciente.getText().isEmpty()
                && choiceBoxSexPaciente.getValue() == null
                && txtAlergiaPaciente.getText().isEmpty();
    }

    /*
    limpiarCampos()
    Este método limpia todos los campos de entrada del formulario.
    */
    private void limpiarCampos() {
        // Limpia los campos de texto
        txtNifPaciente.clear();
        txtDireccionPaciente.clear();
        txtNombrePaciente.clear();
        txtApellidosPaciente.clear();
        txtTelPaciente.clear();
        txtAlergiaPaciente.clear();
        txtFechaNascPaciente.setValue(null); // Establecer el DatePicker a null para limpiarlo
    }
    
    // muestra una alerta con un título y mensaje específicos.
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    /*
    btnEditarPaciente(ActionEvent event): Obtiene el paciente seleccionado en la tabla y 
    carga sus datos en los campos de texto para edición.
    */
    
    @FXML
    private void btnEditarPaciente(ActionEvent event) {
        // Obtener el paciente seleccionado en la tabla
        Paciente pacienteSeleccionado = TablePaciente.getSelectionModel().getSelectedItem();
        if (pacienteSeleccionado != null) {
            // Llenar los campos de texto con la información del paciente seleccionado
            txtNombrePaciente.setText(pacienteSeleccionado.getNombre());
            txtApellidosPaciente.setText(pacienteSeleccionado.getApellidos());
            txtNifPaciente.setText(pacienteSeleccionado.getNif());
            txtDireccionPaciente.setText(pacienteSeleccionado.getDireccion());
            txtTelPaciente.setText(pacienteSeleccionado.getTelefono());
            choiceBoxSexPaciente.setValue(pacienteSeleccionado.getGenero());
            txtAlergiaPaciente.setText(pacienteSeleccionado.getAlergia());
            txtFechaNascPaciente.setValue(LocalDate.parse(pacienteSeleccionado.getFechaNacimiento()));
        } else {
            mostrarAlerta("Advertencia", "Por favor, selecciona un paciente para editar.");
        }
    }

    /*
    btnConfirmarEdicion(ActionEvent event): Actualiza el objeto Paciente seleccionado con los datos editados y 
    lo guarda en la base de datos. Luego, refresca la tabla y limpia los campos de texto.
    */
    
    @FXML
    private void btnConfirmarEdicion(ActionEvent event) {
        // Obtener el paciente seleccionado en la tabla
        Paciente pacienteSeleccionado = TablePaciente.getSelectionModel().getSelectedItem();
        if (pacienteSeleccionado != null) {
            String nuevoNif = txtNifPaciente.getText();
            // Actualizar la información del paciente con los datos ingresados en los campos de texto
            pacienteSeleccionado.setNombre(txtNombrePaciente.getText());
            pacienteSeleccionado.setApellidos(txtApellidosPaciente.getText());
            pacienteSeleccionado.setNif(nuevoNif);  // Asegúrate de actualizar el NIF
            pacienteSeleccionado.setDireccion(txtDireccionPaciente.getText());
            pacienteSeleccionado.setTelefono(txtTelPaciente.getText());
            pacienteSeleccionado.setGenero((String) choiceBoxSexPaciente.getValue());
            pacienteSeleccionado.setAlergia(txtAlergiaPaciente.getText());
            pacienteSeleccionado.setFechaNacimiento(txtFechaNascPaciente.getValue().toString());

            new PacienteDAO().actualizarPaciente(pacienteSeleccionado);

            cargarDatosPacientes();
            // Actualizar la tabla
            TablePaciente.refresh();
            // Limpiar los campos de texto después de la edición
            limpiarCampos();
        }else {
            mostrarAlerta("Advertencia", "Por favor, selecciona un paciente para confirmar la edición.");
        }
    }

    @FXML
    private void btnEliminarPaciente(ActionEvent event) {
        // Obtener el paciente seleccionado en la tabla
        Paciente pacienteSeleccionado = TablePaciente.getSelectionModel().getSelectedItem();
        if (pacienteSeleccionado != null) {
            // Confirmar la eliminación con una ventana de diálogo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar este paciente?");
            alert.setContentText("Esta acción no se puede deshacer.");
            // Mostrar la ventana de diálogo y esperar la respuesta del usuario
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Eliminar el paciente de la base de datos
                    new PacienteDAO().eliminarPaciente(pacienteSeleccionado.getNif());
                    // Eliminar el paciente de la lista observable vinculada a la tabla
                    ObservableList<Paciente> listaPacientes = TablePaciente.getItems();
                    listaPacientes.remove(pacienteSeleccionado);
                    // Mostrar un mensaje de confirmación
                    mostrarAlerta("Información", "El paciente ha sido eliminado correctamente.");
                    limpiarCampos();
                    TablePaciente.refresh();
                }
            });
        } else {
            // Si no se selecciona ningún paciente, mostrar un mensaje de advertencia
            mostrarAlerta("Advertencia", "Por favor, selecciona un paciente para eliminar.");
        }
    }
    
}
