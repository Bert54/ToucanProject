package toucan.modele.algos;

import toucan.modele.LesCases;
import toucan.modele.animation.AffectationCaseCase;
import toucan.modele.animation.AttributAnimation;
import toucan.modele.animation.ComparaisonCaseCase;
import toucan.modele.animation.IAnimation;

import java.util.HashMap;

public abstract class Algo {

    protected HashMap<AttributAnimation, IAnimation> animationsCases;
    protected LesCases lesCases;
    protected int[] tabEntiers;

    /**
     * Constructeur d'un algorithme
     * @param lesCases cases du Toucan
     */
    public Algo(LesCases lesCases, int... entiers) {
        this.lesCases = lesCases;
        this.tabEntiers = new int[entiers.length];
        this.tabEntiers = entiers.clone();
        this.animationsCases = new HashMap<>(); // Contient les types d'animation possibles, chacunes referencees par un AttributAnimation
        this.animationsCases.put(AttributAnimation.AFFECTATION, new AffectationCaseCase());
        this.animationsCases.put(AttributAnimation.COMPARAISON, new ComparaisonCaseCase());
    }


    /**
     * Trie des cases selon un certain algorithme
     */
    public abstract void trier();


    /**
     * Fonction auxiliaire pour la creation d'un mouvement, permet de determiner quel type de mouvement
     * @param attrAnim Type de mouvement definie dans AttributAnimation
     * @param lesIndices Indices des cases
     */
    protected void executerAux(AttributAnimation attrAnim, int... lesIndices) {
        this.animationsCases.get(attrAnim).executer(this.lesCases, lesIndices);
    }
}
