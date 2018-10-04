package toucan.modele;

import java.util.ArrayList;

public class LesCases {

    private ArrayList<Case> lesCases;
    private int nbEtapes;

    /**
     * Constructeur par defaut
     */
    public LesCases() {
        this.lesCases = new ArrayList<>();
        this.nbEtapes = 0;
    }

    /**
     * Constructeur en connaissant le nombre de cases
     * @param taille nombre de cases
     */
    public LesCases(int taille) {
        this.nbEtapes = 0;
        this.lesCases = new ArrayList(taille);
        for(int i = 0 ; i < taille ; i++){
            lesCases.add(new Case());
        }
    }

    /**
     * Creation d une etape associee a une case
     * @param c numero de la case dans la liste
     * @param etape numero de l etape
     * @param dir direction de la case
     * @param val valeur du deplacement de la case OU nouvelle valeur de la case
     */
    public void creerEtape(int c, int etape, int dir, int val, int couleur) {
        assert(c >= 0) : "L'indice de la case est incorrect";
        assert(etape > 0) : "Une etape ne peut pas etre negative";
        assert(dir == Toucan.NORD || dir == Toucan.SUD ||
                dir == Toucan.EST || dir == Toucan.OUEST || dir == Toucan.STABLE) :
                "Aucune correspondance entre la valeur et la direction";
        if(this.nbEtapes < etape)
            this.nbEtapes = etape;
        switch (dir){
            case Toucan.NORD :      // NORD
                assert(val > 0) : "Deplacement negatif impossible";
                this.lesCases.get(c).monter(etape, val, couleur);
                break;
            case Toucan.SUD :       // SUD
                assert(val > 0) : "Deplacement negatif impossible";
                this.lesCases.get(c).descendre(etape, val, couleur);
                break;
            case Toucan.EST :       // EST
                assert(val > 0) : "Deplacement negatif impossible";
                this.lesCases.get(c).droite(etape, val, couleur);
                break;
            case Toucan.OUEST :     // OUEST
                assert(val > 0) : "Deplacement negatif impossible";
                this.lesCases.get(c).gauche(etape, val, couleur);
                break;
            case Toucan.STABLE :    // Modification de la valeur de la case
                this.lesCases.get(c).modifValeur(etape, val, couleur);
            default:
                break;
        }
    }

    /**
     *  Reinitialisation du maximum d'etapes (utile pour la reconstruction des mouvements)
     */
    public void resetMaxEtape() {
        for (int i = 0; i < this.nbCases() ; i++) {
            this.getCase(i).setLastEtape(0);
        }
        this.nbEtapes = 0;
    }

    /**
     * Getter sur le nombre de cases
     * @return le nombre de cases de la liste
     */
    public int nbCases() {
        return lesCases.size();
    }

    /**
     * Getter sur le nombre d etapes
     * @return le nombre d etapes
     */
    public int getMaxEtapes() {
        return nbEtapes;
    }

    /**
     * Getter sur la case
     * @param indice numero de la case
     * @return la case d indice indice
     */
    public Case getCase(int indice) {
        return this.lesCases.get(indice);
    }


    /**
     * Vide les etapes des cases dans la liste
     */
    public void viderEtapes() {
        for(Case c : this.lesCases){
            c.viderEtapes();
        }
    }

    @Override
    public String toString() {

        int maxEtapes = getMaxEtapes() ;
        StringBuilder sb = new StringBuilder() ;

        sb.append("\t\t\t\t\t\t") ;
        for (int i = 0 ; i < nbCases() ; i++) {
            sb.append("Case " + i + "\t\t\t\t");
        }

        sb.append("\n") ;

        sb.append("position initiale : \t") ;
        for (int i = 0 ; i < nbCases() ; i++) {
            Case c = lesCases.get(i);
            sb.append(String.format("(%3d,%3d) [%2d]\t\t", c.getPositionInitialeX(), c.getPositionInitialeY(), c.getValeurInitiale())) ;
        }
        sb.append("\n") ;

        sb.append("déplacements :\n") ;

        for (int etape = 1 ; etape <= maxEtapes ; etape++) {
            sb.append("\t\t\t\t" + etape + "\t\t");

            for (int i = 0; i < nbCases(); i++) {
                Case c = lesCases.get(i);
                int x = c.getDeplacementX(etape);
                int y = c.getDeplacementY(etape);
                int valeur = c.getValeur(etape);
                sb.append(String.format("(%3d,%3d) [%2d]\t\t", x, y, valeur)) ;
            }
            sb.append("\n");
        }

        return sb.toString() ;
    }


}
