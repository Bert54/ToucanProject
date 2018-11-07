package toucan.arbre;

public class CompaCaseVar extends Conditionnelle {

    private int operandeGauche;
    private String comparateur;
    private BlocDInstructions instructionsAlors;
    private BlocDInstructions instructionsSinon;

    /**
     * Constructeur d'une comparaison entre une operande a gauche et la variable
     * temporaire a droite
     * @param opeGauche l'operande gauche
     * @param compa l'operateur de comparaison
     * @param alors le bloc d'instructions si la condition est verifiee
     * @param sinon le bloc d'instructions sinon
     */
    public CompaCaseVar(int opeGauche, String compa, BlocDInstructions alors, BlocDInstructions sinon) {
        this.operandeGauche = opeGauche;
        this.comparateur = compa;
        this.instructionsAlors = alors;
        this.instructionsSinon = sinon;

    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(COMPARAISONVALCASE, " + operandeGauche + ");\n");
        string.append("if (tab[" + operandeGauche + "] " + comparateur + " temp) {\n");
        string.append(instructionsAlors.getCodeDecore());
        string.append("}\nelse {\n");
        string.append(instructionsSinon.getCodeDecore());
        string.append("}\n");
        return string.toString();
    }

}
