package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.AFFECTATION;
import static toucan.modele.animation.AttributAnimation.COMPARAISON;

public class AlgoTest extends Algo {

    /**
     * Constructeur d'un algorithme de test
     * @param lesCases cases du Toucan
     */
    public AlgoTest(LesCases lesCases, int...  entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        this.executerAux(AFFECTATION, 1, 4);
        this.executerAux(COMPARAISON, 3, 0);
        this.executerAux(AFFECTATION, 2, 4);
        this.executerAux(AFFECTATION, 1);
        this.executerAux(AFFECTATION, 7, 8);
        this.executerAux(COMPARAISON, 4, 6);
        this.executerAux(AFFECTATION, 3);
        this.executerAux(AFFECTATION, 0, 4);
        this.executerAux(COMPARAISON, 1, 2);
        this.executerAux(AFFECTATION, 7);
        this.executerAux(COMPARAISON, 3, 8);
        this.executerAux(AFFECTATION, 2, 6);
        this.executerAux(AFFECTATION, 1, 0);
        this.executerAux(AFFECTATION, 6, 3);
        this.executerAux(COMPARAISON, 3, 2);
        this.executerAux(AFFECTATION, 3, 1);
        this.executerAux(AFFECTATION, 4, 0);
        this.executerAux(AFFECTATION, 4, 1);
        this.executerAux(COMPARAISON, 4, 0);
        this.executerAux(COMPARAISON, 5, 6);
        this.executerAux(AFFECTATION, 2);
        this.executerAux(COMPARAISON, 8, 6);
        this.executerAux(AFFECTATION, 0);
        this.executerAux(AFFECTATION, 8, 2);
        this.executerAux(AFFECTATION, 3, 1);
        this.executerAux(AFFECTATION, 5);
        this.executerAux(COMPARAISON, 4, 3);
        this.executerAux(AFFECTATION, 1, 7);
        this.executerAux(COMPARAISON, 8, 4);
        this.executerAux(COMPARAISON, 3, 5);
        this.executerAux(AFFECTATION, 8);
        this.executerAux(COMPARAISON, 4, 2);
        this.executerAux(AFFECTATION, 8, 7);
        this.executerAux(AFFECTATION, 2, 0);
        this.executerAux(AFFECTATION, 4, 1);
        this.executerAux(COMPARAISON, 0, 1);
        this.executerAux(AFFECTATION, 3, 7);
        this.executerAux(AFFECTATION, 4);
        this.executerAux(AFFECTATION, 4, 2);
        this.executerAux(AFFECTATION, 1, 5);
        this.executerAux(COMPARAISON, 7, 4);
        this.executerAux(AFFECTATION, 2);
        this.executerAux(COMPARAISON, 1, 2);
        this.executerAux(AFFECTATION, 6);
        this.executerAux(COMPARAISON, 0, 3);
        this.executerAux(AFFECTATION, 4);
        this.executerAux(AFFECTATION, 0, 8);
        this.executerAux(AFFECTATION, 1, 4);
    }
}
