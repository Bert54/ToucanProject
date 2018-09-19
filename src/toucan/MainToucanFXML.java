package toucan;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import toucan.vuesFXML.panneauAnimation.PanneauAnimation;
import toucan.vuesFXML.toucan.ToucanControleur;

public class MainToucanFXML extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("ToucanControleur");

        FXMLLoader loader;
        // La vue principale
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/toucan/toucan.fxml"));
        ToucanControleur toucan = new ToucanControleur() ;
        loader.setControllerFactory(instanciatedClass -> { return toucan; });
        BorderPane root = loader.load();

        // Le panneau d'animation au centre
        PanneauAnimation panneau = new PanneauAnimation() ;
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/panneauAnimation/panneauAnimation.fxml"));
        loader.setControllerFactory(instantiatedClass -> { return panneau ; });

        Parent sheet = loader.load();
        root.setCenter(sheet);

        primaryStage.setOnCloseRequest(event -> toucan.quitter());
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        // ligne à décommenter pour activer l'animation
        // panneau.run() ;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

