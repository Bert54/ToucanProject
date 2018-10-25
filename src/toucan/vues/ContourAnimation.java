package toucan.vues;

import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import toucan.modele.Case;

import static toucan.modele.Toucan.CASELONGUEUR;


public class ContourAnimation extends Rectangle {

    protected Case laCase ;
    protected Color[] lesCouleurs ;
    protected int coeffDuree;

    /**
     * Constructeur
     * @param c la case du modele dont on anime le contour
     * @param lesCouleurs tableau des couleurs
     */
    public ContourAnimation(Case c, Color[] lesCouleurs, int duree) {
        super(c.getPositionInitialeX(), c.getPositionInitialeY(), CASELONGUEUR, CASELONGUEUR) ;
        this.coeffDuree = duree;
        laCase = c ;
        this.lesCouleurs = lesCouleurs ;

        setStrokeWidth(1);
        setFill(Color.TRANSPARENT);
        setStroke(lesCouleurs[laCase.getCouleurInitiale()]);
    }

    /**
     * Animation de la forme carree representant la case
     * @param etape numero de l'etape a animer
     * @return la transition sequentielle permettant d'animer la forme carree
     */
    public SequentialTransition animer(int etape) {
         //System.out.println("animer (" + laCase.getValeurInitiale() + ") étape : " + etape);

        int depX = laCase.getDeplacementX(etape) ;
        int depY = laCase.getDeplacementY(etape) ;
        int temps = Math.max(Math.abs(depX), Math.abs(depY)) ;

        // changement de couleur (donner une durée non nulle pour visualiser un changement de couleur même quand celui-ci n'est pas suivi d'un déplacement)
        StrokeTransition st = new StrokeTransition(Duration.millis(1), this, lesCouleurs[laCase.getCouleur(etape - 1)], lesCouleurs[laCase.getCouleur(etape)]);
        st.setDelay(Duration.ZERO);

        // déplacement
        TranslateTransition tt = new TranslateTransition(Duration.millis(temps * coeffDuree), this);
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
