package clases;

import java.util.Scanner;
import utilidades.Colores;

public class Cliente {

    private static int panDisponible = 20;
    private static int cantPan;

    public static void main(String[] args) {
        System.out.println(Colores.AMARILLO + "En el mostrador hay " + panDisponible + " panes disponibles.");

        while (puedoComprar()) {
            comprarPan(cantPan);
        }
        
        System.out.printf(Colores.ROJO+"No hay %d unidades de pan. Me voy.",cantPan);

    } // Fin main

    private static boolean puedoComprar() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("¿Cuánto pan quieres?");
        cantPan = teclado.nextInt();
        return panDisponible >= cantPan;
    }

    private static void comprarPan(int cantPan) {
        panDisponible = panDisponible - cantPan;
        System.out.printf(Colores.VERDE + "Has comprado %d unidad de pan. Ahora hay %d panes disponibles.\n", cantPan, panDisponible);
    }

} // Fin class
