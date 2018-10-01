package toucan.modele;

import toucan.modele.animation.AffectationCaseCase;
import toucan.modele.animation.AttributAnimation;
import toucan.modele.animation.ComparaisonCaseCase;
import toucan.modele.animation.IAnimation;

import java.util.HashMap;
import java.util.Observable;

public class Toucan extends Observable {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OUEST = 4;
    public static final int STABLE = 5;
    public static final int CASELONGUEUR = 50;
    public static final int COEFFDUREE = 8;

    private HashMap<AttributAnimation, IAnimation> animationsCases;
    private StatutAnimation statutAnimation; // permet de definir l'etat actuel de l'animation
    private LesCases lesCases;

    /**
     * Constructeur en connaissant le nombre de cases
     * @param nbCases nombre de cases du toucan
     */
    public Toucan(int nbCases) {
        assert (nbCases >= 0) : "Nombre de cases invalides";
        this.lesCases = new LesCases(nbCases);
        int abs = 10;
        for (int i = 0; i < nbCases; i++) {
            setPosition(i, abs, CASELONGUEUR);
            abs += CASELONGUEUR;
        }
        this.animationsCases = new HashMap<>(); // Contient les types d'animation possibles, chacunes referencees par un AttributAnimation
        this.animationsCases.put(AttributAnimation.AFFECTATION, new AffectationCaseCase());
        this.animationsCases.put(AttributAnimation.COMPARAISON, new ComparaisonCaseCase());
        this.statutAnimation = StatutAnimation.NON_INITIALISEE;
    }

    /**
     * Previent les vues lors d'un changement
     */
    public void prevenirVues() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Set l'etat de l'animation
     * @param s entier representant l'etat d'animation a attribuer au modele
     */
    public void setStatutAnimation(int s) {
        switch (s) {
            case 0:
                this.statutAnimation = StatutAnimation.NON_INITIALISEE;
                break;
            case 1:
                this.statutAnimation = StatutAnimation.EN_COURS_ACTIF;
                break;
            case 2:
                this.statutAnimation = StatutAnimation.EN_COURS_PAUSE;
                break;
            default:
                this.statutAnimation = StatutAnimation.FINIE;
        }
        prevenirVues();
    }

    /**
     * Getter sur l'etat actuel de l'animation
     * @return statutAnimation
     */
    public StatutAnimation getStatutAnimation() {
        return this.statutAnimation;
    }

    /**
     * Setter sur la valeur de chaque case
     * @param noCase numero de la case
     * @param val valeur de la case
     */
    public void setValeurInitiale(int noCase, int val) {
        this.lesCases.getCase(noCase).setValeur(val);
    }

    /**
     * Getter sur la valeur initiale d une case
     * @param noCase numero de la case
     * @return valeur initiale de la case
     */
    public int getValeurInitiale(int noCase) {
        return this.lesCases.getCase(noCase).getValeurInitiale();
    }

    /**
     * Setter sur les positions de chaque case
     * @param noCase numero de la case
     * @param x abscisse de la case
     * @param y ordonnee de la case
     */
    public void setPosition(int noCase, int x, int y) {
        this.lesCases.getCase(noCase).setPositions(x, y);
    }

    /**
     * Creation des mouvements des cases
     */
    public void creerLesMouvements() {
        this.lesCases.resetMaxEtape();
        this.executerAux(AttributAnimation.AFFECTATION, 1, 4);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 2, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 7, 8);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 3);
        this.executerAux(AttributAnimation.AFFECTATION, 0, 4);
        this.executerAux(AttributAnimation.COMPARAISON, 1, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 7);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 8);
        this.executerAux(AttributAnimation.AFFECTATION, 2, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 6, 3);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 3, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 1);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 0);
        this.executerAux(AttributAnimation.COMPARAISON, 5, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 2);
        this.executerAux(AttributAnimation.COMPARAISON, 8, 6);
        this.executerAux(AttributAnimation.AFFECTATION, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 8, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 3, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 5);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 3);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 7);
        this.executerAux(AttributAnimation.COMPARAISON, 8, 4);
        this.executerAux(AttributAnimation.COMPARAISON, 3, 5);
        this.executerAux(AttributAnimation.AFFECTATION, 8);
        this.executerAux(AttributAnimation.COMPARAISON, 4, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 8, 7);
        this.executerAux(AttributAnimation.AFFECTATION, 2, 0);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 1);
        this.executerAux(AttributAnimation.COMPARAISON, 0, 1);
        this.executerAux(AttributAnimation.AFFECTATION, 3, 7);
        this.executerAux(AttributAnimation.AFFECTATION, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 4, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 5);
        this.executerAux(AttributAnimation.COMPARAISON, 7, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 2);
        this.executerAux(AttributAnimation.COMPARAISON, 1, 2);
        this.executerAux(AttributAnimation.AFFECTATION, 6);
        this.executerAux(AttributAnimation.COMPARAISON, 0, 3);
        this.executerAux(AttributAnimation.AFFECTATION, 4);
        this.executerAux(AttributAnimation.AFFECTATION, 0, 8);
        this.executerAux(AttributAnimation.AFFECTATION, 1, 4);
        prevenirVues();
    }

    /**
     * Fonction auxiliaire pour la creation d'un mouvement, permet de determiner quel type de mouvement
     * @param attrAnim Type de mouvement definie dans AttributAnimation
     * @param lesIndices Indices des cases
     */
    private void executerAux(AttributAnimation attrAnim, int... lesIndices) {
        this.animationsCases.get(attrAnim).executer(this.lesCases, lesIndices);
    }

    /**
     * Getter sur le nombre de cases
     * @return le nombre de cases
     */
    public int nbCases() {
        return this.lesCases.nbCases();
    }

    /**
     * Getter sur une case par son numéro
     * @param numCase numéro de la case
     * @return La case associée au numéro
     */
    public Case getCase(int numCase){
        return this.lesCases.getCase(numCase);
    }

    /**
     * Getter sur le nombre maximal d'étapes
     * @return le nombre maximal d'étapes
     */
    public int getNbMaxEtapes() {
        return this.lesCases.getMaxEtapes();
    }

    @Override
    public String toString() {
        return "Toucan \n" + lesCases;
    }
}
