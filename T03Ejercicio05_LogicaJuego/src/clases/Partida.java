package clases;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Partida {

    private final Set<String> barcos;
    private int intentosRestantes = 10;
    private int numBarcos = 10;
    private final Set<String> disparos = new HashSet<>();
    private final Set<String> aciertos = new HashSet<>();

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
        System.out.println(coordenadas.toString());
        return coordenadas;
    }

    public int getIntententosRestantes() {
        return intentosRestantes;
    }

    public boolean haFinalizado() {
        return intentosRestantes == 0 || aciertos.size() == barcos.size();
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
                    String coordenada = letras.charAt(fila - 1) + String.valueOf(columna);
                    if (aciertos.contains(coordenada)) {
                        tablero.append("<td class='tocado'>\uD83D\uDCA5</td>");
                    } else if (disparos.contains(coordenada)) {
                        tablero.append("<td class='agua'>\uD83D\uDCA7</td>");
                    } else if (haFinalizado() && barcos.contains(coordenada)) {
                        tablero.append("<td class='barco'>\uD83D\uDEA2</td>");
                    } else {
                        tablero.append("<td></td>");
                    }

                }
            }
            tablero.append("\n  </tr>\n"); // Fin de fila
        }

        tablero.append("</table>\n");

        return tablero.toString();
    }

    public void disparar(String coordenadas) {
        if (coordenadas != null && !coordenadas.isEmpty() && intentosRestantes > 0) {
            disparos.add(coordenadas);
            intentosRestantes--;
        }

        if (barcos.contains(coordenadas)) {
            aciertos.add(coordenadas);
        }
    }

    public int getAciertos() {
        return aciertos.size();
    }

} // Fin class
