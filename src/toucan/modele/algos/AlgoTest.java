package toucan.modele.algos;

import toucan.modele.LesCases;
import toucan.modele.animation.AttributAnimation;

public class AlgoTest extends Algo {

    /**
     * Constructeur d'un algorithme
     * @param lesCases cases du Toucan
     */
    public AlgoTest(LesCases lesCases, int...  entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        this.executerAux(AttributAnimation.AFFECTATION, 1, 4);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 2, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 7, 8);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 3);
        this.executerAux(AttributAnimation.AFFECTATION, 0, 4);
        this.executerAux(AttributAnimation.COMPARAISON, 1, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 7);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 8);
        this.executerAux(AttributAnimation.AFFECTATION, 2, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 6, 3);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 3, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 1);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 0);
        this.executerAux(AttributAnimation.COMPARAISON, 5, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 2);
        this.executerAux(AttributAnimation.COMPARAISON, 8, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 8, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 3, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 5);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 3);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 7);
        this.executerAux(AttributAnimation.COMPARAISON, 8, 4);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 5);
        this.executerAux(AttributAnimation.AFFECTATION, 8);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 8, 7);
        this.executerAux(AttributAnimation.AFFECTATION, 2, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 1);
        this.executerAux(AttributAnimation.COMPARAISON, 0, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 3, 7);
        this.executerAux(AttributAnimation.AFFECTATION, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 5);
        this.executerAux(AttributAnimation.COMPARAISON, 7, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 2);
        this.executerAux(AttributAnimation.COMPARAISON, 1, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 6);
        this.executerAux(AttributAnimation.COMPARAISON, 0, 3);
        this.executerAux(AttributAnimation.AFFECTATION, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 0, 8);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 4);
    }
}
