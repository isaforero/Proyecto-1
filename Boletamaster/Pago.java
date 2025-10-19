package Boletamaster;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pago {
    private static int SEC = 1;

    private int idPago;
    private LocalDateTime fecha;
    private double total;
    private String metodo;
    private String estado;           
    private ArrayList<Tiquete> items;

    public Pago(double total, String metodo) {
        this.idPago = SEC++;
        this.fecha = LocalDateTime.now();
        this.total = total;
        this.metodo = metodo;
        this.estado = "APROBADO";    
        this.items = new ArrayList<>();
    }

    public void agregarItem(Tiquete t) { items.add(t); }

    public double getTotal() { return total; }
    public int getIdPago() { return idPago; }
    public String getEstado() { return estado; }
    public String getMetodo() { return metodo; }
    public LocalDateTime getFecha() { return fecha; }
}