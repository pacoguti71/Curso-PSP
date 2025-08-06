package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PeticionWEB {

    private String metodo;
    private String url;
    private String version;
    private Map<String, String> cabecerasHTTP = new HashMap<>();
    private String cuerpoMensaje;

    public PeticionWEB(BufferedReader entrada) {
        String[] elementosInicio = extraerElementosInicio(entrada);

        this.metodo = elementosInicio[0];
        this.url = elementosInicio[1];
        this.version = elementosInicio[2];

        this.cabecerasHTTP=extraerCabecerasMetadatos(entrada);
        
        this.cuerpoMensaje =extraerCuerpoMensaje(entrada);
    }

    private String[] extraerElementosInicio(BufferedReader entrada) {
        String[] elementosInicio = null;
        try {
            String lineaExtraida = entrada.readLine();
            if (lineaExtraida==null || lineaExtraida.isEmpty()) {
                throw new IllegalArgumentException("Petición vacía.");
            }
            elementosInicio = lineaExtraida.split(" ");
            if (elementosInicio.length<3) {
                throw new IllegalArgumentException("Formato de petición inválido: "+lineaExtraida);
            }
        } catch (IOException ex) {
            System.getLogger(PeticionWEB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return elementosInicio;

    }

    public String getMetodo() {
        return this.metodo;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVersion() {
        return this.version;
    }
    
    public String getCuerpoMensaje(){
        return this.cuerpoMensaje;
    }

    private Map<String, String> extraerCabecerasMetadatos(BufferedReader entrada) {
        String lineaExtraida;
        String [] partesCabecera;
        try {
            while (!(lineaExtraida=entrada.readLine()).isEmpty()) {
                partesCabecera=lineaExtraida.split(": ");
                String clave=partesCabecera[0];
                String valor=partesCabecera[1];
                cabecerasHTTP.put(clave, valor);
            }
        } catch (IOException ex) {
            System.getLogger(PeticionWEB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return cabecerasHTTP;
    }

    private String extraerCuerpoMensaje(BufferedReader entrada) {
        StringBuilder cuerpoMensaje = new StringBuilder();
        
        for (int i = 0; i < getContentLength(); i++) {
            try {
                cuerpoMensaje.append((char) entrada.read());
            } catch (IOException ex) {
                System.getLogger(PeticionWEB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } // Fin for
        
        return cuerpoMensaje.toString();
    }
    
    
    private int getContentLength(){
        int contentLegth=0;
        if (cabecerasHTTP.containsKey("Content-Length")) {
            contentLegth=Integer.parseInt(cabecerasHTTP.get("Content-Length"));
        }
        return contentLegth;
    }
    
    

} // Fin class
