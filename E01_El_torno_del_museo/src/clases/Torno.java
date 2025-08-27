package clases;

import utilidades.Colores;

public class Torno {

    private boolean tornoOcupado = false;

    public void entrarSalir(String nombre, int veces) {

        synchronized (this) {
            while (tornoOcupado) {
                System.out.println(Colores.ROJO + "El torno está en uso. Espera " + nombre);
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            tornoOcupado = true;
        }

        System.out.println(Colores.VERDE + nombre + " está usando el torno... (" + veces + " vez de " + (veces % 2 == 0 ? "entrada)" : "salida)"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }

        System.out.println(Colores.CYAN + nombre + " ha terminado. Que pase el siguiente.");

        synchronized (this) {
            tornoOcupado = false;
            notifyAll();
        }
    }

} // Fin class
