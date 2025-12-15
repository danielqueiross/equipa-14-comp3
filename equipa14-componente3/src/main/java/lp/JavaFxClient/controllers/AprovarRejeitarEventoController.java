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

            String resposta = api.post(
                "/api/gestao-eventos/eventos/" + eventoId + "/aprovar?gestorId=" + gestorId,
                ""
            );
            showInfo("Evento aprovado com sucesso!", resposta);

            Alert a = new Alert(Alert.AlertType.INFORMATION,
                    "Evento aprovado com sucesso!");
            a.showAndWait();

            fechar();

        } catch (Exception e) {
            showError("Erro ao aprovar evento:\n" + e.getMessage());
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

            String motivoEncoded = java.net.URLEncoder.encode(
                    motivo,
                    java.nio.charset.StandardCharsets.UTF_8
            );

            String url = "/api/gestao-eventos/eventos/" + eventoId
                       + "/rejeitar?gestorId=" + gestorId
                       + "&motivo=" + motivoEncoded;

            String result = api.post(url, "");
            showInfo("Evento rejeitado com sucesso", result);
            fechar();

        } catch (NumberFormatException e) {
            showError("IDs inválidos.");
        } catch (Exception e) {
            showError("Erro ao rejeitar evento:\n" + e.getMessage());
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
