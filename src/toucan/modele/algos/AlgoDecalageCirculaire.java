package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static java.lang.Thread.sleep;
import static toucan.modele.animation.AttributAnimation.*;

public class AlgoDecalageCirculaire extends Algo {

    public AlgoDecalageCirculaire(Toucan mod) {
        super(mod);
        this.nomAlgo = "Décalage Circulaire";
        this.caseTempForcee = true;
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    for (int k = 0; k < 100000 ; k++) {
                        executerAux(AFFECTATIONCVAL, 0);
                        int x = tabEntiers[0];
                        for (int i = 0; i < lesCases.nbCases() - 1; i++) {
                            executerAux(AFFECTATIONECRASEMENTCASECASE, i + 1, i);
                            tabEntiers[i] = tabEntiers[i + 1];
                        }

                        executerAux(AFFECTATIONVCASE, lesCases.nbCases() - 1);
                        tabEntiers[lesCases.nbCases() - 1] = x;
                        toucan.prevenirVues();
                    }

                    return null;
                }
            };

            GestionThreads.getInstance().lancer(task);
            sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
