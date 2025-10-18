package Boletamaster;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Localidad {
    private String id;
    private String nombre;
    private double precioBase;
    private boolean numerada;
    private int aforo;

    private Evento evento;                 
    private ArrayList<Tiquete> tiquetes;  
    private ArrayList<Oferta> ofertas;   

    public Localidad(String id, String nombre, double precioBase, boolean numerada, int aforo, Evento evento) {
        this.id = id; this.nombre = nombre; this.precioBase = precioBase;
        this.numerada = numerada; this.aforo = aforo; this.evento = evento;
        this.tiquetes = new ArrayList<Tiquete>();
        this.ofertas = new ArrayList<Oferta>();
    }

    public void agregarTiquete(Tiquete t) { if (t != null) tiquetes.add(t); }
    public void agregarOferta(Oferta o) { if (o != null) ofertas.add(o); }

    public double precioVigente(LocalDateTime ahora) {
        for (int i=0; i<ofertas.size(); i++) {
            Oferta o = ofertas.get(i);
            if (o.activa(ahora)) return precioBase * (1.0 - o.getDescuento());
        }
        return precioBase;
    }

    public Evento getEvento() { return evento; }
    public String getNombre() { return nombre; }
}