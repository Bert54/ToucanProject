package toucan.modele.animation;

import toucan.modele.Case;
import toucan.modele.LesCases;
import toucan.modele.Toucan;

import static toucan.modele.Toucan.CASELONGUEUR;
import static toucan.modele.Toucan.CASETEMPORDONNE;

public class AffectationCaseVal implements IAnimation {

    private static AffectationCaseVal affCValInstancee = new AffectationCaseVal();

    public static AffectationCaseVal getInstance() {
        return affCValInstancee;
    }

    private static final int COULEURANIMATION = 3; // Couleur de cette animation en particulier ; 3 = ???

    @Override
    public void executer(LesCases lesCases, int... lesIndices) {
        if (lesIndices.length > 0) {
            int maxEtapes = lesCases.getMaxEtapes();
            Case c = lesCases.getCase(lesIndices[0]);
            Case cTemp = lesCases.getCase(lesCases.nbCases()); //La case temporaire
            lesCases.creerEtape(lesIndices[0], maxEtapes + 1, Toucan.SUD, CASETEMPORDONNE-CASELONGUEUR, this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 1, Toucan.SUD, 0, this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 2, Toucan.OUEST, CASELONGUEUR * (lesIndices[0]), this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 3, Toucan.STABLE, c.getValeur(maxEtapes + 2), this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 4, Toucan.EST, CASELONGUEUR * (lesIndices[0]), this.COULEURANIMATION);
            lesCases.creerEtape(lesIndices[0], maxEtapes + 5, Toucan.NORD, CASETEMPORDONNE-CASELONGUEUR, this.COULEURANIMATION);
            lesCases.creerEtape(lesCases.nbCases(), maxEtapes + 6, Toucan.SUD, 0, cTemp.getCouleurInitiale());
            lesCases.creerEtape(lesIndices[0], maxEtapes + 6, Toucan.SUD, 0, c.getCouleurInitiale());
        }
    }
}
