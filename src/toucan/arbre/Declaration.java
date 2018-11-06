package toucan.arbre;

public class Declaration extends Instruction {

    private String nom;
    private String type;

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
