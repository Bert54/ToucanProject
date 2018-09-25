package toucan.vuesFXML.panneauControles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    /**
     * Constructeur de la vue. ImagePlay, imagePause et ImageReset sont definies ici, permettant
     * ainsi d'eviter de devoir les reinstancier a chaque mise a jour de la vue.
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
     * Methode associee au bouton permettant de soit instancier les mouvements, jouer l'animation ou mettre en pause
     * l'animation.
     */
    @FXML
    public void toggleAnimation() {
        switch (this.toucan.getStatutAnimation()) {
            case NON_INITIALISEE: // Creation des mouvements (codee en dur pour le moment, sera changee plus tard)
                this.toucan.creerLesMouvements(
                        0, 1, Toucan.EST, 50,
                        //0, 2, Toucan.STABLE, 23,
                        1, 4, Toucan.SUD, 50,
                        1, 1, Toucan.OUEST, 50,
                        3, 6, Toucan.NORD, 50);
                //1, 5, Toucan.STABLE, 9);
                this.toucan.setStatutAnimation(1);
                break;
            case EN_COURS_ACTIF: // Mise en pause de l'animation
                this.toucan.setStatutAnimation(2);
                break;
            case EN_COURS_PAUSE: // Reprise de l'animation
                this.toucan.setStatutAnimation(1);
                break;
            case FINIE: // Reinitialisation de l'animation
                this.toucan.setStatutAnimation(0);
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
    }
}
