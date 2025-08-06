package clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import logica.Logica;

public class Servidor extends Thread {

    private final Socket socketCliente;
    private int tipoRespuesta;
    private String html;
    
    private static final Partida partida= new Partida();

    public Servidor(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {

        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

            PeticionWEB peticionWEB = new PeticionWEB(entrada);

            String metodo=peticionWEB.getMetodo();
            String url=peticionWEB.getUrl();
            String cuerpoMensaje=peticionWEB.getCuerpoMensaje();
            
            String htmlFinal="";
            
            if (!metodo.equals("GET") && !metodo.equals("POST")) {
                tipoRespuesta=405;
                htmlFinal=Files.readString(Paths.get("src/interfaz/405_noPermitido.html"));
            } else if (!url.equals("/") && !url.equals("/juego")) {
                tipoRespuesta=404;
                htmlFinal=Files.readString(Paths.get("src/interfaz/404_noExiste.html"));
            } else{
                tipoRespuesta=200;
                html=Files.readString(Paths.get("src/interfaz/juego.html"));
                htmlFinal=String.format(html,
                        0,
                        partida.getIntententosRestantes(),
                        partida.generarTableroHTML());
            }
            System.out.println(partida.generarTableroHTML());
            
            Logica.mostrarPagina(salida, tipoRespuesta, htmlFinal);

        } catch (Exception e) {
        }

    }

} // Fin class
