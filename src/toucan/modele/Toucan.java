package toucan.modele;

import toucan.modele.algos.*;

import java.util.Observable;

import static toucan.modele.algos.AttributAlgo.*;

public class Toucan extends Observable {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OUEST = 4;
    public static final int STABLE = 5;
    public static final int CASELONGUEUR = 50;
    public static final int COEFFDUREE = 5;
    public static final int CASETEMPORDONNE = CASELONGUEUR * 5;

    private int[] tabEntiers;
    private Algo algoTri;
    private StatutAnimation statutAnimation = StatutAnimation.NON_INITIALISEE;; // permet de definir l'etat actuel de l'animation
    private AttributAlgo algoActuel = ALGOBULLE;        // L'algorithme du tri a bulles est selectionne par defaut
    private LesCases lesCases;

    /**
     * Constructeur en connaissant le nombre de cases
     * @param nbCases nombre de cases du toucan
     */
    public Toucan(int nbCases) {
        assert (nbCases >= 0) : "Nombre de cases invalides";
        this.lesCases = new LesCases(nbCases);
        int abs = 10;
        for (int i = 0; i < nbCases+1; i++) {
            if (i < nbCases) {
                setPosition(i, abs, CASELONGUEUR);
                abs += CASELONGUEUR;
            }
            else {
                setPosition(i, 10, CASETEMPORDONNE);
            }
        }
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
     * Setter sur le choix de l'algorithme
     * @param att algorithme choisi
     */
    public void setAlgoActuel(AttributAlgo att) {
        this.algoActuel = att;
        this.prevenirVues();
    }

    /**
     * Getter sur le choix de l'algorithme
     * @return l'algorithme actuellement utilisé
     */
    public AttributAlgo getAlgoActuel() {
        return this.algoActuel;
    }

    /**
     * Creation des mouvements des cases
     */
    public void creerLesMouvements() {
        this.lesCases.resetMaxEtape();
        lesCases.viderEtapes();
        this.tabEntiers = new int[this.nbCases()];
        for(int i = 0 ; i < this.nbCases() ; i++){
            this.tabEntiers[i] = getValeurInitiale(i);
        }
        switch (this.algoActuel) {
            case ALGOBULLE:
                this.algoTri = new AlgoBulle(this.lesCases, this.tabEntiers);
                break;
            case ALGOTEST:
                this.algoTri = new AlgoTest(this.lesCases, this.tabEntiers);
                break;
            case ALGOSTUPIDE:
                this.algoTri = new AlgoStupide(this.lesCases, this.tabEntiers);
                break;
            case ALGOSELECTION:
                this.algoTri = new AlgoSelection(this.lesCases, this.tabEntiers);
                break;
            case ALGOINSERTION:
                this.algoTri = new AlgoInsertion(this.lesCases, this.tabEntiers);
                break;
            case ALGOCOCKTAIL:
                this.algoTri = new AlgoCocktail(this.lesCases, this.tabEntiers);
                break;
        }
        this.algoTri.trier();
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
    public Case getCase(int numCase) {
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
