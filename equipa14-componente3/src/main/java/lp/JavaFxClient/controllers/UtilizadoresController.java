package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.model.UtilizadorDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class UtilizadoresController {

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
            String json = api.get("/api/utilizadores");
            System.out.println(json);

            if (json.trim().startsWith("{") && json.contains("error")) {
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


    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
