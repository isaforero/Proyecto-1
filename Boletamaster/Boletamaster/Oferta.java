package Boletamaster;

public class Oferta {
    private String id;
    private double descuento;
    private boolean activa;

    public Oferta(String id, double descuento) {
        this.id = id;
        this.descuento = descuento;
        this.activa = true;
    }

    public double getDescuento() { return descuento; }
    public boolean isActiva() { return activa; }
}