package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static java.lang.Thread.sleep;
import static toucan.modele.animation.AttributAnimation.*;

public class AlgoShell extends Algo {

    /**
     * Constructeur d'un algorithme de tri de shell
     */
    public AlgoShell(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri de Shell";
        this.caseTempForcee = true;
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    int n = 0;
                    while (n < tabEntiers.length) {
                        n = 3 * n + 1;
                    }
                    while(n != 0) {
                        n /= 3;
                        for (int i = n ; i < tabEntiers.length ; i++) {
                            int valeur = tabEntiers[i];
                            executerAux(AFFECTATIONCVAL, i);
                            int j = i;
                            while((j > (n - 1)) && (tabEntiers[j - n] > valeur)) {
                                tabEntiers[j] = tabEntiers[j - n];
                                executerAux(AFFECTATIONECRASEMENTCASECASE, j - n, j);
                                j = j - n;
                            }
                            tabEntiers[j] = valeur;
                            executerAux(AFFECTATIONVCASE, j);
                        }
                        Thread.sleep(0);
                    }

                    return null;
                }
            };

            GestionThreads.getInstance().lancer(task);
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
