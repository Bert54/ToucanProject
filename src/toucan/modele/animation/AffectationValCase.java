package toucan.modele.animation;

import toucan.modele.Case;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static toucan.modele.Toucan.CASELONGUEUR;
import static toucan.modele.Toucan.CASETEMPORDONNE;

public class AffectationValCase implements IAnimation {

    private static AffectationValCase affValInstance = new AffectationValCase();

    public static AffectationValCase getInstance() {
        return affValInstance;
    }

    private static final int COULEURANIMATION = 3; // Couleur de cette animation en particulier ; 4 = ???

    /**
     * Execution d'une animation d'affectation d'une case du tableau par la variable temporaire. Les cases en trop sont ignorees
     * @param lesCases Liste des cases
     * @param lesIndices Indices de la case a animer
     */
    @Override
    public void executer(LesCases lesCases, int... lesIndices) {
        if (lesIndices.length > 0) {
            int maxEtapes = lesCases.getMaxEtapes();
            Case c = lesCases.getCase(lesIndices[0]);
            Case cTemp = lesCases.getCase(lesCases.nbCases()); // La case temporaire
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 1, Toucan.EST, CASELONGUEUR * (lesIndices[0]), this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 1, Toucan.EST, 0, this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 2, Toucan.NORD, CASETEMPORDONNE-CASELONGUEUR, this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 3, Toucan.STABLE, cTemp.getValeur(maxEtapes + 2), this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 4, Toucan.SUD, CASETEMPORDONNE-CASELONGUEUR, this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 5, Toucan.OUEST, CASELONGUEUR * (lesIndices[0]), this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 6, Toucan.SUD, 0, cTemp.getCouleurInitiale());
            lesCases.creerEtape(lesIndices[0], maxEtapes + 6, Toucan.SUD, 0, c.getCouleurInitiale());
        }
    }
}
