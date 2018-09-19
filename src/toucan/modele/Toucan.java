package toucan.modele;

public class Toucan {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OUEST = 4;
    public static final int STABLE = 5;
    public static final int CASELONGUEUR = 10;
    public static final int CASELARGEUR = 10;

    private LesCases lesCases;

    /**
     * Constructeur en connaissant le nombre de cases
     * @param nbCases nombre de cases du toucan
     */
    public Toucan(int nbCases) {
        assert(nbCases >= 0) : "Nombre de cases invalides";
        this.lesCases = new LesCases(nbCases);
        int abs = 10;
        for(int i = 0 ; i < nbCases ; i++){
            setPosition(i, abs, CASELARGEUR);
            abs += CASELONGUEUR;
        }
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
    }

    /**
     * Getter sur la valeur initiale d une case
     * @param noCase numero de la case
     * @return valeur initiale de la case
     */
    public int getValeurInitiale(int noCase) {
        return this.lesCases.getCase(noCase).getValeurInitiale();
    }

    @Override
    public String toString() {
        return "Toucan \n" + lesCases;
    }
}