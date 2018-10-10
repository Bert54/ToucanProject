package toucan.vuesFXML.panneauMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
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
    public void setAlgoCocktail() {
        this.toucan.setAlgoActuel(AttributAlgo.ALGOCOCKTAIL);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.toucan.getStatutAnimation() == StatutAnimation.NON_INITIALISEE) {
            this.menuSelectionAlgo.setDisable(false);
        }
        else {
            this.menuSelectionAlgo.setDisable(true);
        }
    }
}
