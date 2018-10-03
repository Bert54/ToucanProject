package toucan.vues;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import toucan.modele.Case;


public class CaseAnimation {

    protected ContourAnimation rect;
    protected TexteAnimation text;

    protected Case laCase ;

    protected static Color[] lesCouleurs = {Color.BLUE, Color.DEEPPINK, Color.DARKORANGE, Color.GREEN, Color.YELLOW} ;

    /**
     * Constructeur
     * @param panneau le panneau contenant les deux éléments graphiques constituant la représentation d'une case :
     *                le contour d'un carré et un texte
     * @param c la case du modèle à animer
     */
    public CaseAnimation(BorderPane panneau, Case c) {
        laCase = c ;
        // dessin de la forme de la case
        rect = new ContourAnimation(laCase, lesCouleurs) ;

        // valeur à écrire au milieu de la case
        text = new TexteAnimation(laCase, lesCouleurs) ;

        panneau.getChildren().add(rect);
        panneau.getChildren().add(text);
    }

    /**
     * animation de la case et de sa valeur
     * @param noEtape numéro de l'étape à animer
     * @return la transition parallèle permettant d'animer la case
     */
    public ParallelTransition animerUneCase(int noEtape) {
        // System.out.println("dans CaseAnimation " + noEtape);
        SequentialTransition ptShape = rect.animer(noEtape);
        SequentialTransition ptText = text.animer(noEtape);

        ParallelTransition pt = new ParallelTransition(ptShape, ptText) ;
        pt.setDelay(Duration.ZERO);
        return pt ;
    }

    /**
     * Gettr sur l'existance d'une animation
     * @param etape numero de l'etape
     * @return true si une animation existe, false sinon
     */
    public boolean existeAnimation(int etape) {
        return laCase.existeAnimation(etape) ;
    }

}
