package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.LesCases;

import static java.lang.Thread.sleep;
import static toucan.modele.animation.AttributAnimation.*;

public class AlgoPeigne extends Algo{

    /**
     * Constructeur d'un algorithme de tri a peigne
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public AlgoPeigne(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    int gap = tabEntiers.length;
                    boolean encore = true;
                    int temp;
                    while (encore || gap > 1) {
                        encore = false;
                        gap /= 1.3; // Facteur de reduction. Peut etre remplace par un flottant comprit entre 1.25 et 1.3.
                        if (gap < 1) {
                            gap = 1;
                        }
                        for (int i = 0 ; i < (tabEntiers.length) - gap; i++) {
                            executerAux(COMPARAISON, i, i+ gap);
                            if (tabEntiers[i] > tabEntiers[i + gap]){
                                encore = true;
                                temp = tabEntiers[i];
                                tabEntiers[i] = tabEntiers[i + gap];
                                tabEntiers[i + gap] = temp;
                                if (lesCases.variableTempActivee()) {
                                    executerAux(AFFECTATIONCVAL, i);
                                    executerAux(AFFECTATIONECRASEMENTCASECASE, i + gap, i);
                                    executerAux(AFFECTATIONVCASE, i + gap);
                                }
                                else {
                                    executerAux(AFFECTATION, i, i + gap);
                                }
                            }
                            Thread.sleep(2);
                        }
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
