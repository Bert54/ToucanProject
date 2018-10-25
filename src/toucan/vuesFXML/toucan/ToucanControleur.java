package toucan.vuesFXML.toucan;

import javafx.application.Platform;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

public class ToucanControleur {

    /**
     * Detruit les threads et ferme le programme
     */
    public void quitter() {
        GestionThreads.getInstance().detruireTout();
        Platform.exit() ;
    }

}
