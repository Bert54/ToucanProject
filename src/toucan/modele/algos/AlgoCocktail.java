package toucan.modele.algos;

import toucan.modele.LesCases;

import static toucan.modele.animation.AttributAnimation.AFFECTATION;
import static toucan.modele.animation.AttributAnimation.COMPARAISON;

public class AlgoCocktail extends Algo {

    /**
     * Constructeur d'un algorithme cocktail
     *
     * @param lesCases cases du Toucan
     * @param entiers  tableau des entiers
     */
    public AlgoCocktail(LesCases lesCases, int... entiers) {
        super(lesCases, entiers);
    }

    @Override
    public void trier() {
        boolean encore = true;
        int debut = 0;
        int fin = lesCases.nbCases() - 1;
        while(encore == true){
            encore = false;
            for(int j = debut ; j < fin ; j++){
                this.executerAux(COMPARAISON, j, j+1);
                if (this.tabEntiers[j] > this.tabEntiers[j+1]) {
                    int var = this.tabEntiers[j] ;
                    this.executerAux(AFFECTATION, j, j+1);
                    this.tabEntiers[j] = this.tabEntiers[j+1] ;
                    this.tabEntiers[j+1] = var ;
                    encore = true ;
                }
            }
            fin--;
            for(int j = fin ; j >= debut ; j--){
                this.executerAux(COMPARAISON, j, j+1);
                if (this.tabEntiers[j] > this.tabEntiers[j+1]) {
                    int var = this.tabEntiers[j] ;
                    this.executerAux(AFFECTATION, j, j+1);
                    this.tabEntiers[j] = this.tabEntiers[j+1] ;
                    this.tabEntiers[j+1] = var ;
                    encore = true ;
                }
            }
            debut++;
        }
    }
}
