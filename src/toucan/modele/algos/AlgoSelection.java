package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoSelection extends Algo {

    /**
     * Constructeur d'un algorithme de tri par selection
     */
    public AlgoSelection(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri par Sélection";
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    long startTime = System.nanoTime();
                    int min;
                    int temp;
                    for (int i = 0; i < tab.length - 1 ; i++) {
                        min = i;
                        for (int j = min; j < tab.length ; j++) {
                            if (min != j) {
                                executerAux(COMPARAISON, min, j);
                            }
                            if (tab[j] < tab[min]) {
                                min = j;
                            }
                        }
                        if (min != i) {
                            if (lesCases.variableTempActivee()) {
                                executerAux(AFFECTATIONCVAL, i);
                                executerAux(AFFECTATIONECRASEMENTCASECASE, min, i);
                                executerAux(AFFECTATIONVCASE, min);
                            }
                            else {
                                executerAux(AFFECTATION, min, i);
                            }
                            temp = tab[i];
                            tab[i] = tab[min];
                            tab[min] = temp;
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
}
