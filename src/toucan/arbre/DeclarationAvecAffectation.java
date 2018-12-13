package toucan.arbre;

public class DeclarationAvecAffectation extends Instruction {

    private String type;
    private String nom;
    private String newVal;

    /**
     * Constructeur d'une declaration
     *
     * @param type type de la variable
     * @param nom  nom de la variable
     * @param newVal nouvelle valeur à laquelle il faut initialiser la nouvelle variable
     */
    public DeclarationAvecAffectation(String type, String nom, String newVal) {
        this.type = type;
        this.nom = nom;
        this.newVal = newVal;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append(type + " " + nom + " = " + newVal + ";\n");
        return string.toString();
    }

}
