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
     * @param panneau le panneau contenant les deux elements graphiques constituant la representation d'une case :
     *                le contour d'un carre et un texte
     * @param c la case du modele a animer
     */
    public CaseAnimation(BorderPane panneau, Case c, int duree) {
        laCase = c ;
        // dessin de la forme de la case
        rect = new ContourAnimation(laCase, lesCouleurs, duree) ;

        // valeur à écrire au milieu de la case
        text = new TexteAnimation(laCase, lesCouleurs, duree) ;

        panneau.getChildren().add(rect);
        panneau.getChildren().add(text);
    }

    /**
     * Animation de la case et de sa valeur
     * @param noEtape numero de l'etape a animer
     * @return la transition parallele permettant d'animer la case
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
     * Getter sur l'existence d'une animation
     * @param etape numero de l'etape
     * @return vrai si une animation existe
     */
    public boolean existeAnimation(int etape) {
        return laCase.existeAnimation(etape) ;
    }

}
