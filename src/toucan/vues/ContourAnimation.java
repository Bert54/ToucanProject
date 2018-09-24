package toucan.vues;

import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import toucan.modele.Case;


public class ContourAnimation extends Rectangle {

    protected Case laCase ;
    protected Color[] lesCouleurs ;

    /**
     * Constructeur
     * @param c la case du modèle dont on anime le contour
     * @param lesCouleurs tableau des couleurs
     */
    public ContourAnimation(Case c, Color[] lesCouleurs) {
        super(c.getPositionInitialeX(), c.getPositionInitialeY(), 50, 50) ;

        laCase = c ;
        this.lesCouleurs = lesCouleurs ;

        setStrokeWidth(1);
        setFill(Color.TRANSPARENT);
        setStroke(lesCouleurs[laCase.getCouleurInitiale()]);
    }

    /**
     * animation de la forme carrée représentant la case
     * @param etape numéro de l'étape à animer
     * @return la transition séquentielle permettant d'animer la forme carrée
     */
    public SequentialTransition animer(int etape) {
         System.out.println("animer (" + laCase.getValeurInitiale() + ") étape : " + etape);

        int depX = laCase.getDeplacementX(etape) ;
        int depY = laCase.getDeplacementY(etape) ;
        int temps = Math.max(Math.abs(depX), Math.abs(depY)) ;

        // changement de couleur (donner une durée non nulle pour visualiser un changement de couleur même quand celui-ci n'est pas suivi d'un déplacement)
        StrokeTransition st = new StrokeTransition(Duration.millis(1), this, lesCouleurs[laCase.getCouleur(etape - 1)], lesCouleurs[laCase.getCouleur(etape)]);
        st.setDelay(Duration.ZERO);

        // déplacement
        TranslateTransition tt = new TranslateTransition(Duration.millis(temps * 10), this);
        tt.setToX(depX); // distance à parcourir
        tt.setToY(depY);
        tt.setDelay(Duration.ZERO) ;
        tt.setOnFinished(event -> {
            // The transition works by manipulating translation values,
            // After the transition is complete, move the node to the new location
            // and zero the translation after relocating the node.

            setX(getX() + getTranslateX());
            setY(getY() + getTranslateY());
            setTranslateX(0);
            setTranslateY(0);
        });

        SequentialTransition res = new SequentialTransition(st, tt) ;
        res.setDelay(Duration.ZERO) ;
        return res;
    }
}
