package Boletamaster;

import java.util.ArrayList;

public class Localidad {
    private String id;
    private String nombre;
    private double precioBase;
    private boolean numerada;
    private int aforo;
    private Evento evento;
    private ArrayList<Oferta> ofertas;
    private int vendidos = 0;

    public Localidad(String id, String nombre, double precioBase, boolean numerada, int aforo, Evento evento) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.numerada = numerada;
        this.aforo = aforo;
        this.evento = evento;
        this.ofertas = new ArrayList<>();
    }

    public void agregarOferta(Oferta o) { if (o != null) ofertas.add(o); }

    public double getPrecioVigente() {
        if (!ofertas.isEmpty() && ofertas.get(0).isActiva()) {
            return precioBase * (1 - ofertas.get(0).getDescuento());
        }
        return precioBase;
    }

    public Tiquete crearTiqueteSimple(String id) {
        return new TiqueteSimple(id, getPrecioVigente(), this);
    }
    public Tiquete crearTiqueteNumerado(String id, String asiento) {
        return new TiqueteNumerado(id, getPrecioVigente(), this, asiento);
    }

    public int obtenerAsientosDisponibles() {
        return Math.max(0, aforo - vendidos);
    }

    public void marcarVendido() { vendidos++; }


    public Evento getEvento() { return evento; }
    public String getNombre() { return nombre; }
    public boolean isNumerada() { return numerada; }
    public int getAforo() { return aforo; }
}