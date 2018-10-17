package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoBulle extends Algo {

    /**
     * Constructeur d'un algorithme de tri à bulles
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public AlgoBulle(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        boolean encore = true ;
        int n = this.lesCases.nbCases() ;
        while (encore) {
            encore = false ;
            for (int j = 0 ; j < n - 1; j++) {
                this.executerAux(COMPARAISON, j, j+1);
                if (this.tabEntiers[j] > this.tabEntiers[j+1]) {
                    int var = this.tabEntiers[j] ;
                    this.tabEntiers[j] = this.tabEntiers[j+1] ;
                    this.tabEntiers[j+1] = var ;
                    if (this.lesCases.variableTempActivee()) {
                        this.executerAux(AFFECTATIONCVAL, j);
                        this.executerAux(AFFECTATIONECRASEMENTCASECASE, j + 1, j);
                        this.executerAux(AFFECTATIONVCASE, j + 1);
                    }
                    else {
                        this.executerAux(AFFECTATION, j, j + 1);
                    }
                    encore = true ;
                }
            }
            n = n-1 ;
        }
    }
}
