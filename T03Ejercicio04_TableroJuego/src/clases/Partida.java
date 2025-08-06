package clases;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Partida {

    private final Set<String> barcos;
    private int intentosRestantes = 10;
    private int numBarcos = 10;

    public Partida() {
        this.barcos = generarBarcosAleatorios(numBarcos);
    }

    private Set<String> generarBarcosAleatorios(int numBarcos) {
        Set<String> coordenadas = new HashSet<>();

        Random aleatorio = new Random();
        String letras = "ABCDEFGHIJ";

        while (coordenadas.size() < numBarcos) {
            char fila = letras.charAt(aleatorio.nextInt(10));
            int columna = aleatorio.nextInt(10) + 1;
            String coordenada = fila + String.valueOf(columna);
            coordenadas.add(coordenada);
        }

        return coordenadas;
    }
    
    public int getIntententosRestantes(){
        return intentosRestantes;
    }

    public String generarTableroHTML() {
        StringBuilder tablero = new StringBuilder();
        String letras = "ABCDEFGHIJ";

        tablero.append("<table class='tablero'>\n");

        for (int fila = 0; fila <= 10; fila++) {
            tablero.append("  <tr>\n"); // Sangría para la fila

            for (int columna = 0; columna <= 10; columna++) {
                if (fila == 0 && columna == 0) {
                    tablero.append("    <td class='label'></td> ");
                } else if (fila == 0) {
                    tablero.append("<td class='label'>").append(columna).append("</td>");
                } else if (columna == 0) {
                    tablero.append("   <td class='label'>").append(letras.charAt(fila - 1)).append("</td> ");
                } else {
                    tablero.append("<td></td>"); // Podrías incluir aquí más lógica si quieres marcar celdas
                }
            }
            tablero.append("\n  </tr>\n"); // Fin de fila
        }

        tablero.append("</table>\n");

        return tablero.toString();
    }

} // Fin class
