package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lp.JavaFxClient.model.UtilizadorDTO;
import lp.JavaFxClient.services.ApiService;

public class CriarUtilizadorController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbTipo;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

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
        try {
            UtilizadorDTO dto = new UtilizadorDTO(
                    txtNome.getText(),
                    txtEmail.getText(),
                    txtPassword.getText()
            );

            ObjectNode jsonNode = mapper.valueToTree(dto);
            jsonNode.put("tipo", cmbTipo.getValue());

            String json = mapper.writeValueAsString(jsonNode);

            String result = api.post("/api/utilizadores", json);

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Utilizador criado com sucesso!");
            a.showAndWait();

            fechar();

        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR,
                    "Erro ao criar utilizador: " + e.getMessage());
            a.showAndWait();
        }
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
