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
        } catch (Exception e) {
        }

    }

} // Fin class
