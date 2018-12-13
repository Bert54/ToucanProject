package toucan.arbre;

public class Declaration extends Instruction {

    protected String nom;
    protected String type;

    /**
     * Constructeur d'une declaration
     * @param type type de la variable
     * @param nom nom de la variable
     */
    public Declaration(String type, String nom) {
        this.nom = nom;
        this.type = type;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append(type + " " + nom + ";\n");
        return string.toString();
    }
}
