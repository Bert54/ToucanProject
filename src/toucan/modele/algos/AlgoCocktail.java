package toucan.modele.algos;

import javafx.concurrent.Task;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

import static toucan.modele.animation.AttributAnimation.*;
import static toucan.modele.animation.AttributAnimation.AFFECTATIONVCASE;

public class AlgoCocktail extends Algo {

    /**
     * Constructeur d'un algorithme de tri cocktail
     */
    public AlgoCocktail(Toucan mod) {
        super(mod);
        this.nomAlgo = "Tri Cocktail";
    }

    @Override
    public void trier() {

        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    long startTime = System.nanoTime();
                    boolean encore = true;
                    int debut = 0;
                    int fin = lesCases.nbCases() - 1;
                    while(encore == true){
                        encore = false;
                        for(int j = debut ; j < fin ; j++){
                            executerAux(COMPARAISON, j, j+1);
                            if (tab[j] > tab[j+1]) {
                                int var = tab[j];
                                tab[j] = tab[j + 1];
                                tab[j + 1] = var;
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
                        fin--;
                        for(int j = fin ; j >= debut ; j--){
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
                        debut++;
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
