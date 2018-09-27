package toucan.modele;

public class Etape {

    private int numero;
    private int valCase;
    private int deplacementX;
    private int deplacementY;
    private int couleur;

    /**
     * Constructeur d une etape de deplacement
     * @param num numero representant l etape
     * @param val valeur de la case
     * @param depX deplacement en abscisse de la case
     * @param depY deplacement en ordonnee de la case
     */
    public Etape(int num, int val, int depX, int depY, int coul) {
        this.numero = num;
        this.valCase = val;
        this.deplacementX = depX;
        this.deplacementY = depY;
        this.couleur = coul;
    }

    /**
     * Getter sur le numero de l etape
     * @return numero associe a l etape
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter sur la valeur possedee par la case
     * @return la valeur de la case
     */
    public int getValeur() {
        return this.valCase;
    }

    /**
     * Getter sur la valeur du deplacement en abscisse
     * @return valeur en abscisse associee au deplacement
     */
    public int getDeplacementX() {
        return deplacementX;
    }

    /**
     * Getter sur la valeur du deplacement en ordonnee
     * @return valeur en ordonnee associee au deplacement
     */
    public int getDeplacementY() {
        return deplacementY;
    }

    /**
     *  Getter de la couleur de la case a cette etape
     * @return couleur de la case a cette etape
     */
    public int getCouleur() {
        return this.couleur;
    }
}
