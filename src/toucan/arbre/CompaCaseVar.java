package toucan.arbre;

public class CompaCaseVar extends Conditionnelle {

    private String operandeGauche;
    private String comparateur;
    private BlocDInstructions instructionsAlors;
    private BlocDInstructions instructionsSinon;

    /**
     * Constructeur d'une comparaison entre un operande a gauche et la variable
     * temporaire a droite
     * @param opeGauche l'operande gauche
     * @param compa l'operateur de comparaison
     * @param alors le bloc d'instructions si la condition est verifiee
     * @param sinon le bloc d'instructions sinon
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public CompaCaseVar(String opeGauche, String compa, BlocDInstructions alors, BlocDInstructions sinon, boolean var) {
        this.operandeGauche = opeGauche;
        this.comparateur = compa;
        this.instructionsAlors = alors;
        this.instructionsSinon = sinon;
        this.variableUtil = var;

    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.variableUtil) {
            string.append("executerAux(COMPARAISONVALCASE, " + removeTabinString(operandeGauche) + ");\n");
        }
        else {
            string.append("executerAux(COMPARAISONVALCASE, " + operandeGauche.replaceAll("[^0-9]", "") + ");\n");
        }
        string.append("if (" + operandeGauche + " " + comparateur + " temp) {\n");
        string.append(instructionsAlors.getCodeDecore());
        string.append("}\nelse {\n");
        string.append(instructionsSinon.getCodeDecore());
        string.append("}\n");
        return string.toString();
    }

}
