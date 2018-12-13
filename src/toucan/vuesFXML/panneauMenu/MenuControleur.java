package toucan.vuesFXML.panneauMenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import toucan.modele.GestionThreads;
import toucan.modele.StatutAnimation;
import toucan.modele.Toucan;
import toucan.modele.algos.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class MenuControleur implements Observer {

    private Toucan toucan;
    @FXML
    public MenuItem menuItemGenerationValeurs;
    @FXML
    public MenuItem menuItemChangeSize;
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
     * Action d'un composant graphique permettant de changer la taille du tableau
     */
    @FXML
    public void changeArraySize() {
        TextInputDialog dialog = new TextInputDialog("" + this.toucan.nbCases());
        dialog.setTitle("Nouvelle taille de tableau");
        dialog.setHeaderText("Entrez la nouvelle taille du tableau :");
        dialog.setContentText("Minimum : 2 ; Maximum : 11");
        Optional<String> result;
        boolean invalid = false; // Permet de déterminer si l'utilisateur a saisie une valeur non acceptée.
        do { // On doit exécuter cette boucle au moins une fois afin de récupérer la valeur de l'utilisateur. On utilise donc un do...while.
            // Si on boucle, cela veut dire que l'utilisateur a sasie une valeur non valide. On affiche donc une fenêtre d'erreur pour l'en informer.
            if (invalid) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nouvelle taille de tableau");
                alert.setHeaderText("Taille saisie invalide");
                alert.setContentText("Veuillez saisir une nouvelle taille comprise\nentre 2 et 11");
                alert.showAndWait();
            }
            result = dialog.showAndWait();
            invalid = true;
        } while (result.isPresent() && (Integer.parseInt(result.get()) < 2 || Integer.parseInt(result.get()) > 11));
        //La méthode isPresent() permet de déterminer si l'utilisateur a annulé sa saisie (a appuyé sur la croix rouge ou sur le bouton annuler).
        if (result.isPresent() ){
            this.toucan.setArraySize(Integer.parseInt(result.get()));
        }
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
                    menuItemChangeSize.setDisable(false);
                }
                else {
                    menuSelectionAlgo.setDisable(true);
                    menuItemGenerationValeurs.setDisable(true);
                    menuItemChangeSize.setDisable(true);
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
