package clases;

public class Cliente extends Thread {

    private Panaderia panaderia;
    private int cantidad;

    public Cliente(String nombre, int cantidad, Panaderia panaderia) {
        super(nombre);
        this.panaderia = panaderia;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        try {
            while (panaderia.isContinuarFabricacion()) {
                panaderia.puedoComprar(this.getName(), this.cantidad);
                        return;
            }
        } catch (InterruptedException ex) {
            System.out.println("Error");
        }
    }

} // Fin class
