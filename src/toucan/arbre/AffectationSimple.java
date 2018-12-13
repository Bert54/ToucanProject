package toucan.arbre;

public class AffectationSimple extends Affectation {

    private String opeGauche;
    private String opeDroite;

    /**
     * Constructeur d'affectation entre 2 operandes
     * @param p operande gauche
     * @param d operande droite
     */
    public AffectationSimple(String p, String d) {
        this.opeGauche = p;
        this.opeDroite = d;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append(opeGauche + " = " + opeDroite +";\n");
        return string.toString();
    }
}