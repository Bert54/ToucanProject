package toucan.vuesFXML.panneauAnimation;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import toucan.modele.Toucan;
import toucan.vues.LesCasesAnimation;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PanneauAnimation implements Observer {

    private LesCasesAnimation lesCasesAnimation;
    protected Toucan toucan;
    private SequentialTransition mouv;
    private ArrayList<ParallelTransition> lesEtapesConstructeur;
    private int derniereEtapeAnimee;
    private Duration duration;

    @FXML
    private BorderPane panneau ;

    /**
     * Constructeur
     * @param modele modele contenant les cases
     */
    public PanneauAnimation(Toucan modele) {
        this.toucan = modele;
        this.toucan.addObserver(this);
        this.lesEtapesConstructeur = new ArrayList<>();
        this.derniereEtapeAnimee = 0;
        this.duration = new Duration(0.00);
    }

    @FXML
    public void initialize() {
        lesCasesAnimation = new LesCasesAnimation(panneau, toucan);
        this.derniereEtapeAnimee = 0;
        this.lesEtapesConstructeur = new ArrayList<>();
        this.duration = new Duration(0.00);
    }

    /**
     * Si c'est la premiere fois que cette méthode est appelee dans un meme tri, alors elle cree une nouvelle animation
     * Sinon, elle reprend l'animation existante et lui ajoute les nouveaux etapes qui n'existaient pas auparavant.
     * Lorsqu'elle ajoute de nouvelles animations a une liste d'animations existantes, elle recree la liste avec les nouvelles
     * animations et reprend l'animation la ou elle etait par le biais du champ 'duration'.
     */
    protected void dessiner() {
        if (this.derniereEtapeAnimee != 0) {
            this.duration = this.mouv.getCurrentTime();
            mouv.stop();
        }
        for (int i = this.derniereEtapeAnimee ; i < toucan.getNbMaxEtapes() ; i++) {
            //lesEtapes[i] = lesCasesAnimation.animerLesCases(i+1) ;
            this.lesEtapesConstructeur.add(lesCasesAnimation.animerLesCases(i+1)) ;
            this.derniereEtapeAnimee++;
        }
        ParallelTransition[] lesEtapes = new ParallelTransition[this.lesEtapesConstructeur.size()];
        lesEtapes = this.lesEtapesConstructeur.toArray(lesEtapes);
        mouv = new SequentialTransition(lesEtapes) ;
        if (this.derniereEtapeAnimee != 0) {
            mouv.jumpTo(this.duration);
        }
        else{
            mouv.setDelay(Duration.ZERO);
        }
        mouv.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                derniereEtapeAnimee = 0;
                lesEtapesConstructeur.clear();
                toucan.setStatutAnimation(3); // Animations finies
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

        Runnable command = new Runnable() {

            @Override
            public void run() {
                switch (toucan.getStatutAnimation()) {
                    case NON_INITIALISEE:      //Initialise les animations des cases
                        derniereEtapeAnimee = 0;
                        lesEtapesConstructeur.clear();
                        duration = new Duration(0.00);
                        initialize();
                        break;
                    case EN_COURS_ACTIF:       //Joue l'animation
                        dessiner();
                        mouv.play();
                        break;
                    case EN_COURS_PAUSE:      // Met en pause l'animation
                        mouv.pause();
                        break;
                    default:                  // (StatutAnimation.FINIT) Stoppe l'animation lorsqu'il n'y a plus de mouvements
                        mouv.setRate(-1) ;
                        mouv.stop();
                        duration = new Duration(0.00);
                        derniereEtapeAnimee = 0;
                        lesEtapesConstructeur.clear();
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
