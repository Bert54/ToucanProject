package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoShell extends Algo {

    /**
     * Constructeur d'un algorithme de tri de shell
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public AlgoShell(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        int n = 0;
        while (n < this.tabEntiers.length) {
            n = 3 * n + 1;
        }
        while(n != 0) {
            n /= 3;
            for (int i = n ; i < this.tabEntiers.length ; i++) {
                int valeur = this.tabEntiers[i];
                this.executerAux(AFFECTATIONCVAL, i);
                int j = i;
                while((j > (n - 1)) && (this.tabEntiers[j - n] > valeur)) {
                    this.tabEntiers[j] = this.tabEntiers[j - n];
                    this.executerAux(AFFECTATIONECRASEMENTCASECASE, j - n, j);
                    j = j - n;
                }
                this.tabEntiers[j] = valeur;
                this.executerAux(AFFECTATIONVCASE, j);
            }
        }
    }
}
