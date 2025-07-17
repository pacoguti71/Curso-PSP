package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import utilidades.Colores;

public class Simulador {

    private static Panaderia panaderia;

    public static void main(String[] args) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        int numClientes;
        int numOrdenCliente = 0;

        panaderia = new Panaderia();
        Panadero panadero1 = new Panadero("Panadero 1", panaderia);
        Panadero panadero2 = new Panadero("Panadero 2", panaderia);
        Panadero panadero3 = new Panadero("Panadero 3", panaderia);

        List<Cliente> clientes = new ArrayList<>();

        panadero1.start();
        panadero2.start();
        panadero3.start();

        do {
            System.out.println(Colores.AMARILLO + "¿Qué deseas hacer?\n"
                    + Colores.AMARILLO + "1. Llegada de clientes\n"
                    + Colores.AMARILLO + "2. Mostrar información de la panadería\n"
                    + Colores.AMARILLO + "3. Salir\n");
            System.out.println(Colores.AMARILLO + "Elige una opcion: ");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print(Colores.AMARILLO + "Introduce cuántos clientes van a entrar: ");
                    numClientes = teclado.nextInt();

                    for (int i = 0; i < numClientes; i++) {
                        numOrdenCliente++;
                        Cliente cliente = new Cliente("Cliente " + numOrdenCliente, new Random().nextInt(1, 5), panaderia);
                        clientes.add(cliente);
                        cliente.start();
                    } // Fin for
                    break;
                case 2:
                    mostrarEstado();
                    break;
                case 3:
                    panaderia.setContinuarFabricacion(false);
                    panaderia.cerrar();
                    System.out.println(Colores.VERDE + "Saliendo del programa...");
                    panadero1.join();
                    panadero2.join();
                    panadero3.join();
                    mostrarEstado();

                    System.out.println("Fabricación terminada.");
                    break;
                default:
                    System.out.println(Colores.ROJO + "Opción incorrecta");
            }

        } while (opcion != 3);

        teclado.close();
    } // Fin main

    private static void mostrarEstado() {
        System.out.println("********************************");
        System.out.println("*");
        System.out.println("Cantidad de pan fabricado: " + panaderia.getPanFabricado());
        System.out.println("Cantidad de pan disponible: " + panaderia.getPanesDisponibles());
        System.out.println("Cantidad de pan vendido: " + (panaderia.getPanFabricado() - panaderia.getPanesDisponibles()));
        System.out.println("********************************");
    }

} // Fin class
