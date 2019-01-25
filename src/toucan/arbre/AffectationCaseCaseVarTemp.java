package toucan.arbre;

public class AffectationCaseCaseVarTemp extends Affectation {

    private String opeGauche;
    private String opeDroite;

    /**
     * Constructeur d'affectation entre deux operandes
     * @param p operande gauche
     * @param d operande droite
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public AffectationCaseCaseVarTemp(String p, String d, boolean var) {
        this.opeGauche = p;
        this.opeDroite = d;
        this.variableUtil = var;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.variableUtil) {
            string.append("executerAux(AFFECTATIONCVAL, "+ this.removeTabinString(opeDroite) + ");\n");
            string.append("temp = " + opeDroite +";\n");
            string.append("executerAux(AFFECTATIONECRASEMENTCASECASE, " + removeTabinString(opeGauche) + ", " + removeTabinString(opeDroite) + ");\n");
            string.append(opeGauche +" = " + opeDroite +";\n");
            string.append("executerAux(AFFECTATIONVCASE, " + removeTabinString(opeGauche) + ");\n");
            string.append(opeGauche +" = temp;\n");
        }
        else {
            string.append("executerAux(AFFECTATIONCVAL, " + opeDroite.replaceAll("[^0-9]", "") + ");\n");
            string.append("temp = " + opeDroite +";\n");
            string.append("executerAux(AFFECTATIONECRASEMENTCASECASE, " + opeGauche.replaceAll("[^0-9]", "") + ", " + opeDroite.replaceAll("[^0-9]", "") + ");\n");
            string.append(opeGauche +" = " + opeDroite +";\n");
            string.append("executerAux(AFFECTATIONVCASE, " + opeGauche.replaceAll("[^0-9]", "") + ");\n");
            string.append(opeGauche +" = temp;\n");
        }
        return string.toString();
    }
}
