package toucan.arbre;

public class AffectCaseCase extends Affectation {

    int premier;
    int deuxieme;

    public AffectCaseCase(int p, int d) {
        this.premier = p;
        this.deuxieme = d;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(AFFECTATION, "+ premier + ", " + deuxieme + ");\n");
        string.append("temp = tab[" + premier +"];\n");
        string.append("tab[" + premier +"] = tab[" + deuxieme +"];\n");
        string.append("tab[" + deuxieme +"] = temp;\n");
        return string.toString();
    }
}
