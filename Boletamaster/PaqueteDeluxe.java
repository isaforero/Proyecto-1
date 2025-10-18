package Boletamaster;

public class PaqueteDeluxe extends PaqueteTiquetes {
    public PaqueteDeluxe(String id, double precioTotal, String beneficios) {
        super(id, precioTotal, beneficios);
        this.transferible = false; 
    }
}