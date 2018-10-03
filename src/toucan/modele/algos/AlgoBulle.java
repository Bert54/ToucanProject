package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.AFFECTATION;
import static toucan.modele.animation.AttributAnimation.COMPARAISON;

public class AlgoBulle extends Algo {

    /**
     * Constructeur d'un algorithme de tri à bulles
     * @param lesCases cases du Toucan
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
                        this.executerAux(AFFECTATION, j, j+1);
                        this.tabEntiers[j] = this.tabEntiers[j+1] ;
                        this.tabEntiers[j+1] = var ;
                        encore = true ;
                    }
                }
                n = n-1 ;
            }
    }
}
