package toucan.vuesFXML.panneauControles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import toucan.modele.Toucan;

public class ControlesControleur {

    private Image imagePlay;
    private Image imagePause;
    public Toucan toucan;

    @FXML
    public Button boutonPlayPause;
    @FXML
    public ImageView playPauseImage;

    /**
     * Constructeur
     * @param mod modele
     */
    public ControlesControleur(Toucan mod) {
        this.toucan = mod;
        this.imagePlay = new Image(getClass().getResource("/toucan/ressources/play.jpg").toString());
        this.imagePause = new Image(getClass().getResource("/toucan/ressources/pause.jpg").toString());
    }

    /**
     * Change l'etat du bouton avec un clic
     */
    @FXML
    public void toggleAnimation() {
        if (this.toucan.getEtatExecution()) {
            this.toucan.changetExecution(false);
            this.playPauseImage.setImage(this.imagePlay);
        }
        else {
            this.toucan.changetExecution(true);
            this.playPauseImage.setImage(this.imagePause);
        }
    }

}
