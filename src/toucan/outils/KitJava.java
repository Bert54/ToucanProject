package toucan.outils;

import toucan.modele.Toucan;
import toucan.modele.algos.Algo;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KitJava {

    private static KitJava instance = new KitJava();

    public static KitJava getInstance() {
        return instance;
    }

    private KitJava() {
        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
    }

    protected String laClasse = "";
    protected static String nomClasse = "AlgoPerso";
    protected static String nomPackage = "toucan.modele.algos";

    protected JavaCompiler compiler ;
    protected ClassFileManager fileManager ;

    public void construireClasse(String code) {

        StringBuilder codeACompiler = new StringBuilder("");
        codeACompiler.append("package toucan.modele.algos;\n\n" +
                "import javafx.concurrent.Task;\n" +
                "import toucan.modele.GestionThreads;\n" +
                "import toucan.modele.Toucan;\n" +
                "\n" +
                "import static toucan.modele.animation.AttributAnimation.*;\n\n");
        codeACompiler.append("public class AlgoPerso extends Algo {\n\n");
        codeACompiler.append("      private int nbCases;\n\n");
        codeACompiler.append("       public AlgoPerso(Toucan mod) {\n" +
                "               super(mod);\n" +
                "               this.nomAlgo = \"Algo Personnel\";\n" +
                "               mod.instancierAlgoTableau(this);\n" +
                "               this.nbCases = tab.length;\n" +
                "       }\n\n");
        codeACompiler.append("       public void trier() {\n\n");
        codeACompiler.append(code);
        codeACompiler.append(
                "\n\n       }\n");
        codeACompiler.append("}");
        this.laClasse = codeACompiler.toString();
    }

    public void compiler() {
        StringWriter sortieErreur = new StringWriter();

        Iterable<? extends JavaFileObject> fileObjects = getJavaSourceFromString(nomClasse, laClasse);

        compiler.getTask(sortieErreur, fileManager, null, null, null, fileObjects).call();
        try {
            sortieErreur.close() ;
        } catch (IOException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Iterable<JavaSource> getJavaSourceFromString(String fileName, String code) {
        return Collections.singletonList(new JavaSource(fileName, code));
    }

    public void executer(Toucan toucan) {
        try {
            String nomExecutable = nomPackage + "." + nomClasse ;
            System.out.println("nom exécutable : " + nomExecutable);

//            Object instance = fileManager.getClassLoader(javax.tools.StandardLocation.CLASS_PATH).loadClass("toucan.outils.Essai").newInstance();
//            ((IEssai)instance).setX(23) ;
//            int res = ((IEssai)instance).getX() ;

            ClassLoader cl = fileManager.getClassLoader(javax.tools.StandardLocation.CLASS_PATH) ;
            Class<?> classe = Class.forName("toucan.modele.algos.AlgoPerso", true, cl) ;
            Constructor<?> constructeur = classe.getConstructor(Class.forName("toucan.modele.Toucan")) ;

            Algo instance = (Algo) constructeur.newInstance(toucan) ;
            toucan.instancierAlgoTableau(instance);
            instance.trier();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TestOutils.class.getName()).log(Level.SEVERE, null, ex);
        }
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
