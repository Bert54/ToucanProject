package toucan.arbre;

public class AffectVarCase extends Affectation {

    int premier;

    public AffectVarCase(int p) {
        this.premier = p;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(AFFECTATIONVCASE, "+ premier + ");\n");
        string.append("tab[" + premier +"] = temp;\n");
        return string.toString();
    }
}
