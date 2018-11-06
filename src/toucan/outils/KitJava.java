package toucan.outils;

public class KitJava {

    private static KitJava instancee = new KitJava();

    public static KitJava getInstance() {
        return instancee;
    }


    protected String laClasse = "";

    public void construireClasse(String code) {

        StringBuilder codeACompiler = new StringBuilder("");
        codeACompiler.append("package toucan.modele.algos;\n\n" +
                "import javafx.concurrent.Task;\n" +
                "import toucan.modele.GestionThreads;\n" +
                "import toucan.modele.Toucan;\n" +
                "\n" +
                "import static toucan.modele.animation.AttributAnimation.*;\n\n");
        codeACompiler.append("public class AlgoPerso extends Algo {\n\n");
        codeACompiler.append("       public AlgoPerso(Toucan mod) {\n" +
                "               super(mod);\n" +
                "               this.nomAlgo = \"Algo Personnel\";\n" +
                "       }\n\n");
        codeACompiler.append("       public void trier() {\n\n");
        codeACompiler.append(code);
        codeACompiler.append(
                "\n\n       }\n");
        codeACompiler.append("}");
        this.laClasse = codeACompiler.toString();
    }

    public void compiler() {

    }

    public void executer() {

    }

    public String toString() {
        return this.laClasse;
    }

    public static void main(String[] args) {
        /**StringBuilder string = new StringBuilder();
        string.append("System.out.println( 5 + 5 );");
        KitJava kit = new KitJava(string.toString());
        System.out.println(kit.toString());*/
    }

}
