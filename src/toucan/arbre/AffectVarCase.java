package toucan.arbre;

public class AffectVarCase extends Affectation {

    private String opeDroite;

    /**
     * Constructeur d'une affectation entre la variable temporaire et un operande
     * @param p l'operande droite
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public AffectVarCase(String p, boolean var) {
        this.opeDroite = p;
        this.variableUtil = var;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.variableUtil) {
            string.append("executerAux(AFFECTATIONVCASE, " + removeTabinString(opeDroite) + ");\n");
        }
        else {
            string.append("executerAux(AFFECTATIONVCASE, " + opeDroite.replaceAll("[^0-9]", "") + ");\n");
        }
        string.append(opeDroite +" = temp;\n");
        return string.toString();
    }
}
