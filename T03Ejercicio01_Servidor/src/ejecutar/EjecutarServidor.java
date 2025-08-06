package ejecutar;

import clases.Servidor;
import java.net.ServerSocket;
import java.net.Socket;

public class EjecutarServidor {

    private static final int PUERTO = 8080;

    public static void main(String[] args) {

        try (ServerSocket socketServidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor funcionando en el puerto " + PUERTO);
            System.out.println("Aceptamos peticiones en http://localhost:" + PUERTO);

            while (true) {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Cliente conectado desde " + socketCliente.getInetAddress());
                Servidor hiloServidor = new Servidor(socketCliente);
                hiloServidor.start();
            }
        } catch (Exception e) {
            System.err.println("Error");
        }

    } // Fin main

} // Fin class
