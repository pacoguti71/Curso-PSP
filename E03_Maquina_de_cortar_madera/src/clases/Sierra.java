package clases;

import utilidades.Colores;

public class Sierra {

    private boolean sierraOcupada;

    public synchronized void usarSierra(String nombre) {
        while (sierraOcupada) {
            System.out.println(Colores.ROJO + "La sierra está en uso. Espera " + nombre);
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        sierraOcupada = true;
        System.out.println(Colores.VERDE + nombre + " está usando la sierra...");
    }

    public synchronized void dejarDeUsarSierra(String nombre) {
        sierraOcupada = false;
        System.out.println(Colores.CYAN + nombre + " ha terminado. Que pase el siguiente");
        notifyAll();
    }

} // Fin class
