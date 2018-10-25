package toucan.modele.animation;

import toucan.modele.Case;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static toucan.modele.Toucan.CASELONGUEUR;

public class AffectationEcrasementCaseCase implements IAnimation {

    private static AffectationEcrasementCaseCase affECaseInstance = new AffectationEcrasementCaseCase();

    public static AffectationEcrasementCaseCase getInstance() {
        return affECaseInstance;
    }

    private static final int COULEURANIMATION = 3; // Couleur de cette animation en particulier ; 5 = ???

    /**
     * Execution d'une animation d'affectation d'une case du tableau par une autre case; les cases en trop sont ignorees
     * @param lesCases Liste des cases
     * @param lesIndices Indices des cases a animer
     */
    @Override
    public void executer(LesCases lesCases, int... lesIndices) {
        if (lesIndices.length >= 2) {
            int maxEtapes = lesCases.getMaxEtapes();
            Case c1 = lesCases.getCase(lesIndices[0]);
            Case c2 = lesCases.getCase(lesIndices[1]);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 1, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEtapes + 1, Toucan.SUD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 2, Toucan.EST, CASELONGUEUR * (lesIndices[1]-lesIndices[0]), this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEtapes + 3, Toucan.STABLE, c1.getValeur(maxEtapes + 2), this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 4, Toucan.OUEST, CASELONGUEUR * (lesIndices[1]-lesIndices[0]), this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 5, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[1], maxEtapes + 5, Toucan.NORD, CASELONGUEUR, COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 6, Toucan.SUD, 0, c1.getCouleurInitiale());
            lesCases.creerEtape(lesIndices[1], maxEtapes + 6, Toucan.SUD, 0, c2.getCouleurInitiale());
        }
    }
}
