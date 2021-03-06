package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoTest extends Algo {

    /**
     * Constructeur d'un algorithme de test
     */
    public AlgoTest(Toucan mod) {
        super(mod);
        this.nomAlgo = "Algo Test";
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    long startTime = System.nanoTime();
                    executerAux(AFFECTATIONCVAL, 0);
                    executerAux(AFFECTATIONECRASEMENTCASECASE, 0, 1);
                    executerAux(AFFECTATIONECRASEMENTCASECASE, 3, 6);
                    executerAux(AFFECTATIONECRASEMENTCASECASE, 7, 2);
                    executerAux(AFFECTATIONVCASE, 4);
                    Thread.sleep(5);
                    toucan.prevenirVues();
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
