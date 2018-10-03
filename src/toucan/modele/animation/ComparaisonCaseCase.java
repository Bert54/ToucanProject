package toucan.modele.animation;

import toucan.modele.Case;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static toucan.modele.Toucan.CASELONGUEUR;

public class ComparaisonCaseCase implements IAnimation {

    public static final int COULEURANIMATION = 2; // Couleur de cette animation en particulier ; 2 = jaune

    /**
     * Execution d'une animation de comparaison ; les cases en trop sont ignorees
     * @param lesCases Liste des cases
     * @param lesIndices Indices des cases a animer
     */
    @Override
    public void executer(LesCases lesCases, int... lesIndices) {
        if (lesIndices.length == 2) {
            Case case1 = lesCases.getCase(lesIndices[0]);
            Case case2 = lesCases.getCase(lesIndices[1]);
            int maxEt = Math.max(case1.getLastEtape()+1, case2.getLastEtape()+1);
            lesCases.creerEtape(lesIndices[0], maxEt, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEt, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+1, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEt+1, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+2, Toucan.STABLE,      // La case redevient bleue
                    case1.getValeur(case1.getLastEtape()), case1.getCouleurInitiale());
            lesCases.creerEtape(lesIndices[1], maxEt+2, Toucan.STABLE,
                    case2.getValeur(case2.getLastEtape()), case2.getCouleurInitiale());
        }
    }

}
