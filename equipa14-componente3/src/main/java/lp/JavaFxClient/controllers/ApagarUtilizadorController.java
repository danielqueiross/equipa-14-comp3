package lp.JavaFxClient.controllers;

import lp.JavaFxClient.services.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ApagarUtilizadorController {

    @FXML private TextField txtId;

    private final ApiService api = new ApiService();

    @FXML
    public void onApagar() {
        try {
            Long id = Long.parseLong(txtId.getText());

            Alert confirm = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Apagar utilizador com ID " + id + "?",
                    ButtonType.YES, ButtonType.NO
            );

            confirm.showAndWait();
            if (confirm.getResult() != ButtonType.YES) return;

            String result = api.delete("/utilizadores/" + id);

            Alert info = new Alert(Alert.AlertType.INFORMATION, result);
            info.showAndWait();

            fechar();

        } catch (NumberFormatException e) {
            showError("ID inválido.");
        }
    }

    @FXML
    public void onCancelar() {
        fechar();
    }

    private void fechar() {
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}

