package toucan.modele.algos;

import toucan.modele.LesCases;

import java.util.Random;

import static toucan.modele.animation.AttributAnimation.*;


public class AlgoStupide  extends Algo {

    /**
     * Constructeur d'un algorithme stupide
     * @param lesCases case du Toucan
     * @param entiers tableaux des entiers
     */
    public AlgoStupide(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        Random random = new Random();
        int temp;
        int rVal1;
        int rVal2;
        while (!estTrie()) {
            rVal1 = random.nextInt(this.tabEntiers.length - 1);
            do {
                rVal2 = random.nextInt(this.tabEntiers.length - 1);
            } while(rVal1 == rVal2);
            temp = tabEntiers[rVal1];
            tabEntiers[rVal1] = tabEntiers[rVal2];
            tabEntiers[rVal2] = temp;
            this.executerAux(AFFECTATION, rVal1, rVal2);
        }
    }

    public boolean estTrie() {
        boolean trier = true;
        for (int i = 0; i < this.tabEntiers.length - 1 ; i++) {
            this.executerAux(COMPARAISON, i, i+1);
            if (this.tabEntiers[i] > this.tabEntiers[i+1]) {
                trier = false;
            }
        }
        return trier;
    }
}
