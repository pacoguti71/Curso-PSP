package clases;

import java.util.Random;
import utilidades.Colores;

public class GestorCargadores {

    private static int cargadoresLibres = 4;
    private Random aleatorio = new Random();

    public void usar(String nombre, int veces) {
        synchronized (this) {
            while (cargadoresLibres == 0) {
                System.out.println(Colores.ROJO + nombre + ", todos los cargadores están usándose. Espera");
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            cargadoresLibres--;
            System.out.printf(Colores.VERDE + "%s está usando un cargador (%d vez). Cargadores ocupados=%d. Cargadores libres=%d%n", nombre, veces, 4 - cargadoresLibres, cargadoresLibres);
        }

        try {
            Thread.sleep(aleatorio.nextInt(3000, 5000));
        } catch (InterruptedException ex) {
        }
    }

    public synchronized void dejarUsar(String nombre) {
        cargadoresLibres++;
        System.out.printf(Colores.CYAN + "%s ha dejado libre un cargador. Cargadores ocupados=%d. Cargadores libres=%d%n", nombre, 4 - cargadoresLibres, cargadoresLibres);
        notifyAll();
    }

} // Fin class
