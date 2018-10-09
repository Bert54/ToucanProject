package toucan.vuesFXML.panneauControles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import toucan.modele.StatutAnimation;
import toucan.modele.Toucan;

import java.util.Observable;
import java.util.Observer;

import static toucan.modele.StatutAnimation.FINIE;

public class ControlesControleur implements Observer {

    private Image imagePlay;
    private Image imagePause;
    private Image imageReset;
    public Toucan toucan;

    @FXML
    public Button boutonPlayPause;
    @FXML
    public ImageView playPauseImage;
    @FXML
    public Label nomAlgoLabel;

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
     * Met a jour le label
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
            case ALGOSELECTION:
                this.nomAlgoLabel.setText("Tri par Sélection");
                break;
            case ALGOINSERTION:
                this.nomAlgoLabel.setText("Tri par Insertion");
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (this.toucan.getStatutAnimation()) {
            case EN_COURS_ACTIF:
                this.playPauseImage.setImage(this.imagePause);
                break;
            case FINIE:
                this.playPauseImage.setImage(this.imageReset);
                break;
            default:
                this.playPauseImage.setImage(this.imagePlay);
        }
        this.updateLabel();
    }
}
