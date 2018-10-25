package toucan.vuesFXML.panneauMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import toucan.modele.GestionThreads;
import toucan.modele.StatutAnimation;
import toucan.modele.Toucan;
import toucan.modele.algos.*;

import java.util.Observable;
import java.util.Observer;

public class MenuControleur implements Observer {

    private Toucan toucan;

    @FXML
    public Menu menuSelectionAlgo;

    public MenuControleur(Toucan toucan) {
        this.toucan = toucan;
        this.toucan.addObserver(this);
    }

    /**
     * Detruit les threads et ferme le programme
     */
    @FXML
    public void fermerProgramme() {
        GestionThreads.getInstance().detruireTout();
        Platform.exit() ;
    }

    @FXML
    public void setAlgoBulle() {
        this.toucan.setAlgoActuel(new AlgoBulle(this.toucan));
    }

    @FXML
    public void setAlgoTest() {
        this.toucan.setAlgoActuel(new AlgoTest(this.toucan));
    }

    @FXML
    public void setAlgoStupide() {
        this.toucan.setAlgoActuel(new AlgoStupide(this.toucan));
    }

    @FXML
    public void setAlgoInsertion() {
        this.toucan.setAlgoActuel(new AlgoInsertion(this.toucan));
    }

    @FXML
    public void setAlgoSelection() {
        this.toucan.setAlgoActuel(new AlgoSelection(this.toucan));
    }

    @FXML
    public void setAlgoCocktail() {
        this.toucan.setAlgoActuel(new AlgoCocktail(this.toucan));
    }

    @FXML
    public void setAlgoPeigne() {
        this.toucan.setAlgoActuel(new AlgoPeigne(this.toucan));
    }

    @FXML
    public void setAlgoDecCirc() {
        this.toucan.setAlgoActuel(new AlgoDecalageCirculaire(this.toucan));
    }

    @FXML
    public void setAlgoShell() {
        this.toucan.setAlgoActuel(new AlgoShell(this.toucan));
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
