package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoTest extends Algo {

    /**
     * Constructeur d'un algorithme de test
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public AlgoTest(LesCases lesCases, int...  entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        this.executerAux(AFFECTATIONCVAL, 0);
        this.executerAux(AFFECTATIONECRASEMENTCASECASE, 0, 1);
        this.executerAux(AFFECTATIONECRASEMENTCASECASE, 3, 6);
        this.executerAux(AFFECTATIONECRASEMENTCASECASE, 7, 2);
        this.executerAux(AFFECTATIONVCASE, 4);

    }
}
