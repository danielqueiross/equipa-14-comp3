package lp.JavaFxClient.controllers;

import lp.JavaFxClient.services.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CancelarInscricaoController {

    @FXML private TextField txtInscricaoId;

    private final ApiService api = new ApiService();

    @FXML
    public void onCancelarInscricao() {
        try {
            Long id = Long.parseLong(txtInscricaoId.getText());

            Alert confirm = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Cancelar inscrição com ID " + id + "?",
                    ButtonType.YES, ButtonType.NO
            );

            confirm.showAndWait();
            if (confirm.getResult() != ButtonType.YES) return;

            String result = api.delete("/gestao-eventos/inscricoes/" + id);

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
        Stage stage = (Stage) txtInscricaoId.getScene().getWindow();
        stage.close();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
