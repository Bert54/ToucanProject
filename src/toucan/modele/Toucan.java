package toucan.modele;

import java.util.Observable;

public class Toucan extends Observable {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OUEST = 4;
    public static final int STABLE = 5;
    public static final int CASELONGUEUR = 50;
    public static final int COEFFDUREE = 10;

    private StatutAnimation statutAnimation;
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
        this.statutAnimation = StatutAnimation.NON_INITIALISEE;
    }

    /**
     * Retourne l'etat actuel de l'animation
     * @return StatutAnimation
     */
    public StatutAnimation getStatutAnimation() {
        return this.statutAnimation;
    }

    /**
     * Set l'etat de l'animation
     * @param s
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
     * Previent les vues lors d'un changement
     */
    public void prevenirVues() {
        this.setChanged();
        this.notifyObservers();
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
     * @param dep indice de la case a deplacer
     *            le numero de l etape
     *            le type du deplacement
     *            la duree du deplacement
     */
    public void creerLesMouvements(int... dep) {
        assert(dep.length % 4 == 0) : "Nombre de paramètres de mouvements incorrects";
        int indiceCase = 0;
        int numEtape = 1;
        int direction = 2;
        int dureeDep = 3;
        int nbFois = dep.length/4;   // 4 car le parametre est en fait composé de 4 parametres
        for(int i = 0 ; i < nbFois ; i++) {
            this.lesCases.creerEtape(dep[indiceCase], dep[numEtape],
                    dep[direction], dep[dureeDep]);
            indiceCase += 4;
            numEtape += 4;
            direction += 4;
            dureeDep += 4;
        }
        prevenirVues();
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
