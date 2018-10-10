package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoSelection extends Algo {

    public AlgoSelection(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        int min;
        int temp;
        for (int i = 0 ; i < this.tabEntiers.length - 1 ; i++) {
            min = i;
            for (int j = min ; j < this.tabEntiers.length ; j++) {
                if (min != j) {
                    this.executerAux(COMPARAISON, min, j);
                }
                if (this.tabEntiers[j] < this.tabEntiers[min]) {
                    min = j;
                }
            }
            if (min != i) {
                this.executerAux(AFFECTATION, min, i);
                temp = this.tabEntiers[i];
                this.tabEntiers[i] = this.tabEntiers[min];
                this.tabEntiers[min] = temp;
            }
        }
    }
}
