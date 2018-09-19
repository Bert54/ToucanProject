package toucan.vues;

import javafx.animation.ParallelTransition;

import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import toucan.modele.Case;
import java.util.ArrayList;

public class LesCasesAnimation {

    protected ArrayList<CaseAnimation> lesCases;

    /**
     * Constructeur
     * @param panneau panneau dans lequel on visualise les animations
     */
    public LesCasesAnimation(BorderPane panneau) {

        // ménage du panneau pour une instanciation préalable de cases d'animation
        panneau.getChildren().removeAll(panneau.getChildren()) ;

        lesCases = new ArrayList<>(6);
        for (int i = 0 ; i < 10 ; i++) {
            lesCases.add(new CaseAnimation(panneau, new Case())) ;
        }
    }

    /**
     * visualisation de l'animation des cases
     * @param noEtape numéro de l'étape à animer
     * @return l'animation parallèle de toutes les cases à animer pour cette étape
     */
    public ParallelTransition animerLesCases(int noEtape) {
        ArrayList<ParallelTransition> lesAnimations = new ArrayList<>() ;

        for (CaseAnimation ca : lesCases) {
            if (ca.existeAnimation(noEtape)) {
                lesAnimations.add(ca.animerUneCase(noEtape));
            }
        }

        ParallelTransition[] tab = new ParallelTransition[lesAnimations.size()] ;
        for (int i = 0 ; i < tab.length ; i++) {
            tab[i] = lesAnimations.get(i) ;
        }
        ParallelTransition mouv = new ParallelTransition(tab) ;
        mouv.setDelay(Duration.ZERO) ;
        return mouv;
    }

}
