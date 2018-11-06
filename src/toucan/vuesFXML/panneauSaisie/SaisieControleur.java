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
        this.updateCodeAlgoPerso();
        this.textBoxDesactivee();
    }

    /**
     * Met a jour le code de l'utilisateur dans le modele
     */
    @FXML
    public void updateCodeAlgoPerso() {
        this.toucan.setCodeUtilisateur(this.algoSaisie.getText());
    }

    /**
     * Active le champ de saisie de code personnel si l'algo actif est l'algo personnel et que l'animation n'est pas lancee
     */
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
