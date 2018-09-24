package toucan;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import toucan.modele.Toucan;
import toucan.vuesFXML.panneauAnimation.PanneauAnimation;
import toucan.vuesFXML.toucan.ToucanControleur;

public class MainToucanFXML extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initialisation du toucan
        Toucan modele = new Toucan(5);
        modele.setValeurInitiale(0, 4);
        modele.setValeurInitiale(1, 1);
        modele.setValeurInitiale(2, 7);
        modele.setValeurInitiale(3, 8);
        modele.setValeurInitiale(4, 2);
        modele.creerLesMouvements(
                0, 1, Toucan.EST, 50,
                0, 2, Toucan.STABLE, 23,
                1, 4, Toucan.SUD, 50,
                1, 1, Toucan.OUEST, 50,
                3, 6, Toucan.NORD, 50,
                1, 5, Toucan.STABLE, 9);
        //   indice, etape, type, valeur


        primaryStage.setTitle("Toucan");

        FXMLLoader loader;
        // La vue principale
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/toucan/toucan.fxml"));
        ToucanControleur toucan = new ToucanControleur() ;
        loader.setControllerFactory(instanciatedClass -> { return toucan; });
        BorderPane root = loader.load();

        // Le panneau d'animation au centre
        PanneauAnimation panneau = new PanneauAnimation(modele) ;
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/panneauAnimation/panneauAnimation.fxml"));
        loader.setControllerFactory(instantiatedClass -> { return panneau ; });

        Parent sheet = loader.load();
        root.setCenter(sheet);

        primaryStage.setOnCloseRequest(event -> toucan.quitter());
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        // ligne à décommenter pour activer l'animation
        panneau.run() ;
    }


    public static void main(String[] args) {

        launch(args);
    }
}

