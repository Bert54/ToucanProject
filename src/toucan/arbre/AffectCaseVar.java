package toucan.arbre;

public class AffectCaseVar extends Affectation {

    int premier;

    public AffectCaseVar(int p) {
        this.premier = p;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(AFFECTATIONCVAL, "+ premier + ");\n");
        string.append("temp = tab[" + premier +"];\n");
        return string.toString();
    }
}
