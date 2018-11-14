package toucan.arbre;

public class CompaVarCase extends Conditionnelle {

    private String comparateur;
    private String operandeDroite;
    private BlocDInstructions instructionsAlors;
    private BlocDInstructions instructionsSinon;

    /**
     * Constructeur d'une comparaison la variable temporaire a gauche et un operande
     * a droite
     * @param compa l'operateur de comparaison
     * @param opeDroite l'operande droite
     * @param alors le bloc d'instructions si la condition est verifiee
     * @param sinon le bloc d'instructions sinon
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public CompaVarCase(String compa, String opeDroite, BlocDInstructions alors, BlocDInstructions sinon, boolean var) {
        this.comparateur = compa;
        this.operandeDroite = opeDroite;
        this.instructionsAlors = alors;
        this.instructionsSinon = sinon;
        this.variableUtil = var;

    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.variableUtil) {
            string.append("executerAux(COMPARAISONVALCASE, " + removeTabinString(operandeDroite) + ");\n");
        }
        else {
            string.append("executerAux(COMPARAISONVALCASE, " + operandeDroite.replaceAll("[^0-9]", "") + ");\n");
        }
        string.append("if (temp " + comparateur + " " + operandeDroite + ") {\n");
        string.append(instructionsAlors.getCodeDecore());
        string.append("}\nelse {\n");
        string.append(instructionsSinon.getCodeDecore());
        string.append("}\n");
        return string.toString();
    }

}
