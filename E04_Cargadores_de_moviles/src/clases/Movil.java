package clases;



public class Movil extends Thread{
    private GestorCargadores cargadores;
    

    public Movil(String nombre, GestorCargadores cargadores) {
        super(nombre);
        this.cargadores=cargadores;
    }

    @Override
    public void run() {
        String nombre=this.getName();
        
        for (int i = 1; i <= 2; i++) {
            cargadores.usar(nombre,i);
            cargadores.dejarUsar(nombre);
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            
        } // Fin for


    }


} // Fin class