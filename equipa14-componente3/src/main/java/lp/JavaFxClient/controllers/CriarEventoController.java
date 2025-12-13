package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lp.JavaFxClient.services.ApiService;

public class CriarEventoController {

    @FXML private TextField txtOrganizadorId;
    @FXML private TextField txtTipoId;
    @FXML private TextField txtTitulo;
    @FXML private TextArea txtDescricao;
    @FXML private TextField txtData;
    @FXML private TextField txtLotacao;

    private final ApiService api = new ApiService();

    @FXML
    public void onCriarEvento() {
        try {
            String json = """
            {
              "titulo": "%s",
              "descricao": "%s",
              "data": "%s",
              "lotacaoMax": %d
            }
            """.formatted(
                    txtTitulo.getText(),
                    txtDescricao.getText(),
                    txtData.getText(),
                    Integer.parseInt(txtLotacao.getText())
            );

            String url = "/gestao-eventos/eventos?organizadorId="
                    + txtOrganizadorId.getText()
                    + "&tipoId="
                    + txtTipoId.getText();

            String result = api.post(url, json);

            Alert a = new Alert(Alert.AlertType.INFORMATION, result);
            a.showAndWait();

            onCancelar();

        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR,
                    "Erro ao criar evento: " + e.getMessage());
            err.showAndWait();
        }
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }
}
