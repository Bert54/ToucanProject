package toucan.modele.animation;

import toucan.modele.Case;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static toucan.modele.Toucan.CASELONGUEUR;

public class AffectationCaseCase implements IAnimation {

    private static AffectationCaseCase affInstance = new AffectationCaseCase();

    public static AffectationCaseCase getInstance() {
        return affInstance;
    }

    private static final int COULEURANIMATION = 1; // Couleur de cette animation en particulier ; 1 = rose

    /**
     * Execution d'une animation d'echange de variables entre 2 cases
     * @param lesCases Liste des cases
     * @param lesIndices Indices des cases a animer
     */
    @Override
    public void executer(LesCases lesCases, int... lesIndices) { //si on a 2 cases
        if (lesIndices.length == 2) {
            Case case1 = lesCases.getCase(lesIndices[0]);
            Case case2 = lesCases.getCase(lesIndices[1]);
            int maxEt = lesCases.getMaxEtapes()+1;      // Une seule affectation par étape
            //int maxEt = Math.max(case1.getLastEtape()+1, case2.getLastEtape()+1);     // plusieurs affectations possible par étape
            int tempVal;
            lesCases.creerEtape(lesIndices[0], maxEt, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEt, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+1, Toucan.EST, CASELONGUEUR*(lesIndices[1]-lesIndices[0]), COULEURANIMATION);
            tempVal = case1.getValeur(case1.getLastEtape());
            lesCases.creerEtape(lesIndices[0], maxEt+2, Toucan.STABLE, case2.getValeur(case2.getLastEtape()), COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEt+2, Toucan.STABLE, tempVal, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+3, Toucan.OUEST, CASELONGUEUR*(lesIndices[1]-lesIndices[0]), COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+4, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEt+4, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+5, Toucan.STABLE, case1.getValeur(case1.getLastEtape()), case1.getCouleurInitiale());
            lesCases.creerEtape(lesIndices[1], maxEt+5, Toucan.STABLE, case2.getValeur(case2.getLastEtape()), case2.getCouleurInitiale());
        }
        else if (lesIndices.length == 1) { //si on a une seule case, elle se contente de revenir a sa position ; ignore les cases en plus qui peuvent etre eventuellement envoyees
            Case c = lesCases.getCase(lesIndices[0]);
            lesCases.creerEtape(lesIndices[0], c.getLastEtape()+1, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], c.getLastEtape()+1, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], c.getLastEtape()+1, Toucan.STABLE, c.getValeur(c.getLastEtape()), c.getCouleurInitiale());
        }
    }
}
