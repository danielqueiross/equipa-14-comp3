package lp.JavaFxClient.controllers;

import lp.JavaFxClient.model.EventoDTO;
import lp.JavaFxClient.services.ApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EventosController {

    @FXML private TableView<EventoDTO> eventosTable;
    @FXML private TableColumn<EventoDTO, Long> idCol;
    @FXML private TableColumn<EventoDTO, String> tituloCol;
    @FXML private TableColumn<EventoDTO, String> dataCol;
    @FXML private TableColumn<EventoDTO, String> estadoCol;
    @FXML private TableColumn<EventoDTO, Integer> lotacaoCol;
    @FXML private TableColumn<EventoDTO, Integer> participantesCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        lotacaoCol.setCellValueFactory(new PropertyValueFactory<>("lotacaoMax"));
        participantesCol.setCellValueFactory(
                new PropertyValueFactory<>("numParticipantes")
        );

        loadEventos();
    }

    @FXML
    public void onRefresh() {
        loadEventos();
    }

    private void loadEventos() {
        try {
            String json = api.get("/eventos");

            if (json.startsWith("ERROR")) {
                showError(json);
                return;
            }

            List<EventoDTO> eventos =
                    mapper.readValue(json, new TypeReference<List<EventoDTO>>() {});

            eventosTable.getItems().setAll(eventos);

        } catch (Exception e) {
            showError("Erro ao carregar eventos: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
