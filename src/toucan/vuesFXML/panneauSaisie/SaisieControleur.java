package toucan.vuesFXML.panneauSaisie;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import toucan.modele.StatutAnimation;
import toucan.modele.Toucan;

import java.util.Observable;
import java.util.Observer;

public class SaisieControleur implements Observer {

    private Toucan toucan;

    @FXML
    public TextArea algoSaisie;

    public SaisieControleur(Toucan toucan) {
        this.toucan = toucan;
        this.toucan.addObserver(this);
    }

    @FXML
    public void initialize() throws Exception {
        this.textBoxDesactivee();
    }

    @FXML
    public void updateCodeAlgoPerso() {
        this.toucan.setCodeUtilisateur(this.algoSaisie.getText());
    }

    public void textBoxDesactivee() {
        if (toucan.getAlgoActuel().equals("Algo Personnel") && toucan.getStatutAnimation() == StatutAnimation.NON_INITIALISEE) {
            this.algoSaisie.setDisable(false);
        }
        else {
            this.algoSaisie.setDisable(true);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Runnable command = new Runnable() {

            @Override
            public void run() {
                textBoxDesactivee();
            }

        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }

}
