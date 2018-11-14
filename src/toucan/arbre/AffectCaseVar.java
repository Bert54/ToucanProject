package toucan.arbre;

public class AffectCaseVar extends Affectation {

    private String premier;

    /**
     * Constructeur d'une affectation entre un operande et la variable temp
     * @param p operande gauche
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public AffectCaseVar(String p, boolean var) {
        this.premier = p;
        this.variableUtil = var;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.variableUtil) {
            string.append("executerAux(AFFECTATION, "+ this.removeTabinString(premier) + ");\n");
        }
        else {
            string.append("executerAux(AFFECTATIONCVAL, " + premier.replaceAll("[^0-9]", "") + ");\n");
        }
        string.append("temp = " + premier +";\n");
        return string.toString();
    }
}
