package toucan.modele;

import toucan.modele.algos.*;

import java.util.Observable;
import java.util.Random;

public class Toucan extends Observable {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OUEST = 4;
    public static final int STABLE = 5;
    public static final int CASELONGUEUR = 50;
    public static final int CASETEMPORDONNE = CASELONGUEUR * 5;
    public static final int NOMBREALEATOIREMAX = 20;

    private int[] tabEntiers;
    private Algo algoTri;
    private StatutAnimation statutAnimation = StatutAnimation.NON_INITIALISEE; // permet de definir l'etat actuel de l'animation
    private LesCases lesCases;
    private int coeffDuree = 5;

    /**
     * Constructeur en connaissant le nombre de cases
     * @param nbCases nombre de cases du toucan
     */
    public Toucan(int nbCases) {
        assert (nbCases >= 0) : "Nombre de cases invalides";
        Random random = new Random();
        int newVal;
        this.lesCases = new LesCases(nbCases);
        int abs = 10;
        for (int i = 0; i < nbCases+1; i++) {
            if (i < nbCases) {
                setPosition(i, abs, CASELONGUEUR);
                abs += CASELONGUEUR;
                newVal = random.nextInt(NOMBREALEATOIREMAX + 1); // Generation d'une valeur aleatoire pour la case i
                if (random.nextInt(2) == 1) { // Determiner si la valeur generee sera negative ou non
                    newVal -= newVal * 2;
                }
                this.setValeurInitiale(i, newVal);
            }
            else {
                setPosition(i, 10, CASETEMPORDONNE);
            }
        }
        this.algoTri = new AlgoBulle(this);
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
                GestionThreads.getInstance().detruireTout();
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
     * @param algo algorithme choisi
     */
    public void setAlgoActuel(Algo algo) {
        this.algoTri = algo;
        this.prevenirVues();
    }

    /**
     * Getter sur le choix de l'algorithme
     * @return l'algorithme actuellement utilisé
     */
    public String getAlgoActuel() {
        return this.algoTri.getNomAlgo();
    }


    /**
     * Creation des mouvements des cases ainsi que creation execution de l'algorithme choisit
     */
    public void creerLesMouvements() throws InterruptedException {
        this.lesCases.resetMaxEtape();
        lesCases.viderEtapes();
        this.tabEntiers = new int[this.nbCases()];
        for(int i = 0 ; i < this.nbCases() ; i++){
            this.tabEntiers[i] = getValeurInitiale(i);
        }
        this.algoTri.setTab(this.lesCases, this.tabEntiers);
        this.algoTri.trier();
        Thread.sleep(30);
        //System.out.println(this.toString());
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

    /**
     * Setter sur le statut d'activation de la variable temporaire
     * @param status le nouveau status
     */
    public void setVariableTemp(boolean status) {
        this.lesCases.setVariableTemp(status);
        this.prevenirVues();
    }

    public boolean varTempForceActif() {
        return this.algoTri.variableForceeActive();
    }

    /**
     * Getter sur le statut d'activation de la variable temporaire
     * @return le statut d'activation de la variable temporaire
     */
    public boolean variableTempActivee() {
        return this.lesCases.variableTempActivee();
    }

    /**
     * Setter sur la vitesse des animations
     * @param nouveauCoeff nouvelle vitesse
     * @param valMax valeur maximale du slider
     */
    public void setVitesse(int nouveauCoeff, int valMax) {
        this.coeffDuree = valMax - nouveauCoeff;
    }

    /**
     * Getter sur la vitesse des animations
     * @return le vitesse de l'animation (format administrateur, entre 1 et 10)
     * 1 étant la vitesse le plus rapide
     */
    public int getVitesse() {
        return this.coeffDuree;
    }

    @Override
    public String toString() {
        return "Toucan \n" + lesCases;
    }
}
