/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dentalclinc.modelo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eduar
 * en desarollo no tengo esto listo de momento
 */
public class CadastroTrabajadorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastroTrabajador(ActionEvent event) {
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/Login.fxml"));
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
    private void txtNombreTrabajador(ActionEvent event) {
    }

    @FXML
    private void txtApellidoTrabajador(ActionEvent event) {
    }

    @FXML
    private void txtCorreoTrabajador(ActionEvent event) {
    }

    @FXML
    private void txtPasswordTrabajador(ActionEvent event) {
    }

    @FXML
    private void btnEditarTrabajador(ActionEvent event) {
    }

    @FXML
    private void btnDeletarTrabajador(ActionEvent event) {
    }
    
}
