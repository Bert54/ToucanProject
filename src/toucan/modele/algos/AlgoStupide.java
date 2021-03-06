package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import java.util.Random;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoStupide  extends Algo {

    /**
     * Constructeur d'un algorithme stupide
     */
    public AlgoStupide(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri Stupide";
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    long startTime = System.nanoTime();
                    Random random = new Random();
                    int temp;
                    int rVal1;
                    int rVal2;
                    while (!estTrie()) {
                        rVal1 = random.nextInt(tab.length - 1);
                        do {
                            rVal2 = random.nextInt(tab.length - 1);
                        } while(rVal1 == rVal2);
                        temp = tab[rVal1];
                        tab[rVal1] = tab[rVal2];
                        tab[rVal2] = temp;
                        if (lesCases.variableTempActivee()) {
                            executerAux(AFFECTATIONCVAL, rVal1);
                            executerAux(AFFECTATIONECRASEMENTCASECASE, rVal2, rVal1);
                            executerAux(AFFECTATIONVCASE, rVal2);
                        }
                        else {
                            executerAux(AFFECTATION, rVal1, rVal2);
                        }
                        Thread.sleep(5);
                        toucan.prevenirVues();
                    }
                    long endTime = System.nanoTime();
                    toucan.setExecTime(endTime - startTime);
                    return null;
                }
            };
            Thread.sleep(5);
            toucan.prevenirVues();
            GestionThreads.getInstance().lancer(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifie que la tableau est trie (propre au tri stupide)
     * @return vrai si le tableau trie
     */
    public boolean estTrie() {
        boolean trier = true;
        for (int i = 0; i < this.tab.length - 1 ; i++) {
            this.executerAux(COMPARAISON, i, i+1);
            if (this.tab[i] > this.tab[i+1]) {
                trier = false;
            }
        }
        return trier;
    }
}
