package clases;

import java.util.Random;
import utilidades.Colores;

public class Panadero extends Thread {

    Panaderia panaderia;

    public Panadero(String nombre, Panaderia panaderia) {
        super(nombre);
        this.panaderia = panaderia;
    }

    @Override
    public void run() {
        System.out.println(Colores.VERDE + this.getName() + " ha iniciado la producción de pan...");
        while (panaderia.puedoFabricar(this.getName())) {
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }
        }
        System.out.println(Colores.VERDE + "Capacidad de pan ha llegado al límite");

    }

} // Fin class
