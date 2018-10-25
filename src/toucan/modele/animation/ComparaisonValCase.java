package toucan.modele.animation;

import toucan.modele.Case;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static toucan.modele.Toucan.CASELONGUEUR;

public class ComparaisonValCase implements IAnimation {

    private static ComparaisonValCase instance = new ComparaisonValCase();

    public static ComparaisonValCase getInstance() {
        return instance;
    }

    private static final int COULEURANIMATION = 2; // Couleur de cette animation en particulier ; 2 = jaune


    /**
     * Execution d'une animation de comparaison entre une case du tableau et la variable temporaire ; les cases en trop sont ignorees
     * @param lesCases Liste des cases
     * @param lesIndices Indice de la case a animer
     */
    @Override
    public void executer(LesCases lesCases, int... lesIndices) {
        if (lesIndices.length > 0) {
            int maxEt = lesCases.getMaxEtapes() + 1;
            Case c = lesCases.getCase(lesIndices[0]);
            Case cTemp = lesCases.getCase(lesCases.nbCases()); // La case temporaire
            lesCases.creerEtape(lesIndices[0], maxEt, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEt, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+1, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEt+1, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEt+2, Toucan.STABLE,      // La case redevient bleue
                    c.getValeur(c.getLastEtape()), c.getCouleurInitiale());
            lesCases.creerEtape(lesCases.nbCases(), maxEt+2, Toucan.STABLE,
                    cTemp.getValeur(cTemp.getLastEtape()), cTemp.getCouleurInitiale());

        }
    }
}
