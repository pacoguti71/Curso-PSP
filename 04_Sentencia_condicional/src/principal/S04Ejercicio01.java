package principal;

/**
 * Escribe un programa que pida por teclado un día de la semana y que diga qué
 * asignatura toca a primera hora ese día.
 *
 */
import java.util.Scanner;

public class S04Ejercicio01 {

    public static void main(String[] args) {
        String dia = "";
        String asignatura = "";

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el día de la semana (lunes-domingo): ");
        dia = teclado.nextLine();

        switch (dia.toUpperCase()) {
            case "LUNES":
                asignatura = "matemáticas";
                System.out.printf("El %s tienes %s\n", dia, asignatura);
                break;
            case "MARTES":
                asignatura = "proghramación";
                System.out.printf("El %s tienes %s\n", dia, asignatura);
                break;
            case "MIERCOLES":
                asignatura = "lógica";
                System.out.printf("El %s tienes %s\n", dia, asignatura);
                break;
            case "JUEVES":
                asignatura = "base de datos";
                System.out.printf("El %s tienes %s\n", dia, asignatura);
                break;
            case "VIERNES":
                asignatura = "FOL";
                System.out.printf("El %s tienes %s\n", dia, asignatura);
                break;
            case "SABADO":
            case "DOMINGO":
                System.out.printf("El %s no hay clases\n", dia);
                break;
            default:
                System.err.println("Has introducido un día de la semana no válido");
        }

    } // Fin main

} // Fin class
