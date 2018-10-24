package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.LesCases;

import static java.lang.Thread.sleep;
import static toucan.modele.animation.AttributAnimation.*;

public class AlgoTest extends Algo {

    /**
     * Constructeur d'un algorithme de test
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public AlgoTest(LesCases lesCases, int...  entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    executerAux(AFFECTATIONCVAL, 0);
                    executerAux(AFFECTATIONECRASEMENTCASECASE, 0, 1);
                    executerAux(AFFECTATIONECRASEMENTCASECASE, 3, 6);
                    executerAux(AFFECTATIONECRASEMENTCASECASE, 7, 2);
                    executerAux(AFFECTATIONVCASE, 4);

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
