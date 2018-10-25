package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoInsertion extends Algo {

    /**
     * Constructeur d'un algorithme de tri par insertion
     */
    public AlgoInsertion(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri par Insertion";
        this.caseTempForcee = true;
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    int mem;
                    int j;
                    for (int i = 1 ; i < tabEntiers.length ; i++) {
                        mem = tabEntiers[i];
                        executerAux(AFFECTATIONCVAL, i);
                        j = i - 1;
                        while (j >= 0 && tabEntiers[j] > mem) {
                            tabEntiers[j + 1] = tabEntiers[j];
                            executerAux(AFFECTATIONECRASEMENTCASECASE, j, j + 1);
                            j--;
                        }
                        tabEntiers[j + 1] = mem;
                        executerAux(AFFECTATIONVCASE, j + 1);
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
}
