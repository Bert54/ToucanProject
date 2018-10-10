package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoInsertion extends Algo {

    public AlgoInsertion(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        int mem;
        int j;
        for (int i = 1 ; i < this.tabEntiers.length ; i++) {
            mem = this.tabEntiers[i];
            this.executerAux(AFFECTATIONCVAL, i);
            j = i - 1;
            while (j >= 0 && this.tabEntiers[j] > mem) {
                this.tabEntiers[j + 1] = this.tabEntiers[j];
                this.executerAux(AFFECTATIONECRASEMENTCASECASE, j, j + 1);
                j--;
            }
            this.tabEntiers[j + 1] = mem;
            this.executerAux(AFFECTATIONVCASE, j + 1);
        }
    }
}
