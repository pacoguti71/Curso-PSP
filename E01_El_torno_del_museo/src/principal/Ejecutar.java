package principal;

import clases.Torno;
import clases.Visitante;
import java.util.ArrayList;
import java.util.List;
import utilidades.Colores;

public class Ejecutar {

    public static void main(String[] args) {

        final int NUMERO_VISITANTES = 3;
        Torno torno = new Torno();
        List<Visitante> listaVisitantes = new ArrayList<>();

        for (int i = 1; i <= NUMERO_VISITANTES; i++) {
            Visitante visitante = new Visitante("Visitante " + i, torno);
            listaVisitantes.add(visitante);
            System.out.println("Ha llegado el " + visitante.getName());
        }

        for (Visitante visitante : listaVisitantes) {
            visitante.start();
        }

        for (Visitante visitante : listaVisitantes) {
            try {
                visitante.join();
            } catch (InterruptedException ex) {
            }
        }

        System.out.println(Colores.MORADO + "Ya se han ido los visitantes");

    } // Fin main

} // Fin class
