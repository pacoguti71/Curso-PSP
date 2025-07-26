package principal;

import java.util.Scanner;

/**
 * Realiza un programa que pida una hora por teclado y que muestre luego buenos
 * días, buenas tardes o buenas noches según la hora. Se utilizarán los tramos
 * de 6 a 12, de 13 a 20 y de 21 a 5. respectivamente. Sólo se tienen en cuenta
 * las horas, los minutos no se deben introducir por teclado.
 *
 */
public class S04Ejercicio02 {

    public static void main(String[] args) {
        int hora;
        String mensaje;

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce una hora (0-23)");
        hora = teclado.nextInt();

        if (hora < 0 || hora > 23) {
            mensaje = "La hora introducida no es correcta";
        } else if (hora >= 6 && hora < 12) {
            mensaje = "Buenos días";
        } else if (hora >= 12 && hora < 20) {
            mensaje = "Buentas tardes";
        } else {
            mensaje = "Buenas noches";
        }

        System.out.println(mensaje);

    } // Fin main

} // Fin class
