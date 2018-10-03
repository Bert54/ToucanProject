package toucan.modele;
import java.util.HashMap;
import java.util.Random;

public class Case {

    private int valeurInit;
    private int absInit;
    private int ordInit;
    private HashMap<Integer, Etape> hmEtapes;
    private int couleur;
    private int maxEtape;

    public static final int COULEURCASE = 0; // Couleur des cases quand pas de mouvement. 0 = bleu.

    /**
     * Constructeur par defaut d'une case
     */
    public Case () {
        this.hmEtapes = new HashMap<>();
        this.maxEtape = 0;
        couleur = COULEURCASE;
    }

    /**
     * Setter sur la valeur de la case
     * @param val valeur de la case
     */
    public void setValeur(int val) {
        this.valeurInit = val;
    }

    /**
     * Getter sur la valeur initiale possedant la case
     * @return la valeur initiale de la case
     */
    public int getValeurInitiale() {
        return this.valeurInit;
    }

    /**
     * Getter sur la valeur actuelle de la case
     * @param etape etape
     * @return valeur de la case
     */
    public int getValeur(int etape) {
        int val = this.valeurInit;
        int tmpEtape = etape;
        boolean trouve = false;
        while(!trouve && tmpEtape > 0){
            if (this.hmEtapes.containsKey(tmpEtape)) {
                val = this.hmEtapes.get(tmpEtape).getValeur();
                trouve = true;
            }
            tmpEtape--;
        }
        return val;
    }

    /**
     * Modification de la valeur de la case
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param newVal nouvelle valeur de la case
     */
    public void modifValeur(int etape, int newVal, int couleur) {
        this.ajouterEtape(etape, newVal,0,0, couleur);
    }

    /**
     * Setter sur les positions de la case
     * @param x abscisse de la case
     * @param y ordonnee de la case
     */
    public void setPositions(int x, int y) {
        this.absInit = x;
        this.ordInit = y;
    }

    /**
     * Getter sur la position initiale de l abscisse
     * @return Position initiable de l abscisse
     */
    public int getPositionInitialeX() {
        return this.absInit;
    }

    /**
     * Getter sur la position initiale de l ordonnee
     * @return Position initiable de l ordonnee
     */
    public int getPositionInitialeY() {
        return this.ordInit;
    }

    /**
     * Deplacement de la case vers la gauche
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void gauche(int etape, int dep, int couleur) {
        int val = getValeur(etape);
        this.ajouterEtape(etape, val, -dep, 0, couleur);
    }

    /**
     * Deplacement de la case vers la droite
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void droite(int etape, int dep, int couleur) {
        int val = getValeur(etape);
        this.ajouterEtape(etape, val, dep, 0, couleur);
    }

    /**
     * Deplacement de la case vers le haut
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void monter(int etape, int dep, int couleur) {
        int val = getValeur(etape);
        this.ajouterEtape(etape, val, 0, -dep, couleur);
    }

    /**
     * Deplacement de la case vers le bas
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void descendre(int etape, int dep, int couleur) {
        int val = getValeur(etape);
        this.ajouterEtape(etape, val, 0, dep, couleur);
    }

    public void ajouterEtape(int etape, int val, int depX, int depY, int couleur) {
        if (etape > this.maxEtape) {
            this.setLastEtape(etape);
        }
        this.hmEtapes.put(etape, new Etape(etape, val, depX, depY, couleur));
    }

    /**
     * Getter sur le deplacement pour une certaine etape
     * @param etape numero de l etape
     * @return valeur du deplacement
     */
    public int getDeplacementX(int etape) {
        if(this.hmEtapes.containsKey(etape)) {
            return this.hmEtapes.get(etape).getDeplacementX();
        }
        return 0;
    }

    /**
     * Getter sur le deplacement pour une certaine etape
     * @param etape numero de l etape
     * @return valeur du deplacement
     */
    public int getDeplacementY(int etape) {
        if(this.hmEtapes.containsKey(etape)) {
            return this.hmEtapes.get(etape).getDeplacementY();
        }
        return 0;
    }

    /**
     * Getter sur la couleur initiale de la case
     * @return le numéro associe a la couleur initiale de la case
     */
    public int getCouleurInitiale() {
        return couleur ;
    }

    /**
     * Getter sur la couleur de la case a une etape donnee
     * @param etape Etape a laquelle la couleur doit etre recuperee
     * @return la couleur associee a l'etape donnee
     */
    public int getCouleur(int etape) {
        int coul = this.couleur;
        if (existeAnimation(etape)) {
            int tmpEtape = etape;
            boolean trouve = false;
            while (!trouve && tmpEtape > 0) {
                if (this.hmEtapes.containsKey(tmpEtape)) {
                    coul = this.hmEtapes.get(tmpEtape).getCouleur();
                    trouve = true;
                }
                tmpEtape--;
            }
        }
        return coul;
    }

    /**
     * Getter sur l'existence d'animation
     * @return vrai si une animation existe
     */
    public boolean existeAnimation (int etape) {
        if(hmEtapes.containsKey(etape))
            return true;
        return false;
    }

    /**
     * Setter du numero de la derniere etape de la case
     * @param l numero de la derniere etape de la case
     */
    public void setLastEtape(int l) {
        this.maxEtape = l;
    }

    /**
     * Getter du numero de la derniere etape de la case
     * @return numero de la derniere etape de la case
     */
    public int getLastEtape() {
        return this.maxEtape;
    }


    @Override
    public String toString() {
        return "Case{}";
    }
}
