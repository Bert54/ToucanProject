package toucan.vuesFXML.panneauControles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import toucan.modele.Toucan;

import java.util.Observable;
import java.util.Observer;

public class ControlesControleur implements Observer {

    private Image imagePlay;
    private Image imagePause;
    private Image imageReset;
    private boolean majEnCours;
    public Toucan toucan;

    @FXML
    public Button boutonPlayPause;
    @FXML
    public ImageView playPauseImage;
    @FXML
    public Label nomAlgoLabel;
    @FXML
    public CheckBox varTempCheckBox;

    /**
     * Constructeur de la vue. ImagePlay, imagePause et ImageReset sont definies ici, permettant
     * ainsi d'eviter de devoir les reinstancier a chaque mise a jour de la vue
     * @param mod Modele
     */
    public ControlesControleur(Toucan mod) {
        this.toucan = mod;
        this.toucan.addObserver(this);
        this.imagePlay = new Image(getClass().getResource("/toucan/ressources/play.jpg").toString());
        this.imagePause = new Image(getClass().getResource("/toucan/ressources/pause.jpg").toString());
        this.imageReset = new Image(getClass().getResource("/toucan/ressources/rewind.jpg").toString());
        this.majEnCours = false;
    }

    @FXML
    public void initialize() throws Exception {
        this.updateLabel();
        this.algoVariableTempDetection();
    }

    /**
     * Methode associee au bouton permettant d'instancier les mouvements, de jouer l'animation ou de mettre en pause
     * l'animation
     */
    @FXML
    public void toggleAnimation() {
        switch (this.toucan.getStatutAnimation()) {
            case NON_INITIALISEE:   // Creation des mouvements (codee en dur pour le moment, sera changee plus tard)
                this.toucan.creerLesMouvements();
                this.toucan.setStatutAnimation(1);
                break;
            case EN_COURS_ACTIF:    // Mise en pause de l'animation
                this.toucan.setStatutAnimation(2);
                break;
            case EN_COURS_PAUSE:    // Reprise de l'animation
                this.toucan.setStatutAnimation(1);
                break;
            case FINIE:             // Reinitialisation de l'animation
                this.toucan.setStatutAnimation(0);
        }
    }

    public void algoVariableTempDetection() {
        if (!this.majEnCours) {
            this.majEnCours = true; // Permet d'eviter une boucle infinie
            switch (this.toucan.getAlgoActuel()) {
                case ALGOINSERTION:
                    this.varTempCheckBox.setSelected(true);
                    this.toggleVariableTemp();
                    this.varTempCheckBox.setDisable(true);
                    break;
                default:
                    this.varTempCheckBox.setDisable(false);
            }
            this.majEnCours = false;
        }
    }

    /**
     * Met a jour le label affichant l'algo en cours
     */
    public void updateLabel() {
        switch (this.toucan.getAlgoActuel()) {
            case ALGOBULLE:
                this.nomAlgoLabel.setText("Tri à Bulles");
                break;
            case ALGOTEST:
                this.nomAlgoLabel.setText("Tri de Test");
                break;
            case ALGOSTUPIDE:
                this.nomAlgoLabel.setText("Tri Stupide");
                break;
            case ALGOINSERTION:
                this.nomAlgoLabel.setText("Tri par Insertion");
                break;
            case ALGOSELECTION:
                this.nomAlgoLabel.setText("Tri par Sélection");
                break;
            case ALGOCOCKTAIL:
                this.nomAlgoLabel.setText("Tri Cocktail");
                break;
        }
    }

    @FXML
    public void toggleVariableTemp() {
        if (this.varTempCheckBox.isSelected()) {
            this.toucan.setVariableTemp(true);
        }
        else {
            this.toucan.setVariableTemp(false);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (this.toucan.getStatutAnimation()) {
            case EN_COURS_ACTIF:
                this.playPauseImage.setImage(this.imagePause);
                this.varTempCheckBox.setDisable(true);
                break;
            case FINIE:
                this.playPauseImage.setImage(this.imageReset);
                this.varTempCheckBox.setDisable(true);
                break;
            case EN_COURS_PAUSE:
                this.playPauseImage.setImage(this.imagePlay);
                this.varTempCheckBox.setDisable(true);
                break;
            case NON_INITIALISEE:
                this.playPauseImage.setImage(this.imagePlay);
                this.algoVariableTempDetection();
        }
        this.updateLabel();
    }
}
