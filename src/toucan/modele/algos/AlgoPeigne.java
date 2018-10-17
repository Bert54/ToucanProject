package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoPeigne extends Algo{

    /**
     * Constructeur d'un algorithme de tri a peigne
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public AlgoPeigne(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        int gap = this.tabEntiers.length;
        boolean encore = true;
        int temp;
        while (encore || gap > 1) {
            encore = false;
            gap /= 1.3; // Facteur de reduction. Peut etre remplace par un flottant comprit entre 1.25 et 1.3.
            if (gap < 1) {
                gap = 1;
            }
            for (int i = 0 ; i < (this.tabEntiers.length) - gap; i++) {
                this.executerAux(COMPARAISON, i, i+ gap);
                if (this.tabEntiers[i] > this.tabEntiers[i + gap]){
                    encore = true;
                    temp = this.tabEntiers[i];
                    this.tabEntiers[i] = this.tabEntiers[i + gap];
                    this.tabEntiers[i + gap] = temp;
                    if (this.lesCases.variableTempActivee()) {
                        this.executerAux(AFFECTATIONCVAL, i);
                        this.executerAux(AFFECTATIONECRASEMENTCASECASE, i + gap, i);
                        this.executerAux(AFFECTATIONVCASE, i + gap);
                    }
                    else {
                        this.executerAux(AFFECTATION, i, i + gap);
                    }
                }
            }
        }
    }
}
