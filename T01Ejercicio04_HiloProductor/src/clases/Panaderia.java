package clases;

public class Panaderia {

    public static final int MAX_PAN_DISPONIBLE = 20;
    private int panDisponible = 0;

    public boolean puedoFabricar() {
        return panDisponible < MAX_PAN_DISPONIBLE;
    }

    public void fabricarPan(String nombre) {
        panDisponible++;
        System.out.println(nombre + " ha fabricado una unidad de pan. Ahora hay " + panDisponible + " panes disponibles.");
    }

} // Fin class
