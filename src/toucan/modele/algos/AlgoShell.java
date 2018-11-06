package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

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
                    while (n < tab.length) {
                        n = 3 * n + 1;
                    }
                    while(n != 0) {
                        n /= 3;
                        for (int i = n; i < tab.length ; i++) {
                            int valeur = tab[i];
                            executerAux(AFFECTATIONCVAL, i);
                            int j = i;
                            executerAux(COMPARAISONVALCASE, j - n);
                            while((j > (n - 1)) && (tab[j - n] > valeur)) {
                                tab[j] = tab[j - n];
                                executerAux(AFFECTATIONECRASEMENTCASECASE, j - n, j);
                                executerAux(COMPARAISONVALCASE, j - n);
                                j = j - n;
                            }
                            tab[j] = valeur;
                            executerAux(AFFECTATIONVCASE, j);
                        }
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
