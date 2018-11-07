package toucan.arbre;

public class CompaVarCase extends Conditionnelle {

    private String comparateur;
    private int operandeDroite;
    private BlocDInstructions instructionsAlors;
    private BlocDInstructions instructionsSinon;

    /**
     * Constructeur d'une comparaison la variable temporaire a gauche et une operande
     * a droite
     * @param compa l'operateur de comparaison
     * @param opeDroite l'operande droite
     * @param alors le bloc d'instructions si la condition est verifiee
     * @param sinon le bloc d'instructions sinon
     */
    public CompaVarCase(String compa, int opeDroite, BlocDInstructions alors, BlocDInstructions sinon) {
        this.comparateur = compa;
        this.operandeDroite = opeDroite;
        this.instructionsAlors = alors;
        this.instructionsSinon = sinon;

    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(COMPARAISONVALCASE, " + operandeDroite + ");\n");
        string.append("if (temp " + comparateur + " tab[" + operandeDroite + "]) {\n");
        string.append(instructionsAlors.getCodeDecore());
        string.append("}\nelse {\n");
        string.append(instructionsSinon.getCodeDecore());
        string.append("}\n");
        return string.toString();
    }

}
