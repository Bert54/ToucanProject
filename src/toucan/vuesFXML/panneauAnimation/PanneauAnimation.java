package toucan.vuesFXML.panneauAnimation;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import toucan.modele.Toucan;
import toucan.vues.LesCasesAnimation;

public class PanneauAnimation {

    private LesCasesAnimation lesCasesAnimation;
    protected Toucan toucan;
    @FXML
    private BorderPane panneau ;

    /**
     * Constructeur
     * @param modele modele contenant les cases
     */
    public PanneauAnimation(Toucan modele) {
        this.toucan = modele;
    }

    @FXML
    public void initialize() throws Exception {
        lesCasesAnimation = new LesCasesAnimation(panneau, toucan);
    }

    protected void dessiner() {
        ParallelTransition[] lesEtapes = new ParallelTransition[toucan.getNbMaxEtapes()] ;

        for (int i = 0 ; i < lesEtapes.length ; i++) {
            lesEtapes[i] = lesCasesAnimation.animerLesCases(i+1) ;
        }

        SequentialTransition mouv = new SequentialTransition(lesEtapes) ;
        mouv.setDelay(Duration.ZERO);
        mouv.play() ;

        mouv.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // à modifier pour que l'animation s'arrête...
                mouv.setRate(-1) ;
                //mouv.play() ;
            }
        });
    }

    public void run() {
        dessiner() ;
    }
}
