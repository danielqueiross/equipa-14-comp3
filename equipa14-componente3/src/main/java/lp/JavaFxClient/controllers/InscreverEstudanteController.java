package lp.JavaFxClient.controllers;

import lp.JavaFxClient.services.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InscreverEstudanteController {

    @FXML private TextField txtEstudanteId;
    @FXML private TextField txtEventoId;
    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;

    private final ApiService api = new ApiService();

    @FXML
    public void onInscrever() {
        try {
            Long estudanteId = Long.parseLong(txtEstudanteId.getText());
            Long eventoId = Long.parseLong(txtEventoId.getText());
            String nome = txtNome.getText();
            String email = txtEmail.getText();

            if (nome.isBlank() || email.isBlank()) {
                showError("Nome e email são obrigatórios.");
                return;
            }

            String json = """
            {
              "nomeParticipante": "%s",
              "email": "%s"
            }
            """.formatted(nome, email);

            String url = "/gestao-eventos/inscricoes?estudanteId=" +
                         estudanteId + "&eventoId=" + eventoId;

            String result = api.post(url, json);

            Alert info = new Alert(Alert.AlertType.INFORMATION, result);
            info.showAndWait();

            fechar();

        } catch (NumberFormatException e) {
            showError("IDs inválidos.");
        }
    }

    @FXML
    public void onCancelar() {
        fechar();
    }

    private void fechar() {
        Stage stage = (Stage) txtEstudanteId.getScene().getWindow();
        stage.close();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
