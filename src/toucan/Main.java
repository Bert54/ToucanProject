package toucan;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import toucan.modele.Toucan;
import toucan.vuesFXML.panneauAnimation.PanneauAnimation;
import toucan.vuesFXML.panneauControles.ControlesControleur;
import toucan.vuesFXML.panneauMenu.MenuControleur;
import toucan.vuesFXML.toucan.ToucanControleur;

import java.util.Collection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initialisation du toucan
        Toucan modele = new Toucan(11);
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

        // Le panneau des controles
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/panneauControles/panneauControles.fxml"));
        loader.setControllerFactory(iC->new ControlesControleur(modele));
        //ControlesControleur controles = new ControlesControleur(modele);
        //loader.setControllerFactory(instantiatedClass -> { return controles ; });
        BorderPane south = loader.load();
        root.setBottom(south);

        // Le menu
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/toucan/vuesFXML/panneauMenu/panneauMenu.fxml"));
        MenuControleur menu = new MenuControleur(modele);
        loader.setControllerFactory(instantiatedClass -> { return menu ; });
        root.getChildren().addAll(((Node)loader.load()));

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/toucan/ressources/toucan.jpg"))); // Ne fonctionne pas sur les systemes linux apparemment. Dommage.
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

