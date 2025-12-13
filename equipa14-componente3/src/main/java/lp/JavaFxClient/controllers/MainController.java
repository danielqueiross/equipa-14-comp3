package lp.JavaFxClient.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class MainController {

    @FXML
    public void onCriarUtilizador() {
        abrirJanela("/formulario-utilizador.fxml", "Criar Utilizador");
    }

    @FXML
    public void onListarUtilizadores() {
        abrirJanela("/listar-utilizadores.fxml", "Listar Utilizadores");
    }

    @FXML
    public void onApagarUtilizador() {
        abrirJanela("/apagar-utilizadores.fxml", "Apagar Utilizador");
    }

    @FXML
    public void onCriarTipo() {
        abrirJanela("/criar-tipo.fxml", "Criar Tipo de Evento");
    }

    @FXML
    public void onCriarEvento() {
        abrirJanela("/criar-evento.fxml", "Criar Evento");
    }

    @FXML
    public void onEditarEvento() {
        abrirJanela("/editar-evento.fxml", "Editar Evento");
    }

    @FXML
    public void onApagarEvento() {
        abrirJanela("/apagar-evento.fxml", "Apagar Evento");
    }

    @FXML
    public void onListarEventos() {
        abrirJanela("/listar-eventos.fxml", "Listar Eventos");
    }

    @FXML
    public void onEventosPoucaAderencia() {
        abrirJanela("/eventos-pouca-aderencia.fxml", "Eventos com Pouca Aderência");
    }

    @FXML
    public void onAprovarEvento() {
        abrirJanela("/aprovar-rejeitar-evento.fxml", "Aprovar / Rejeitar Evento");
    }
    @FXML public void onRejeitarEvento() { 
    	abrirJanela("rejeitar-evento.fxml", "Rejeitar Evento"); }

    @FXML
    public void onInscreverEstudante() {
        abrirJanela("/inscrever-estudante.fxml", "Inscrever Estudante");
    }

    @FXML
    public void onCancelarInscricao() {
        abrirJanela("/cancelar-inscricao.fxml", "Cancelar Inscrição");
    }

    @FXML
    public void onSair() {
        Alert confirm = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Tem a certeza que deseja sair da aplicação?",
                ButtonType.YES,
                ButtonType.NO
        );

        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            Platform.exit();
            System.exit(0);
        }
    }

    private void abrirJanela(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            Alert a = new Alert(
                    Alert.AlertType.ERROR,
                    "Erro ao abrir janela:\n" + fxml
            );
            a.showAndWait();
            e.printStackTrace();
        }
    }
}
