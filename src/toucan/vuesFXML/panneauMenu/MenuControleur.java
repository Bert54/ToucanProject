package toucan.vuesFXML.panneauMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import toucan.modele.GestionThreads;
import toucan.modele.StatutAnimation;
import toucan.modele.Toucan;
import toucan.modele.algos.AttributAlgo;

import java.util.Observable;
import java.util.Observer;

public class MenuControleur implements Observer {

    private Toucan toucan;

    @FXML
    public Menu menuSelectionAlgo;

    public MenuControleur(Toucan t) {
        this.toucan = t;
        this.toucan.addObserver(this);
    }

    @FXML
    public void fermerProgramme() {
        GestionThreads.getInstance().detruireTout();
        Platform.exit() ;
    }

    @FXML
    public void setAlgoBulle() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOBULLE);
    }

    @FXML
    public void setAlgoTest() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOTEST);
    }

    @FXML
    public void setAlgoStupide() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOSTUPIDE);
    }

    @FXML
    public void setAlgoInsertion() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOINSERTION);
    }

    @FXML
    public void setAlgoSelection() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOSELECTION);
    }

    @FXML
    public void setAlgoCocktail() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOCOCKTAIL);
    }

    @FXML
    public void setAlgoPeigne() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOPEIGNE);
    }

    @FXML
    public void setAlgoDecCirc() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGODECALAGECIRC);
    }

    @FXML
    public void setAlgoShell() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOSHELL);
    }

    @Override
    public void update(Observable o, Object arg) {

        Runnable command = new Runnable() {

            @Override
            public void run() {
                if (toucan.getStatutAnimation() == StatutAnimation.NON_INITIALISEE) {
                    menuSelectionAlgo.setDisable(false);
                }
                else {
                    menuSelectionAlgo.setDisable(true);
                }
            }

        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
