package toucan.modele;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class GestionThreads {

    private static GestionThreads instance = new GestionThreads();

    public static GestionThreads getInstance() {
        return instance;
    }

    ArrayList<Thread> threads;

    public GestionThreads() {
        this.threads = new ArrayList<>();
    }

    public void lancer(Task task) {
        Thread thread = new Thread(task);
        this.threads.add(thread);
        thread.start();
    }

    public void detruireTout() {
        for (Thread t : this.threads) {
            t.interrupt();
        }
        this.threads.clear();
    }

}
