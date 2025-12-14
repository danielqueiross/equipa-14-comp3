package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lp.JavaFxClient.services.ApiService;

public class CancelarInscricaoController {

    @FXML private TextField txtInscricaoId;

    private final ApiService api = new ApiService();

    @FXML
    public void onCancelarInscricao() {
        try {
            long id = Long.parseLong(txtInscricaoId.getText().trim());

            Alert confirm = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Cancelar inscrição com ID " + id + "?",
                    ButtonType.YES, ButtonType.NO
            );
            confirm.showAndWait();
            if (confirm.getResult() != ButtonType.YES) return;

            String result = api.delete("/api/gestao-eventos/inscricoes/" + id);

            if (result.startsWith("ERROR:")) {
                showError(result);
                return;
            }

            Alert info = new Alert(Alert.AlertType.INFORMATION,
                    result == null || result.isBlank()
                            ? "Inscrição cancelada com sucesso!"
                            : result
            );
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
