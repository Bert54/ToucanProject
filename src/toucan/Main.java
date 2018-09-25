package toucan;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import toucan.modele.Toucan;
import toucan.vuesFXML.panneauAnimation.PanneauAnimation;
import toucan.vuesFXML.panneauControles.ControlesControleur;
import toucan.vuesFXML.toucan.ToucanControleur;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initialisation du toucan
        Toucan modele = new Toucan(5);
        modele.setValeurInitiale(0, 4);
        modele.setValeurInitiale(1, 1);
        modele.setValeurInitiale(2, 7);
        modele.setValeurInitiale(3, 8);
        modele.setValeurInitiale(4, 2);

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

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/panneauControles/panneauControles.fxml"));
        ControlesControleur controles = new ControlesControleur(modele);
        loader.setControllerFactory(instantiatedClass -> { return controles ; });
        BorderPane south = loader.load();
        root.setBottom(south);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}

