package toucan.arbre;

public class CompaCaseCase extends Conditionnelle {

    private int operandeGauche;
    private String comparateur;
    private int operandeDroite;
    private BlocDInstructions instructionsAlors;
    private BlocDInstructions instructionsSinon;

    /**
     * COnstructueur d'une comparaison entre deux operandes
     * @param opeGauche l'operande gauche
     * @param compa l'operateur de comparaison
     * @param opeDroite l'operande droite
     * @param alors le bloc d'instructions si la condition est verifiee
     * @param sinon le bloc d'instructions sinon
     */
    public CompaCaseCase(int opeGauche, String compa, int opeDroite, BlocDInstructions alors, BlocDInstructions sinon) {
        this.operandeGauche = opeGauche;
        this.comparateur = compa;
        this.operandeDroite = opeDroite;
        this.instructionsAlors = alors;
        this.instructionsSinon = sinon;

    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(COMPARAISON, "+ operandeGauche + ", " + operandeDroite + ");\n");
        string.append("if (tab[" + operandeGauche + "] " + comparateur + " tab[" + operandeDroite + "]) {\n");
        string.append(instructionsAlors.getCodeDecore());
        string.append("}\nelse {\n");
        string.append(instructionsSinon.getCodeDecore());
        string.append("}\n");
        return string.toString();
    }

}
