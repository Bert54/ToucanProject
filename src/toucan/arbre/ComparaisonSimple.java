package toucan.arbre;

public class ComparaisonSimple extends Conditionnelle {

    private String operandeGauche;
    private String comparateur;
    private String operandeDroite;
    private BlocDInstructions instructionsAlors;
    private BlocDInstructions instructionsSinon;

    /**
     * Constructueur d'une comparaison entre deux operandes
     * @param opeGauche l'operande gauche
     * @param compa l'operateur de comparaison
     * @param opeDroite l'operande droite
     * @param alors le bloc d'instructions si la condition est verifiee
     * @param sinon le bloc d'instructions sinon
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public ComparaisonSimple(String opeGauche, String compa, String opeDroite, BlocDInstructions alors, BlocDInstructions sinon, boolean var) {
        this.operandeGauche = opeGauche;
        this.comparateur = compa;
        this.operandeDroite = opeDroite;
        this.instructionsAlors = alors;
        this.instructionsSinon = sinon;
        this.variableUtil = var;

    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("if (" + operandeGauche + " " + comparateur + " " + operandeDroite + ") {\n");
        string.append(instructionsAlors.getCodeDecore());
        string.append("}\nelse {\n");
        string.append(instructionsSinon.getCodeDecore());
        string.append("}\n");
        return string.toString();
    }

}
