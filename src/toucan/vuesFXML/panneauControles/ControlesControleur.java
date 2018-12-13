package toucan.vuesFXML.panneauControles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Toucan toucan;

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
    @FXML
    public Slider sliderVitesse;
    @FXML
    public Label labelExecTime;
    @FXML
    public Label labelExecTimeValue;
    @FXML
    public CheckBox viewAlgoTimeEll;

    /**
     * Constructeur de la vue. ImagePlay, imagePause et ImageReset sont definies ici, permettant
     * ainsi d'eviter a devoir les reinstancier a chaque mise a jour de la vue
     * @param toucan Modele
     */
    public ControlesControleur(Toucan toucan) {
        this.toucan = toucan;
        this.toucan.addObserver(this);
        this.imagePlay = new Image(getClass().getResource("/toucan/ressources/play.jpg").toString());
        this.imagePause = new Image(getClass().getResource("/toucan/ressources/pause.jpg").toString());
        this.imageReset = new Image(getClass().getResource("/toucan/ressources/rewind.jpg").toString());
        this.majEnCours = false;
    }

    /**
     * Initialisation des elements de l'interface
     * @throws Exception
     */
    @FXML
    public void initialize() throws Exception {
        this.updateLabel();
        this.setVitesse();
        this.algoVariableTempDetection();
        this.toggleAlgorithmExecTimeView();
    }

    /**
     * Methode associee au bouton permettant d'instancier les mouvements, de jouer l'animation ou de mettre en pause l'animation
     */
    @FXML
    public void toggleAnimation() throws InterruptedException {
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
     * Force le mode de visualisation avec variable temporaire si l'algorithme actif du modele l'oblige
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
     * Met à jour le label qui affiche le temps d'exécution de l'algorithme sélectionné
     */
    public void updateExecTimeLabel() {
        if (this.toucan.getExecTime() != -1.00) {
            double timeElapsed = this.toucan.getExecTime() / 1000000000;
            int minutes = 0;
            int seconds = 0;
            int milSeconds = 0;
            while (timeElapsed > 60.00) {
                minutes += 1;
                timeElapsed -= 60.00;
            }
            while (timeElapsed > 1.00) {
                seconds += 1;
                timeElapsed -= 1.00;
            }
            milSeconds = (int) (timeElapsed * 1000);
            String minutesPlur = "minute";
            String secondsPlur = "seconde";
            String milSecondsPlus = "milliseconde";
            if (minutes > 1) {
                minutesPlur += "s";
            }
            if (seconds > 1) {
                secondsPlur += "s";
            }
            if (milSeconds > 1) {
                milSecondsPlus += "s";
            }
            labelExecTimeValue.setText(minutes + " " + minutesPlur + " " + seconds + " " + secondsPlur + " " + milSeconds + " " + milSecondsPlus);
        }
        else {
            labelExecTimeValue.setText("NaN");
        }
    }

    /**
     * Met a jour le label affichant l'algo en cours
     */
    public void updateLabel() {
        this.nomAlgoLabel.setText(this.toucan.getAlgoActuel());
    }

    /**
     * Change le mode de visualitation de l'animation dans le modele
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

    /**
     * Change si on veut visualiser le temps de l'exécution de l'algorithme sélectionné ou non
     */
    @FXML
    public void toggleAlgorithmExecTimeView() {
        if (viewAlgoTimeEll.isSelected()) {
            this.labelExecTimeValue.setVisible(true);
            this.labelExecTime.setVisible(true);
        }
        else {
            this.labelExecTimeValue.setVisible(false);
            this.labelExecTime.setVisible(false);
        }
    }

    /**
     * Fixe la vitesse de l'animation
     */
    @FXML
    public void setVitesse() {
        this.toucan.setVitesse((int)sliderVitesse.getValue(), (int)sliderVitesse.getMax()+1);
    }

    /**
     * Reinitialise les animations
     */
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
                        sliderVitesse.setDisable(true);
                        viewAlgoTimeEll.setDisable(true);
                        updateExecTimeLabel();
                        break;
                    case FINIE:
                        playPauseImage.setImage(imageReset);
                        varTempCheckBox.setDisable(true);
                        boutonStop.setDisable(true);
                        sliderVitesse.setDisable(true);
                        viewAlgoTimeEll.setDisable(true);
                        updateExecTimeLabel();
                        break;
                    case EN_COURS_PAUSE:
                        playPauseImage.setImage(imagePlay);
                        varTempCheckBox.setDisable(true);
                        boutonStop.setDisable(false);
                        sliderVitesse.setDisable(true);
                        viewAlgoTimeEll.setDisable(true);
                        updateExecTimeLabel();
                        break;
                    case NON_INITIALISEE:
                        playPauseImage.setImage(imagePlay);
                        boutonStop.setDisable(true);
                        algoVariableTempDetection();
                        sliderVitesse.setDisable(false);
                        viewAlgoTimeEll.setDisable(false);
                        updateExecTimeLabel();
                }
                updateLabel();
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        }
        else {
            Platform.runLater(command);
        }
    }
}
