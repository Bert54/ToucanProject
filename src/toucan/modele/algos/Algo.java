package toucan.modele.algos;

import toucan.modele.LesCases;
import toucan.modele.Toucan;
import toucan.modele.animation.*;

import java.util.HashMap;

public abstract class Algo {

    protected HashMap<AttributAnimation, IAnimation> animationsCases;
    protected LesCases lesCases;
    protected String nomAlgo;
    protected Toucan toucan;
    protected boolean caseTempForcee;
    protected int[] tab;

    /**
     * Constructeur d'un algorithme (algo inutilisable sans avoir appele la methode setTab() )
     */
    public Algo(Toucan mod) {
        this.animationsCases = new HashMap<>(); // Contient les types d'animation possibles, chacunes referencees par un AttributAnimation
        this.animationsCases.put(AttributAnimation.AFFECTATION, AffectationCaseCase.getInstance());
        this.animationsCases.put(AttributAnimation.COMPARAISON, ComparaisonCaseCase.getInstance());
        this.animationsCases.put(AttributAnimation.AFFECTATIONCVAL, AffectationCaseVal.getInstance());
        this.animationsCases.put(AttributAnimation.AFFECTATIONVCASE, AffectationValCase.getInstance());
        this.animationsCases.put(AttributAnimation.AFFECTATIONECRASEMENTCASECASE, AffectationEcrasementCaseCase.getInstance());
        this.animationsCases.put(AttributAnimation.COMPARAISONVALCASE, ComparaisonValCase.getInstance());
        this.nomAlgo = "";
        this.toucan = mod;
        this.caseTempForcee = false;
    }

    /**
     * Rend l'algo utilisable en lui donnant les cases a trier
     * @param lesCases cases du Toucan
     * @param entiers tableau des entiers
     */
    public void setTab(LesCases lesCases, int... entiers) {
        this.lesCases = lesCases;
        this.tab = new int[entiers.length];
        this.tab = entiers.clone();
    }


    /**
     * Trie des cases selon un certain algorithme
     */
    public abstract void trier();

    /**
     * Getter sur le nom de l'algorithme choisi
     * @return l'algorithme choisi
     */
    public String getNomAlgo() {
        return this.nomAlgo;
    }

    /**
     * Indique si la variable temporaire est obligatoire
     * @return vrai si la variable temporaire est obligatoire
     */
    public boolean variableForceeActive() {
        return this.caseTempForcee;
    }


    /**
     * Fonction auxiliaire pour la creation d'un mouvement, permet de determiner le type de mouvement
     * @param attrAnim Type de mouvement defini dans AttributAnimation
     * @param lesIndices Indices des cases
     */
    protected void executerAux(AttributAnimation attrAnim, int... lesIndices) {
        this.animationsCases.get(attrAnim).executer(this.lesCases, lesIndices);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Algorithme : " + "\nNom : " + this.nomAlgo + ", Variable temporaire forcée : " + this.caseTempForcee);
        string.append("\nTableau : ");
        for (int i = 0; i < this.tab.length ; i++) {
            string.append(this.tab[i] + " ");
        }
        return string.toString();
    }

}
