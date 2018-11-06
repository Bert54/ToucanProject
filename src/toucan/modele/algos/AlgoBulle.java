package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import static toucan.modele.animation.AttributAnimation.*;

public class AlgoBulle extends Algo {

    /**
     * Constructeur d'un algorithme de tri à bulles
     */
    public AlgoBulle(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri à Bulles";
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    boolean encore = true ;
                    int n = lesCases.nbCases() ;
                    while (encore) {
                        encore = false ;
                        for (int j = 0 ; j < n - 1; j++) {
                            executerAux(COMPARAISON, j, j+1);
                            if (tab[j] > tab[j+1]) {
                                int var = tab[j] ;
                                tab[j] = tab[j+1] ;
                                tab[j+1] = var ;
                                if (lesCases.variableTempActivee()) {
                                    executerAux(AFFECTATIONCVAL, j);
                                    executerAux(AFFECTATIONECRASEMENTCASECASE, j + 1, j);
                                    executerAux(AFFECTATIONVCASE, j + 1);
                                }
                                else {
                                    executerAux(AFFECTATION, j, j + 1);
                                }
                                encore = true ;
                            }
                        }
                        n = n-1 ;
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
