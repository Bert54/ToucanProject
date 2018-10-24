package toucan.vuesFXML.panneauControles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
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
    public Button boutonStop;
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

    /**
     * Permet de forcer le mode de visualisation avec variable temporaire si l'algorithme actif du modele l'oblige
     */
    public void algoVariableTempDetection() {
        if (!this.majEnCours) {
            this.majEnCours = true; // Permet d'eviter une boucle infinie
            if (this.toucan.varTempForceActif()) {
                this.varTempCheckBox.setSelected(true);
                this.toggleVariableTemp();
                this.varTempCheckBox.setDisable(true);
            }
            else {
                this.varTempCheckBox.setDisable(false);
            }
            this.majEnCours = false;
        }
    }

    /**
     * Met a jour le label affichant l'algo en cours
     */
    public void updateLabel() {
        this.nomAlgoLabel.setText(this.toucan.getAlgoActuel());
    }

    /**
     * Change le mode de mode de visualitation de l'animation
     */
    @FXML
    public void toggleVariableTemp() {
        if (this.varTempCheckBox.isSelected()) {
            this.toucan.setVariableTemp(true);
        }
        else {
            this.toucan.setVariableTemp(false);
        }
    }

    @FXML
    public void reinitialiseAnimation() {
        this.toucan.setStatutAnimation(0);
    }

    @Override
    public void update(Observable o, Object arg) {

        Runnable command = new Runnable() {
            @Override
            public void run() {
                switch (toucan.getStatutAnimation()) {
                    case EN_COURS_ACTIF:
                        playPauseImage.setImage(imagePause);
                        varTempCheckBox.setDisable(true);
                        boutonStop.setDisable(true);
                        break;
                    case FINIE:
                        playPauseImage.setImage(imageReset);
                        varTempCheckBox.setDisable(true);
                        boutonStop.setDisable(true);
                        break;
                    case EN_COURS_PAUSE:
                        playPauseImage.setImage(imagePlay);
                        varTempCheckBox.setDisable(true);
                        boutonStop.setDisable(false);
                        break;
                    case NON_INITIALISEE:
                        playPauseImage.setImage(imagePlay);
                        boutonStop.setDisable(true);
                        algoVariableTempDetection();
                }
                updateLabel();
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
