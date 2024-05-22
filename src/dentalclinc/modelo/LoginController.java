/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dentalclinc.modelo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author eduar
 * El controlador FXML de la pantalla de bienvenida al usuario 
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Asegurame de que el TextField de usuario obtenga el foco al iniciar
        Platform.runLater(() -> textLoginUser.requestFocus());
        // Configurar eventos para capturar la tecla "Enter" en los campos de texto
        textLoginUser.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin(); // Llama al método de inicio de sesión
            }
        });

        textLoginPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin(); // Llama al método de inicio de sesión
            }
        });
    } 
    
    private void textLoginUser(ActionEvent event) {
        String username = textLoginUser.getText();
    }

    private void textLoginPassword(ActionEvent event) {
        String password = textLoginPassword.getText();
    }

    @FXML private TextField textLoginUser; //necesario para getText()
    @FXML private PasswordField textLoginPassword; //necesario para getText
    
    @FXML
    private void btnLogin() {
        String usuario = textLoginUser.getText();
        String password = textLoginPassword.getText();

        if (usuario.equals("admin") && password.equals("admin")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/Principal.fxml"));
                Parent root = loader.load();
                // PrincipalController controller = loader.getController(); // Obtener el controlador de la ventana principal si es necesario

                // Crear una nueva escena
                Scene scene = new Scene(root);

                // Obtener la ventana actual y establecer la nueva escena
                Stage loginStage = (Stage) textLoginUser.getScene().getWindow(); // Obtener la ventana actual
                loginStage.setScene(scene);
                loginStage.show();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inicio de sesión exitoso");
                alert.setHeaderText(null);
                alert.setContentText("¡Bienvenido, " + usuario + "!");
                alert.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            // Credenciales inválidas, mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al iniciar sesión");
            alert.setHeaderText(null);
            alert.setContentText("Nombre de usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void btnLogin(ActionEvent event) {
        btnLogin(); // Llamar al método btnLogin sin parámetros
    }  
     

    @FXML
    private void btnCadastro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dentalclinc/vista/CadastroTrabajador.fxml"));
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


    
}

