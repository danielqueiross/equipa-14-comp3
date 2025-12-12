package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FromController {

    @FXML public void onCriarUtilizador() {
        abrirJanela("/criar-utilizador.fxml", "Criar Utilizador");
    }
    @FXML public void onListarUtilizadores() {
        abrirJanela("/listar-utilizadores.fxml", "Listar Utilizadores");
    }
    @FXML public void onApagarUtilizador() {}
    
    @FXML public void onCriarTipo() {}
    
    @FXML public void onCriarEvento() {}
    @FXML public void onEditarEvento() {}
    @FXML public void onApagarEvento() {}
    @FXML public void onListarEventos() {}
    @FXML public void onEventosPoucaAderencia() {}
    @FXML public void onAprovarEvento() {}
    @FXML public void onRejeitarEvento() {}
    
    @FXML public void onInscreverEstudante() {}
    @FXML public void onCancelarInscricao() {}
    
    @FXML public void onSair() {
        System.exit(0);
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
            e.printStackTrace();
        }
    }
}
