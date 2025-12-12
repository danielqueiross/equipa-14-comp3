package lp.JavaFxClient.controllers;

import lp.JavaFxClient.model.UtilizadorDTO;
import lp.JavaFxClient.services.ApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UtilizadorController {

    @FXML private TableView<UtilizadorDTO> utilizadoresTable;
    @FXML private TableColumn<UtilizadorDTO, Long> idCol;
    @FXML private TableColumn<UtilizadorDTO, String> nomeCol;
    @FXML private TableColumn<UtilizadorDTO, String> emailCol;
    @FXML private TableColumn<UtilizadorDTO, String> tipoCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        loadUtilizadores();
    }

    @FXML
    public void onRefresh() {
        loadUtilizadores();
    }

    private void loadUtilizadores() {
        try {
            String json = api.get("/utilizadores");

            if (json.startsWith("ERROR:")) {
                showError(json);
                return;
            }

            List<UtilizadorDTO> utilizadores =
                    mapper.readValue(json, new TypeReference<List<UtilizadorDTO>>() {});

            utilizadoresTable.getItems().setAll(utilizadores);

        } catch (Exception e) {
            showError("Erro ao carregar utilizadores: " + e.getMessage());
        }
    }

    @FXML
    public void onAddUtilizador() {
        showInfo("Criar Utilizador", "Abrir formulário criar-utilizador.fxml");
    }

    @FXML
    public void onDeleteUtilizador() {
        UtilizadorDTO selected =
                utilizadoresTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Seleciona um utilizador primeiro.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Apagar utilizador " + selected.getNome() + "?",
                ButtonType.YES, ButtonType.NO);

        confirm.showAndWait();
        if (confirm.getResult() != ButtonType.YES) return;

        String result = api.delete("/utilizadores/" + selected.getId());
        showInfo("Resultado", result);

        loadUtilizadores();
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
