package Boletamaster;


import java.time.LocalDateTime;

public class Oferta {
    private String id;
    private double descuento; 
    private LocalDateTime inicio;
    private LocalDateTime fin;

    public Oferta(String id, double descuento, LocalDateTime inicio, LocalDateTime fin) {
        this.id = id; this.descuento = descuento; this.inicio = inicio; this.fin = fin;
    }

    public boolean activa(LocalDateTime ahora) {
        return (ahora.isAfter(inicio) || ahora.isEqual(inicio))
            && (ahora.isBefore(fin)   || ahora.isEqual(fin));
    }

    public double getDescuento() { return descuento; }
}