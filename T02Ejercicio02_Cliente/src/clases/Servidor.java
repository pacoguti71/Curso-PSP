package clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import logica.Juego;

public class Servidor extends Thread {

    private Socket cliente;
    private Juego juegoIndividual;

    public Servidor(Socket cliente, Juego juegoIndividual) {
        this.cliente = cliente;
        this.juegoIndividual = juegoIndividual;
    }

    @Override
    public void run() {

        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {
            salida.println("Bienvenido al juego del Ahorcado!");

            while (true) {
                salida.println("Introduce una letra:");
                String letraRecibida = entrada.readLine();
                if (letraRecibida != null && letraRecibida.length() == 1) {
                    char letra = letraRecibida.charAt(0);

                }
            }

        } catch (Exception e) {
        }

    }

} // Fin class
