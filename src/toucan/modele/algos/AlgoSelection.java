package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static java.lang.Thread.sleep;
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

                    int min;
                    int temp;
                    for (int i = 0 ; i < tabEntiers.length - 1 ; i++) {
                        min = i;
                        for (int j = min ; j < tabEntiers.length ; j++) {
                            if (min != j) {
                                executerAux(COMPARAISON, min, j);
                            }
                            if (tabEntiers[j] < tabEntiers[min]) {
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
                            temp = tabEntiers[i];
                            tabEntiers[i] = tabEntiers[min];
                            tabEntiers[min] = temp;
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
}
