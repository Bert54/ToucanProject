package toucan.arbre;

/**
 * Cette classe, en plus de visualiser une animation de type AffectationEcrasementCaseCase, permet aussi d'affecter une valeur a une
 * variable declaree par l'utilisateur. Le champ execAnimation permet de ne pas executer l'animation correspondante a cette classe
 * dans ce cas precis.
 */

public class AffectEcrasementCaseCase extends Affectation {

    private String opeGauche;
    private String opeDroite;
    private boolean execAnimation;

    /**
     * Constructeur d'une affectation entre 2 operandes avec ecrasement de valeur
     * @param p operande gauche
     * @param d operande droite
     * @param anim determine l'utilisation d'une variable utilisateur pour l'execution de l'animation
     * @param var le booleen qui determine si on a un operande avec une variable utilisateur ou avec un calcul
     */
    public AffectEcrasementCaseCase(String p, String d, boolean anim, boolean var) {
        this.opeGauche = p;
        this.opeDroite = d;
        this.execAnimation = anim;
        this.variableUtil = var;
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        if (this.execAnimation) {
            if (this.variableUtil) {
                string.append("executerAux(AFFECTATIONECRASEMENTCASECASE, " + removeTabinString(opeGauche) + ", " + removeTabinString(opeDroite) + ");\n");
            }
            else {
                string.append("executerAux(AFFECTATIONECRASEMENTCASECASE, " + opeGauche.replaceAll("[^0-9]", "") + ", " + opeDroite.replaceAll("[^0-9]", "") + ");\n");
            }
        }
        string.append(opeGauche +" = " + opeDroite +";\n");
        return string.toString();
    }
}
