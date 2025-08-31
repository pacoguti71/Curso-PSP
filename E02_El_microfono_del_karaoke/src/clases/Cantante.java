package clases;

public class Cantante extends Thread {

    public final int NUM_CANCIONES = 8;
    private Microfono microfono;

    public Cantante(String nombre, Microfono microfono) {
        super(nombre);
        this.microfono = microfono;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUM_CANCIONES; i++) {
            try {
                microfono.cantar(this.getName());
                Thread.sleep(2000);
            } // Fin for
            catch (InterruptedException ex) {
            }
        }

    }

} // Fin class
