package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CriarUtilizadorController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbTipo;

    @FXML
    public void initialize() {
        cmbTipo.getItems().addAll(
                "estudante",
                "organizador",
                "gestor"
        );
    }

    @FXML
    public void onCriar() {
        Alert a = new Alert(Alert.AlertType.INFORMATION,
                "Tipo escolhido: " + cmbTipo.getValue());
        a.showAndWait();
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}
