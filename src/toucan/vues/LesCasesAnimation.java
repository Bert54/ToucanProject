package toucan.vues;

import javafx.animation.ParallelTransition;

import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import toucan.modele.Case;
import toucan.modele.Toucan;

import java.util.ArrayList;

public class LesCasesAnimation {

    protected ArrayList<CaseAnimation> lesCases;
    protected Toucan toucan;

    /**
     * Constructeur
     * @param panneau panneau dans lequel on visualise les animations
     * @param modele modele contenant les cases
     */
    public LesCasesAnimation(BorderPane panneau, Toucan modele) {
        this.toucan = modele;
        // ménage du panneau pour une instanciation préalable de cases d'animation
        panneau.getChildren().removeAll(panneau.getChildren()) ;        // panneau.getCenter()
        int nbCases = toucan.nbCases();
        lesCases = new ArrayList<>(nbCases);
        for (int i = 0 ; i < nbCases ; i++) {
            lesCases.add(new CaseAnimation(panneau, this.toucan.getCase(i))) ;
        }
    }

    /**
     * visualisation de l'animation des cases
     * @param noEtape numero de l'etape a animer
     * @return l'animation parallele de toutes les cases a animer pour cette etape
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
