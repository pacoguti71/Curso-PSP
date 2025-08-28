package principal;

import clases.Cantante;
import clases.Microfono;
import utilidades.Colores;

public class Ejecutar {

    public static void main(String[] args) {
        Microfono microfono = new Microfono();

        Cantante andy = new Cantante("Andy", microfono);
        System.out.println("Ha llegado " + andy.getName());
        Cantante lucas = new Cantante("Lucas", microfono);
        System.out.println("Ha llegado " + lucas.getName());

        andy.start();
        lucas.start();

        try {
            andy.join();
            lucas.join();
        } catch (InterruptedException ex) {
        }

        System.out.println(Colores.MORADO + "Se terminó el cante");

    } // Fin main

} // Fin class
