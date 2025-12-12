package lp.JavaFxClient.controllers;

import lp.JavaFxClient.services.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CriarTipoController {

    @FXML private TextField txtNome;

    private final ApiService api = new ApiService();

    @FXML
    public void onCriar() {
        String nome = txtNome.getText();

        if (nome == null || nome.isBlank()) {
            showError("O nome do tipo é obrigatório.");
            return;
        }

        String json = """
        {
          "nome": "%s"
        }
        """.formatted(nome);

        String result = api.post("/tipos", json);

        if (result.startsWith("ERROR")) {
            showError(result);
            return;
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Tipo criado com sucesso!");
        a.showAndWait();

        fechar();
    }

    @FXML
    public void onCancelar() {
        fechar();
    }

    private void fechar() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
