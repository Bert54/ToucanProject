package toucan.arbre;

public class AffectEcrasementCaseCase extends Affectation {

    int premier;
    int deuxieme;

    public AffectEcrasementCaseCase(int p, int d) {
        this.premier = p;
        this.deuxieme = d;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        string.append("executerAux(AFFECTATIONECRASEMENTCASECASE, "+ premier + ", " + deuxieme + ");\n");
        string.append("tab[" + premier +"] = tab[" + deuxieme +"];\n");
        return string.toString();
    }
}
