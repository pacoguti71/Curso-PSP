package principal;

import clases.GestorCargadores;
import clases.Movil;
import java.util.ArrayList;
import java.util.List;
import utilidades.Colores;

public class Ejecutar {

    public static void main(String[] args) {
        final int NUM_MOVILES = 6;
        GestorCargadores cargadores = new GestorCargadores();
        List<Movil> listaMoviles = new ArrayList<>();

        for (int i = 1; i <= NUM_MOVILES; i++) {
            Movil movil = new Movil("Movil " + i, cargadores);
            listaMoviles.add(movil);
            System.out.println("Ha llegado el " + movil.getName());
        } // Fin for

        for (Movil movil : listaMoviles) {
            movil.start();
        }

        for (Movil movil : listaMoviles) {
            try {
                movil.join();
            } catch (InterruptedException ex) {
            }
        }

        System.out.println(Colores.MORADO + "Todos los móviles han sido cargados dos veces");
    } // Fin main

} // Fin class
