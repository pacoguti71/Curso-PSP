package principal;

import clases.Carpintero;
import clases.Sierra;
import utilidades.Colores;

public class Ejecutar {

    public static void main(String[] args) {
        Sierra sierra = new Sierra();

        Carpintero c1 = new Carpintero("Carpintero 1", sierra);
        System.out.println("Ha llegado " + c1.getName());
        Carpintero c2 = new Carpintero("Carpintero 2", sierra);
        System.out.println("Ha llegado " + c2.getName());

        c1.start();
        c2.start();

        try {
            c1.join();
            c2.join();
        } catch (InterruptedException ex) {
        }

        System.out.println(Colores.MORADO + "Cerramos la carpitenría");

    } // Fin main

} // Fin class
