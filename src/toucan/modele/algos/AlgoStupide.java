package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import java.util.Random;

import static java.lang.Thread.sleep;
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

                    Random random = new Random();
                    int temp;
                    int rVal1;
                    int rVal2;
                    while (!estTrie()) {
                        rVal1 = random.nextInt(tabEntiers.length - 1);
                        do {
                            rVal2 = random.nextInt(tabEntiers.length - 1);
                        } while(rVal1 == rVal2);
                        temp = tabEntiers[rVal1];
                        tabEntiers[rVal1] = tabEntiers[rVal2];
                        tabEntiers[rVal2] = temp;
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
     * Verifie que la tableau est trie (propre au tri stupide).
     * @return Tableau trie ou tableau non triee
     */
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
