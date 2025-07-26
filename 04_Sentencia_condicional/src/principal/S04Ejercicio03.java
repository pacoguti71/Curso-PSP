package principal;

import java.util.Scanner;

/**
 * Escribe un programa en que dado un número del 1 a 7 escriba el
 * correspondiente nombre del día de la semana.
 *
 */

public class S04Ejercicio03 {

    public static void main(String[] args) {
        
        int numero;
        Scanner teclado = new Scanner(System.in);
        
        System.out.print("Introduce un número del 1 al 7");
        numero=teclado.nextInt();
        
        switch (numero) {
            case 1 -> System.out.println("Lunes");
            case 2 -> System.out.println("Martes");
            case 3 -> System.out.println("Miércoles");
            case 4 -> System.out.println("Jueves");
            case 5 -> System.out.println("Viernes");
            case 6 -> System.out.println("Sábado");
            case 7 -> System.out.println("Domingo");
            default -> System.err.println("El dato introducido no está entre 1 y 7");
        }

    } // Fin main

} // Fin class
