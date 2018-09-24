package toucan.vuesFXML.panneauControles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import toucan.modele.Toucan;

public class ControlesControleur{

    private Image imagePlay;
    private Image imagePause;
    public Toucan toucan;
    private boolean animer;

    @FXML
    public Button boutonPlayPause;
    @FXML
    public ImageView playPauseImage;

    public ControlesControleur(Toucan mod) {
        this.toucan = mod;
        this.animer = false;
        this.imagePlay = new Image("/toucan/ressources/play.jpg");
        this.imagePause = new Image("/toucan/ressources/pause.jpg");
    }

    @FXML
    public void toggleAnimation() {
        if (animer) {
            this.playPauseImage.setImage(this.imagePlay);
            this.animer = false;
        }
        else {
            this.playPauseImage.setImage(this.imagePause);
            this.animer = true;
        }
    }

}
