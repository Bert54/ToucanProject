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

import java.util.Observable;
import java.util.Observer;

public class PanneauAnimation implements Observer {

    private LesCasesAnimation lesCasesAnimation;
    protected Toucan toucan;
    private SequentialTransition mouv;
    @FXML
    private BorderPane panneau ;

    /**
     * Constructeur
     * @param modele modele contenant les cases
     */
    public PanneauAnimation(Toucan modele) {
        this.toucan = modele;
        this.toucan.addObserver(this);
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
        mouv = new SequentialTransition(lesEtapes) ;
        mouv.setDelay(Duration.ZERO);
        mouv.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toucan.setStatutAnimation(3); // Animations finies
            }
        });
    }

    public void run() {
        dessiner() ;
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (this.toucan.getStatutAnimation()) {
            case NON_INITIALISEE: //Initialise les animations des cases
                try {
                    this.initialize();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.run();
                break;
            case EN_COURS_ACTIF: //Joue l'animation
                this.mouv.play();
                break;
            case EN_COURS_PAUSE: // Met en pause l'animation
                this.mouv.pause();
                break;
            default: // (StatutAnimation.FINIT) Stoppe l'animation lorsqu'il n'y a plus de mouvements
                mouv.setRate(-1) ;
                mouv.stop();
        }
    }

}
