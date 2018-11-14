package toucan.modele.algos;

import toucan.arbre.*;
import toucan.modele.Toucan;
import toucan.outils.KitJava;

public class AlgoFacade extends Algo {

    /**
     * Constructeur de la facade de l'algo de tri personnel
     */
    public AlgoFacade(Toucan mod) {
        super(mod);
        this.nomAlgo = "Algo Personnel";
        this.caseTempForcee = true;
    }

    @Override
    public void trier() {
        BlocDInstructions arbre = new BlocDInstructions();
        arbre.ajouter(new Declaration("int", "temp"));
        arbre.ajouter(new Declaration("int", "abc"));
        arbre.ajouter(new AffectEcrasementCaseCase("abc", "2", false, true));
        arbre.ajouter(new AffectEcrasementCaseCase("abc", "abc+3", false, true));
        arbre.ajouter(new AffectCaseCase("tab[9]", "tab[abc]", true));
        BlocDInstructions alors = new BlocDInstructions();
        BlocDInstructions sinon = new BlocDInstructions();
        alors.ajouter(new AffectCaseCase("tab[6]", "tab[3+2]", true));
        alors.ajouter(new AffectCaseCase("tab[5]", "tab[3]", false));
        alors.ajouter(new AffectCaseCase("tab[7]", "tab[2]", false));
        sinon.ajouter(new AffectCaseVar("tab[0]", false));
        sinon.ajouter(new AffectEcrasementCaseCase("tab[0]", "tab[7]", true, false));
        sinon.ajouter(new AffectVarCase("tab[7]", false));
        arbre.ajouter(new CompaCaseCase("tab[2]", "<", "tab[5]", alors, sinon, false));
        BlocDInstructions sinonAlors = new BlocDInstructions();
        BlocDInstructions sinonSinon = new BlocDInstructions();
        sinonAlors.ajouter(new AffectCaseCase("tab[2]","tab[1]", false));
        sinonSinon.ajouter(new AffectCaseCase("tab[6-5]","tab[7]", true));
        arbre.ajouter(new CompaVarCase("<", "tab[8]", sinonAlors, sinonSinon, false));
        //KitJava.getInstance().construireClasse(toucan.getCodeUtilisateur())
        KitJava.getInstance().construireClasse(arbre.getCodeDecore());
        System.out.println(KitJava.getInstance().toString());   // Affiche les erreurs de l'utilisateur sur la sortie standard
        KitJava.getInstance().compiler();
        KitJava.getInstance().executer(toucan);
        toucan.prevenirVues();
    }
}

/**
 * Pour construire les animations de l'algo perso depuis l'IG, il faut utiliser la methode executerAux(AttributAnimation attrAnim, int... lesIndices),
 * avec attrAnim, un type d'animation defini dans toucan.modele.animation.AttributAnimation et lesIndices les cases concernées (ici, seule 1 ou
 * 2 cases sont concernees).
 */

