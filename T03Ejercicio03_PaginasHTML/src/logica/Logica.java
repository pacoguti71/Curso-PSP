package logica;

import java.io.PrintWriter;

public class Logica {

    public static final String linea1_OK = "HTTP/1.1 200 OK\r\n";
    public static final String linea1_NotFound = "HTTP/1.1 404 Not Found\r\n";
    public static final String linea1_MetodoNoPermitido = "HTTP/1.1 405 Method Not Allowed\r\n";
    public static final String linea1_InternalServerError = "HTTP/1.1 500 Internal Server Error\r\n";

    public static final String linea2_Cabecera = "Content-Type:text/html;charset=UTF-8\r\n";

    public static void mostrarPagina(PrintWriter salida, int tipoRespuesta, String linea4_html) {

        StringBuilder respuesta = new StringBuilder();

        String linea1 = null;

        switch (tipoRespuesta) {
            case 200:
                linea1 = linea1_OK;
                break;
            case 404:
                linea1 = linea1_NotFound;
                break;
            case 405:
                linea1 = linea1_MetodoNoPermitido;
                break;
            default:
                linea1 = linea1_InternalServerError;
                break;
        }
        
        respuesta.append(linea1);
        respuesta.append(linea2_Cabecera);
        respuesta.append("\r\n");
        respuesta.append(linea4_html);
        
        salida.print(respuesta.toString());
        salida.flush();
        
        

    }

} // Fin class
