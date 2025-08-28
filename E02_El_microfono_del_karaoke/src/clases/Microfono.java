package clases;

import utilidades.Colores;

public class Microfono {

    private boolean microfonoOcupado = false;

    public void cantar(String nombre) {

        synchronized (this) {
            while (microfonoOcupado) {
                System.out.println(Colores.ROJO + "El micrófono está en uso. Espera " + nombre);
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            microfonoOcupado = true;
        }

        System.out.println(Colores.VERDE + nombre + " está usando el micrófono...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        System.out.println(Colores.CYAN + nombre + " ha terminado. Que pase " + (nombre.equals("Andy") ? "Lucas" : "Andy"));

        synchronized (this) {
            microfonoOcupado = false;
            notifyAll();
        }

    }

} // Fin class
