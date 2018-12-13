package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoPeigne extends Algo{

    /**
     * Constructeur d'un algorithme de tri a peigne
     */
    public AlgoPeigne(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri à Peigne";
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    long startTime = System.nanoTime();
                    int gap = tab.length;
                    boolean encore = true;
                    int temp;
                    while (encore || gap > 1) {
                        encore = false;
                        gap /= 1.3; // Facteur de reduction. Peut etre remplace par un flottant comprit entre 1.25 et 1.3.
                        if (gap < 1) {
                            gap = 1;
                        }
                        for (int i = 0; i < (tab.length) - gap; i++) {
                            executerAux(COMPARAISON, i, i+ gap);
                            if (tab[i] > tab[i + gap]){
                                encore = true;
                                temp = tab[i];
                                tab[i] = tab[i + gap];
                                tab[i + gap] = temp;
                                if (lesCases.variableTempActivee()) {
                                    executerAux(AFFECTATIONCVAL, i);
                                    executerAux(AFFECTATIONECRASEMENTCASECASE, i + gap, i);
                                    executerAux(AFFECTATIONVCASE, i + gap);
                                }
                                else {
                                    executerAux(AFFECTATION, i, i + gap);
                                }
                            }
                            Thread.sleep(5);
                            toucan.prevenirVues();
                        }
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
