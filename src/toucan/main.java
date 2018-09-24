package toucan;

import toucan.modele.*;

public class main {

    public static void main(String[] args) {

        // Initialisation du toucan
        Toucan toucan = new Toucan(5);
        toucan.setValeurInitiale(0, 4);
        toucan.setValeurInitiale(1, 1);
        toucan.setValeurInitiale(2, 7);
        toucan.setValeurInitiale(3, 8);
        toucan.setValeurInitiale(4, 2);
        toucan.creerLesMouvements(
                0, 1, Toucan.EST, 10,
                0, 2, Toucan.STABLE, 23,
                1, 4, Toucan.SUD, 30,
                0, 3, Toucan.OUEST, 10,
                3, 6, Toucan.NORD, 5,
                1, 5, Toucan.STABLE, 9);


        System.out.println(toucan.toString());

    }

    public void aaa() {
        
    }

}
