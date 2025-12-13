package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lp.JavaFxClient.model.InscricaoDTO;
import lp.JavaFxClient.services.ApiService;

public class InscreverEstudanteController {

    @FXML private TextField txtEstudanteId;
    @FXML private TextField txtEventoId;
    @FXML private TextField txtNomeParticipante;
    @FXML private TextField txtEmail;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void onInscrever() {
        try {
            long estudanteId = Long.parseLong(txtEstudanteId.getText().trim());
            long eventoId = Long.parseLong(txtEventoId.getText().trim());

            InscricaoDTO dto = new InscricaoDTO();
            dto.setNomeParticipante(txtNomeParticipante.getText().trim());
            dto.setEmail(txtEmail.getText().trim());

            String json = mapper.writeValueAsString(dto);

            String result = api.post(
                    "/gestao-eventos/inscricoes?estudanteId=" + estudanteId + "&eventoId=" + eventoId,
                    json
            );

            if (result.startsWith("ERROR:")) {
                showError(result);
                return;
            }

            Alert info = new Alert(Alert.AlertType.INFORMATION, "Inscrição criada:\n" + result);
            info.showAndWait();
            fechar();

        } catch (NumberFormatException e) {
            showError("IDs inválidos (Estudante ID e Evento ID têm de ser números).");
        } catch (Exception e) {
            showError("Erro ao inscrever: " + e.getMessage());
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
