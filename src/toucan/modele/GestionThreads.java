package toucan.modele;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class GestionThreads {

    private static GestionThreads instance = new GestionThreads();

    public static GestionThreads getInstance() {
        return instance;
    }

    private ArrayList<Thread> threads;

    public GestionThreads() {
        this.threads = new ArrayList<>();
    }

    /**
     * Stocke et lance un thread
     * @param task le thread a stocker et a lancer
     */
    public void lancer(Task task) {
        Thread thread = new Thread(task);
        this.threads.add(thread);
        thread.start();
    }

    /**
     * Arrete et detruit tous les threads
     */
    public void detruireTout() {
        for (Thread t : this.threads) {
            t.interrupt();
        }
        this.threads.clear();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("");
        string.append("Threads : \n");
        int i = 1;
        for (Thread t : this.threads) {
            string.append("Thread " + i +" --> Nom : "+ t.getName() + ", ID : " + t.getId() + ", Etat : " + t.getState() + "\n");
            i++;
        }
        return string.toString();
    }
}
