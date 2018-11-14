package toucan.vuesFXML.panneauMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import toucan.modele.GestionThreads;
import toucan.modele.StatutAnimation;
import toucan.modele.Toucan;
import toucan.modele.algos.*;

import java.util.Observable;
import java.util.Observer;

public class MenuControleur implements Observer {

    private Toucan toucan;
    @FXML
    public MenuItem menuItemGenerationValeurs;
    @FXML
    public Menu menuSelectionAlgo;

    /**
     * Constructeur
     * @param toucan modele
     */
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

    /**
     * Genere aleatoirement les valeurs du tableau
     */
    @FXML
    public void GenererValeurs() {
        this.toucan.genererCases();
    }

    /**
     * Selection du tri à bulles
     */
    @FXML
    public void setAlgoBulle() {
        this.toucan.setAlgoActuel(new AlgoBulle(this.toucan));
    }


    /**
     * Selection d'un algo de test... non performant
     */
    @FXML
    public void setAlgoTest() {
        this.toucan.setAlgoActuel(new AlgoTest(this.toucan));
    }


    /**
     * Selection du tri stupide
     */
    @FXML
    public void setAlgoStupide() {
        this.toucan.setAlgoActuel(new AlgoStupide(this.toucan));
    }


    /**
     * Selection du tri par insertion
     */
    @FXML
    public void setAlgoInsertion() {
        this.toucan.setAlgoActuel(new AlgoInsertion(this.toucan));
    }


    /**
     * Selection du tri par selection
     */
    @FXML
    public void setAlgoSelection() {
        this.toucan.setAlgoActuel(new AlgoSelection(this.toucan));
    }


    /**
     * Selection du tri cocktail
     */
    @FXML
    public void setAlgoCocktail() {
        this.toucan.setAlgoActuel(new AlgoCocktail(this.toucan));
    }


    /**
     * Selection du tri a peigne
     */
    @FXML
    public void setAlgoPeigne() {
        this.toucan.setAlgoActuel(new AlgoPeigne(this.toucan));
    }


    /**
     * Selection de l'algorithme de decalage circulaire
     */
    @FXML
    public void setAlgoDecCirc() {
        this.toucan.setAlgoActuel(new AlgoDecalageCirculaire(this.toucan));
    }


    /**
     * Selection du tri de shell
     */
    @FXML
    public void setAlgoShell() {
        this.toucan.setAlgoActuel(new AlgoShell(this.toucan));
    }

    @FXML
    public void setAlgoPerso() {
        this.toucan.setAlgoActuel(new AlgoFacade(this.toucan));
    }

    @Override
    public void update(Observable o, Object arg) {

        Runnable command = new Runnable() {

            @Override
            public void run() {
                if (toucan.getStatutAnimation() == StatutAnimation.NON_INITIALISEE) {   // Si un algorithme est lancé,
                    menuSelectionAlgo.setDisable(false);                                // impossibilité de changer l'algorithme
                    menuItemGenerationValeurs.setDisable(false);                        // et de regénérer les valeurs
                }
                else {
                    menuSelectionAlgo.setDisable(true);
                    menuItemGenerationValeurs.setDisable(true);
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
