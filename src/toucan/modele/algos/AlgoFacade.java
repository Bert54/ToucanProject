package toucan.modele.algos;

import toucan.modele.Toucan;
import toucan.outils.KitJava;

public class AlgoFacade extends Algo {

    /**
     * Constructeur de la facade de l'algo de tri personnel
     */
    public AlgoFacade(Toucan mod) {
        super(mod);
        this.nomAlgo = "Algo Personnel";
    }

    @Override
    public void trier() {
        KitJava.getInstance().construireClasse(toucan.getCodeUtilisateur());
        System.out.println(KitJava.getInstance().toString());
    }
}
