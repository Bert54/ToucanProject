package toucan.modele;

import java.util.ArrayList;

public class LesCases {

    private ArrayList<Case> lesCases;
    private int nbEtapes;
    private boolean variableTemp;

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
        this.lesCases = new ArrayList(taille+1); // Taille+1 pour ajouter la case temporaire
        for(int i = 0 ; i < taille+1 ; i++){
            lesCases.add(new Case());
        }
        this.variableTemp = false;
    }

    /**
     * Setter sur le statut d'activation de la variable temporaire
     * @param status le nouveau status
     */
    public void setVariableTemp(boolean status) {
        this.variableTemp = status;
    }

    /**
     * Getter sur le statut d'activation de la variable temporaire
     * @return le statut d'activation de la variable temporaire
     */
    public boolean variableTempActivee() {
        return this.variableTemp;
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
        return lesCases.size() - 1;
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
        sb.append("Les Cases : \nVariable temporaire activée : " + this.variableTemp + ", Nombre d'étapes : " + this.nbEtapes + "\n\n");
        sb.append("Cases contenues : \n\n") ;
        for (int i = 0 ; i < nbCases() ; i++) {
            Case c = lesCases.get(i);
            sb.append("Case " + i + " : " + c.toString()) ;
        }
        return sb.toString() ;
    }


}
