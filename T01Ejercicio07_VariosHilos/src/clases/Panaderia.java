package clases;

import utilidades.Colores;

public class Panaderia {

    public static final int MAX_PAN_DISPONIBLE = 100;
    private int panDisponible = 0;
    private int panFabricado = 0;
    private boolean continuarFabricacion = true;

    public synchronized boolean puedoFabricar(String nombre) {
        if (panDisponible < MAX_PAN_DISPONIBLE) {
            panDisponible++;
            panFabricado++;
            System.out.println(Colores.VERDE + nombre + " ha fabricado una unidad de pan. Ahora hay " + panDisponible + " panes disponibles.");
            notifyAll();
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean puedoComprar(String nombre, int cantidad) throws InterruptedException {
        String deseo = Colores.CYAN + nombre + ": Quiero comprar " + cantidad + ". ";
        while (panDisponible < cantidad) {
            System.out.println(deseo + Colores.ROJO + "No puedo comprar (" + cantidad + "). No hay pan suficiente (" + panDisponible + "). Me pongo a la espera");
            wait();
        }
        panDisponible -= cantidad;
        System.out.println(deseo + Colores.BLANCO + "He comprado " + cantidad + " panes. Quedan " + panDisponible);
        return true;
    }

    public synchronized int getPanesDisponibles() {
        return panDisponible;
    }

    public int getPanFabricado() {
        return panFabricado;
    }

    public void setContinuarFabricacion(boolean continuar) {
        continuarFabricacion = continuar;
    }

    public boolean isContinuarFabricacion() {
        return continuarFabricacion;
    }

} // Fin class
