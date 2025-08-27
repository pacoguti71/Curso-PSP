package clases;

import java.util.Random;

public class Visitante extends Thread {

    private final int NUM_ON_OFF = 6;
    private Torno torno;
    private Random aletaorio = new Random();

    public Visitante(String nombre, Torno torno) {
        super(nombre);
        this.torno = torno;
    }

    @Override
    public void run() {
        String nombre = this.getName();
        try {
            Thread.sleep(aletaorio.nextInt(500));
        } catch (InterruptedException ex) {
        }

        for (int i = 1; i <= NUM_ON_OFF; i++) {
            torno.entrarSalir(nombre, i);
            try {
                Thread.sleep(aletaorio.nextInt(1000, 3000));
            } catch (InterruptedException ex) {
            }
        }
    }

} // Fin class
