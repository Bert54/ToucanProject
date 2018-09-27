package toucan.modele;

import toucan.modele.animation.AffectationCaseCase;
import toucan.modele.animation.ComparaisonCaseCase;
import toucan.modele.animation.IAnimation;

import java.util.Observable;

public class Toucan extends Observable {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OUEST = 4;
    public static final int STABLE = 5;
    public static final int CASELONGUEUR = 50;
    public static final int COEFFDUREE = 8;

    private IAnimation mouvCases; // champ qui s'occupe de l'affectation lors d'un mouvement
    private IAnimation compCases; // champ qui s'occupe de la comparaison lors d'un mouvement
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
        this.mouvCases = new AffectationCaseCase();
        this.compCases = new ComparaisonCaseCase();
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
        this.mouvCases.executer(lesCases, 1, 4);
        this.compCases.executer(lesCases, 3, 0);
        this.mouvCases.executer(lesCases, 2, 4);
        this.mouvCases.executer(lesCases, 1);
        this.mouvCases.executer(lesCases, 7, 8);
        this.mouvCases.executer(lesCases, 4, 6);
        this.mouvCases.executer(lesCases, 3);
        this.mouvCases.executer(lesCases, 0, 4);
        this.mouvCases.executer(lesCases, 1, 2);
        this.mouvCases.executer(lesCases, 7);
        this.mouvCases.executer(lesCases, 3, 8);
        this.compCases.executer(lesCases, 2, 6);
        this.mouvCases.executer(lesCases, 1, 0);
        this.compCases.executer(lesCases, 6, 3);
        this.mouvCases.executer(lesCases, 3, 2);
        this.compCases.executer(lesCases, 3, 1);
        this.mouvCases.executer(lesCases, 4, 0);
        this.compCases.executer(lesCases, 4, 1);
        this.mouvCases.executer(lesCases, 4, 0);
        this.compCases.executer(lesCases, 5, 6);
        this.mouvCases.executer(lesCases, 2);
        this.mouvCases.executer(lesCases, 8, 6);
        this.mouvCases.executer(lesCases, 0);
        this.mouvCases.executer(lesCases, 8, 2);
        this.mouvCases.executer(lesCases, 3, 1);
        this.mouvCases.executer(lesCases, 5);
        this.mouvCases.executer(lesCases, 4, 3);
        this.compCases.executer(lesCases, 1, 7);
        this.mouvCases.executer(lesCases, 8, 4);
        this.mouvCases.executer(lesCases, 3, 5);
        this.mouvCases.executer(lesCases, 8);
        this.compCases.executer(lesCases, 4, 2);
        this.mouvCases.executer(lesCases, 8, 7);
        this.mouvCases.executer(lesCases, 2, 0);
        this.compCases.executer(lesCases, 4, 1);
        this.compCases.executer(lesCases, 0, 1);
        this.mouvCases.executer(lesCases, 3, 7);
        this.mouvCases.executer(lesCases, 4);
        this.compCases.executer(lesCases, 4, 2);
        this.mouvCases.executer(lesCases, 1, 5);
        this.compCases.executer(lesCases, 7, 4);
        this.mouvCases.executer(lesCases, 2);
        this.mouvCases.executer(lesCases, 1, 2);
        this.mouvCases.executer(lesCases, 6);
        this.mouvCases.executer(lesCases, 0, 3);
        this.mouvCases.executer(lesCases, 4);
        this.mouvCases.executer(lesCases, 0, 8);
        this.mouvCases.executer(lesCases, 1, 4);
        prevenirVues();
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
