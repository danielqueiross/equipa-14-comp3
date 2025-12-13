package lp.JavaFxClient.controllers;

import lp.JavaFxClient.services.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AprovarRejeitarEventoController {

    @FXML private TextField txtEventoId;
    @FXML private TextField txtGestorId;
    @FXML private TextArea txtMotivo;

    private final ApiService api = new ApiService();


    @FXML
    public void onAprovar() {
        try {
            Long eventoId = Long.parseLong(txtEventoId.getText());
            Long gestorId = Long.parseLong(txtGestorId.getText());

            String url = "/gestao-eventos/eventos/" + eventoId +
                         "/aprovar?gestorId=" + gestorId;

            String result = api.post(url, null);
            showInfo("Evento aprovado", result);
            fechar();

        } catch (NumberFormatException e) {
            showError("IDs inválidos.");
        }
    }

    @FXML
    public void onRejeitar() {
        try {
            Long eventoId = Long.parseLong(txtEventoId.getText());
            Long gestorId = Long.parseLong(txtGestorId.getText());
            String motivo = txtMotivo.getText();

            if (motivo == null || motivo.isBlank()) {
                showError("O motivo é obrigatório.");
                return;
            }

            String url = "/gestao-eventos/eventos/" + eventoId +
                         "/rejeitar?gestorId=" + gestorId +
                         "&motivo=" + motivo.replace(" ", "%20");

            String result = api.post(url, null);
            showInfo("Evento rejeitado", result);
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
        Stage stage = (Stage) txtEventoId.getScene().getWindow();
        stage.close();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.setTitle(title);
        a.showAndWait();
    }
}
