package toucan.arbre;

public class AffectCaseCase extends Affectation {

    private String opeGauche;
    private String opeDroite;

    /**
     * Constructeur d'affectation entre 2 operandes
     * @param p operande gauche
     * @param d operande droite
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public AffectCaseCase(String p, String d, boolean var) {
        this.opeGauche = p;
        this.opeDroite = d;
        this.variableUtil = var;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.variableUtil) {
            string.append("executerAux(AFFECTATION, "+ this.removeTabinString(opeGauche) + ", " + this.removeTabinString(opeDroite) + ");\n");
        }
        else {
            string.append("executerAux(AFFECTATION, "+ opeGauche.replaceAll("[^0-9]", "") + ", " + opeDroite.replaceAll("[^0-9]", "") + ");\n");
        }
        string.append("temp = " + opeGauche +";\n");
        string.append(opeGauche +" = " + opeDroite +";\n");
        string.append(opeDroite +" = temp;\n");
        return string.toString();
    }
}
