package clases;

import utilidades.Colores;

public class Carpintero extends Thread {

    final int NUM_PIEZAS = 10;
    private Sierra sierra;

    public Carpintero(String nombre, Sierra sierra) {
        super(nombre);
        this.sierra = sierra;
    }

    @Override
    public void run() {
        String nombre = this.getName();

        for (int i = 1; i <= NUM_PIEZAS; i++) {
            sierra.usarSierra(nombre);

            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
            }

            sierra.dejarDeUsarSierra(nombre);

            try {
                System.out.printf(Colores.AMARILLO + "Soy %s. Voy a descansar un poco%n", nombre);
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }

        } // Fin for
    }

} // Fin class
