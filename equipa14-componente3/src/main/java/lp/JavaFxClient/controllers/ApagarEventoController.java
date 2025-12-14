package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lp.JavaFxClient.services.ApiService;

public class ApagarEventoController {

    @FXML private TextField txtId;

    private final ApiService api = new ApiService();

    @FXML
    public void onApagar() {
        try {
            Long id = Long.parseLong(txtId.getText());

            api.delete("/api/eventos/" + id);

            Alert a = new Alert(Alert.AlertType.INFORMATION,
                    "Evento apagado com sucesso!");
            a.showAndWait();

            fechar();

        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR,
                    "Erro ao apagar evento:\n" + e.getMessage());
            a.showAndWait();
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
}
