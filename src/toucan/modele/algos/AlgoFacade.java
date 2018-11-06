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
        BlocDInstruction arbre = new BlocDInstruction();
        arbre.ajouter(new Declaration("int", "temp"));
        arbre.ajouter(new AffectCaseCase(3, 6));
        arbre.ajouter(new AffectCaseVar(0));
        arbre.ajouter(new AffectEcrasementCaseCase(7, 0));
        arbre.ajouter(new AffectVarCase(7));
        //KitJava.getInstance().construireClasse(toucan.getCodeUtilisateur());
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

