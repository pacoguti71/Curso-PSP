package ejecutar;

import clases.Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import logica.Juego;

public class EjecutarServidor {

    public static void main(String[] args) {

        int puertoServidor = 6000;

        try (ServerSocket socketServidor = new ServerSocket(puertoServidor)) {

            System.out.println("SERVIDOR CONCURRENTE");
            System.out.println("--------------------");
            System.out.println("Servidor iniciado.");
            System.out.printf("Escuchando por el puerto %d.\n", socketServidor.getLocalPort());
            System.out.println("Esperando conexión con clientes.");

            while (true) {
                Socket cliente = socketServidor.accept();
                System.out.println("Conexión establecida con cliente.");
                Juego juegoIndividual = new Juego();
                Servidor hiloServidor = new Servidor(cliente, juegoIndividual);
                hiloServidor.start();
            }
        } catch (IOException ex) {
            System.getLogger(EjecutarServidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    } // Fin main

} // Fin class
