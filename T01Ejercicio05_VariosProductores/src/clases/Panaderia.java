package clases;

public class Panaderia {

    public static final int MAX_PAN_DISPONIBLE = 20;
    private int panDisponible = 0;

    public synchronized boolean puedoFabricar(String nombre) {
        if (panDisponible < MAX_PAN_DISPONIBLE) {
            panDisponible++;
            System.out.println(nombre + " ha fabricado una unidad de pan. Ahora hay " + panDisponible + " panes disponibles.");
            return true;
        } else {
            return false;
        }
    }


} // Fin class
