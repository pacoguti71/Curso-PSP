package clases;

import utilidades.Colores;

public class Panaderia {

    public static final int MAX_PAN_DISPONIBLE = 100;
    private int panDisponible = 0;

    public synchronized boolean puedoFabricar(String nombre) {
        if (panDisponible < MAX_PAN_DISPONIBLE) {
            panDisponible++;
            System.out.println(Colores.VERDE + nombre + " ha fabricado una unidad de pan. Ahora hay " + panDisponible + " panes disponibles.");
            notifyAll();
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean puedoComprar(String nombre, int cantidad) throws InterruptedException {
        System.out.println(Colores.CYAN + "Quiero comprar " + cantidad);
        while (panDisponible < cantidad) {
            wait();
        }
        panDisponible -= cantidad;
        System.out.println(Colores.CYAN + "He comprado " + cantidad + " panes. Quedan " + panDisponible);
        return true;
    }

    public synchronized int getPanesDisponibles() {
        return panDisponible;
    }

} // Fin class
