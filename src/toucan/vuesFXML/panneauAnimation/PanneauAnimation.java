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
    private ArrayList<ParallelTransition> lesEtapesConstructeur; // stockage de la construction de l'animation
    private int derniereEtapeAnimee;
    private Duration duration;

    @FXML
    private BorderPane panneau ;

    /**
     * Construction du panneau d'animation
     * @param toucan modele contenant les cases
     */
    public PanneauAnimation(Toucan toucan) {
        this.toucan = toucan;
        this.toucan.addObserver(this);
        this.lesEtapesConstructeur = new ArrayList<>();
        this.derniereEtapeAnimee = 0;
        this.duration = new Duration(0.00);
    }

    /**
     * Initialisation des elements pour l'animation des cases
     * @throws Exception
     */
    @FXML
    public void initialize() {
        lesCasesAnimation = new LesCasesAnimation(panneau, toucan);
        this.derniereEtapeAnimee = 0;
        this.lesEtapesConstructeur = new ArrayList<>();
        this.duration = new Duration(0.00);
    }

    /**
     * Si c'est la premiere fois que cette methode est appelee dans un meme tri, alors elle cree une nouvelle animation.
     * Sinon, elle reprend l'animation existante et lui ajoute les nouvelles etapes qui n'existaient pas auparavant.
     * Lorsqu'elle ajoute de nouvelles animations a une liste d'animations existante, elle recree la liste avec les nouvelles
     * animations et reprend l'animation la ou elle etait par le biais du champ 'duration'.
     */
    protected void dessiner() {
        if (this.derniereEtapeAnimee != 0) { // Recupere la position en temps de l'animation en cours (si il y a une)
            this.duration = this.mouv.getCurrentTime();
            mouv.stop();
        }
        for (int i = this.derniereEtapeAnimee ; i < toucan.getNbMaxEtapes() ; i++) {
            this.lesEtapesConstructeur.add(lesCasesAnimation.animerLesCases(i+1)) ; // Construction de l'animation
            this.derniereEtapeAnimee++;
        }
        ParallelTransition[] lesEtapes = new ParallelTransition[this.lesEtapesConstructeur.size()];
        lesEtapes = this.lesEtapesConstructeur.toArray(lesEtapes); // Creation de l'animation a jouer
        mouv = new SequentialTransition(lesEtapes) ; // Injection de l'animation
        if (this.derniereEtapeAnimee != 0) { // Reprend l'animation la ou elle s'est arretee si on a rajoute des etapes
            mouv.jumpTo(this.duration);
        }
        else { // Nouvelle animation
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
                    default:                  // (StatutAnimation.FINIE) Stoppe l'animation lorsqu'il n'y a plus de mouvements
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
