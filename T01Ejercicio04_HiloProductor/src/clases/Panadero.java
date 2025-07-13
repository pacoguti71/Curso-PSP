package clases;

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
        while (panaderia.puedoFabricar()) {
            panaderia.fabricarPan(this.getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }
        }
        System.out.println(Colores.VERDE + "Capacidad de pan ha llegado al límite");

    }

} // Fin class
