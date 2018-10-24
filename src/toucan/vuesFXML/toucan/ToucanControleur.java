package toucan.vuesFXML.toucan;

import javafx.application.Platform;
import toucan.modele.GestionThreads;
import toucan.modele.Toucan;

public class ToucanControleur {

    public void quitter() {
        GestionThreads.getInstance().detruireTout();
        Platform.exit() ;
    }

}
