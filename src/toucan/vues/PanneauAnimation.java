package toucan.vues;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class PanneauAnimation extends BorderPane {

    private LesCasesAnimation lesCasesAnimation;

    public PanneauAnimation() {
        lesCasesAnimation = new LesCasesAnimation(this);
    }

    protected void dessiner() {
        ParallelTransition[] lesEtapes = new ParallelTransition[50] ;

        for (int i = 0 ; i < lesEtapes.length ; i++) {
            lesEtapes[i] = lesCasesAnimation.animerLesCases(i+1) ;
        }

        SequentialTransition mouv = new SequentialTransition(lesEtapes) ;
        mouv.setDelay(Duration.ZERO);
        mouv.play() ;

        mouv.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mouv.setRate(-1) ;
                mouv.play() ;
            }
        });
    }

    public void run() {
        dessiner() ;
    }

}

