package toucan.vues;

import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import toucan.modele.Case;

import static toucan.modele.Toucan.CASELONGUEUR;
import static toucan.modele.Toucan.COEFFDUREE;

public class TexteAnimation extends Text {

    protected Case laCase ;
    protected Color[] lesCouleurs ;
    protected Double H ;
    protected Double W ;

    /**
     * Constructeur
     * @param c la case du modele dont on anime la valeur
     * @param lesCouleurs tableau des couleurs
     */
    public TexteAnimation(Case c, Color[] lesCouleurs) {
        super("" + c.getValeurInitiale());

        laCase = c ;
        this.lesCouleurs = lesCouleurs ;

        setFill(lesCouleurs[laCase.getCouleurInitiale()]);
        setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // setFill(Color.web("#0076a3"));

        W = getBoundsInLocal().getWidth();
        H = getBoundsInLocal().getHeight();

        setX(c.getPositionInitialeX() + (CASELONGUEUR - W) / 2);      // Centre de la case
        setY(c.getPositionInitialeY() + CASELONGUEUR / 2 + H / 4);
    }

    /**
     * animation du texte au centre de la case
     * @param etape numero de l'etape a animer
     * @return la transition saquentielle permettant d'animer le texte au centre de la case
     */
    public SequentialTransition animer(int etape) {
        int depX = laCase.getDeplacementX(etape) ;
        int depY = laCase.getDeplacementY(etape) ;
        int temps = Math.max(Math.abs(depX), Math.abs(depY)) ;

        // mise a jour de la couleur et de la valeur au debut du déplacement
        // changement de couleur (donner une duree non nulle pour visualiser un changement de couleur meme quand celui-ci n'est pas suivi d'un deplacement)
        FillTransition ft = new FillTransition(Duration.millis(1), this, lesCouleurs[laCase.getCouleur(etape-1)], lesCouleurs[laCase.getCouleur(etape)]) ;
        ft.setDelay(Duration.ZERO) ;
        ft.setOnFinished(event -> {
            String nouvelleValeur = "" + laCase.getValeur(etape) ;

            if (! getText().equals(nouvelleValeur)) {
                setText(nouvelleValeur) ;

                Double Wnew = getBoundsInLocal().getWidth();
                Double Hnew = getBoundsInLocal().getHeight();

                setX(getX() + (W - Wnew) / 2);
                setY(getY() - (H - Hnew) / 4) ;

                W = Wnew ;
                H = Hnew ;
            }
        });

        // le deplacement ...
        TranslateTransition tt = new TranslateTransition(Duration.millis(temps * COEFFDUREE), this);
        tt.setToX(depX);
        tt.setToY(depY);
        tt.setDelay(Duration.ZERO) ;
        tt.setOnFinished(event -> {
            setX(getX() + getTranslateX());
            setY(getY() + getTranslateY());
            setTranslateX(0);
            setTranslateY(0);
        });

        SequentialTransition res = new SequentialTransition(ft, tt) ;
        res.setDelay(Duration.ZERO);
        return res ;
    }

}
