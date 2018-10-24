package toucan.modele.algos;

import toucan.modele.LesCases;
import toucan.modele.animation.*;

import java.util.HashMap;

public abstract class Algo {

    protected HashMap<AttributAnimation, IAnimation> animationsCases;
    protected LesCases lesCases;
    protected String nomAlgo;
    protected boolean caseTempForcee;
    protected int[] tabEntiers;

    /**
     * Constructeur d'un algorithme (inutilisable pour le moment)
     */
    public Algo() {
        this.animationsCases = new HashMap<>(); // Contient les types d'animation possibles, chacunes referencees par un AttributAnimation
        this.animationsCases.put(AttributAnimation.AFFECTATION, AffectationCaseCase.getInstance());
        this.animationsCases.put(AttributAnimation.COMPARAISON, ComparaisonCaseCase.getInstance());
        this.animationsCases.put(AttributAnimation.AFFECTATIONCVAL, AffectationCaseVal.getInstance());
        this.animationsCases.put(AttributAnimation.AFFECTATIONVCASE, AffectationValCase.getInstance());
        this.animationsCases.put(AttributAnimation.AFFECTATIONECRASEMENTCASECASE, AffectationEcrasementCaseCase.getInstance());
        this.nomAlgo = "";
        this.caseTempForcee = false;
    }

    /**
     * Rend l'algo utilisable en lui donnant les cases a trier
     * @param lesCases cases du Toucan
     * @param entiers tableaux des entiers
     */
    public void setTab(LesCases lesCases, int... entiers) {
        this.lesCases = lesCases;
        this.tabEntiers = new int[entiers.length];
        this.tabEntiers = entiers.clone();
    }


    /**
     * Trie des cases selon un certain algorithme
     */
    public abstract void trier();

    public String getNomAlgo() {
        return this.nomAlgo;
    }

    public boolean variableForceeActive() {
        return this.caseTempForcee;
    }


    /**
     * Fonction auxiliaire pour la creation d'un mouvement, permet de determiner quel type de mouvement
     * @param attrAnim Type de mouvement definie dans AttributAnimation
     * @param lesIndices Indices des cases
     */
    protected void executerAux(AttributAnimation attrAnim, int... lesIndices) {
        this.animationsCases.get(attrAnim).executer(this.lesCases, lesIndices);
    }
}
