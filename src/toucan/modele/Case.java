package toucan.modele;
import java.util.HashMap;
import java.util.Random;

public class Case {

    private int valeurInit;
    private int absInit;
    private int ordInit;
    private HashMap<Integer, Etape> hmEtapes;
    private int couleur;
    private Random random ;

    /**
     * Constructeur par defaut d'un case
     */
    public Case () {
        this.hmEtapes = new HashMap<>();

        random = new Random() ;
        couleur = random.nextInt(4) ;
    }

    /**
     * Setter sur la valeur de la case
     * @param val valeur de la case
     */
    public void setValeur(int val) {
        this.valeurInit = val;
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
     * Deplacement de la case vers la gauche
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void gauche(int etape, int dep) {
        int val = getValeur(etape);
        this.hmEtapes.put(etape, new Etape(etape, val, -dep, 0));
    }

    /**
     * Deplacement de la case vers la droite
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void droite(int etape, int dep) {
        int val = getValeur(etape);
        this.hmEtapes.put(etape, new Etape(etape, val, dep, 0));
    }

    /**
     * Deplacement de la case vers le haut
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void monter(int etape, int dep) {
        int val = getValeur(etape);
        this.hmEtapes.put(etape, new Etape(etape, val, 0, -dep));
    }

    /**
     * Deplacement de la case vers le bas
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param dep valeur de la distance a parcourir
     */
    public void descendre(int etape, int dep) {
        int val = getValeur(etape);
        this.hmEtapes.put(etape, new Etape(etape, val, 0, dep));
    }

    /**
     * Modification de la valeur de la case
     * @param etape numero de l etape dans laquelle la case se deplacera
     * @param newVal nouvelle valeur de la case
     */
    public void modifValeur(int etape, int newVal) {
        this.hmEtapes.put(etape, new Etape(etape, newVal,0,0));
    }

    /**
     * Getter sur la valeur initiale possedant la case
     * @return la valeur initiale de la case
     */
    public int getValeurInitiale() {
        return this.valeurInit;
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
     * Getter sur le deplacement pour une certaine etape
     * @param etape numero de l etape
     * @return valeur du deplacement
     */
    public int getDeplacementX(int etape) {
        if(this.hmEtapes.containsKey(etape))
            return this.hmEtapes.get(etape).getDeplacementX();
        return 0;
    }

    /**
     * Getter sur le deplacement pour une certaine etape
     * @param etape numero de l etape
     * @return valeur du deplacement
     */
    public int getDeplacementY(int etape) {
        if(this.hmEtapes.containsKey(etape))
            return this.hmEtapes.get(etape).getDeplacementY();
        return 0;
    }


    public int getCouleurInitiale() {
        return couleur ;
    }

    public int getCouleur(int etape) {
        return couleur ;
    }

    public boolean existeAnimation (int etape) {

        int res = random.nextInt(5) ;
        return (res != 0) ;
    }


    @Override
    public String toString() {
        return "Case{}";
    }
}
