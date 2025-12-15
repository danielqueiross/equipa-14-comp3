package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lp.JavaFxClient.model.EventoDTO;
import lp.JavaFxClient.services.ApiService;

public class EditarEventoController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextArea txtDescricao;
    @FXML private TextField txtData;
    @FXML private TextField txtLotacao;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void onGuardar() {
        try {
            Long id = Long.parseLong(txtId.getText());

            EventoDTO dto = new EventoDTO();
            dto.setTitulo(txtTitulo.getText());
            dto.setDescricao(txtDescricao.getText());
            dto.setData(txtData.getText());
            dto.setLotacaoMax(Integer.parseInt(txtLotacao.getText()));

            String json = mapper.writeValueAsString(dto);
            String result = api.put("/api/eventos/" + id, json);
            Alert a = new Alert(
            	    Alert.AlertType.INFORMATION,
            	    result == null || result.isBlank()
            	        ? "Evento atualizado com sucesso!"
            	        : "Evento atualizado:\n" + result
            	);
            	a.showAndWait();
            fechar();

        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR,
                    "Erro ao editar evento:\n" + e.getMessage() );
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
