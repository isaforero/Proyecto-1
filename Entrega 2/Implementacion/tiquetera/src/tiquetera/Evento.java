package tiquetera;


import java.util.ArrayList;
import java.util.List;

public class Evento {
    private final String id;
    private final String nombre;
    private final String fecha;
    private final Venue venue;
    private final Organizador organizador;
    private final List<Localidad> localidades = new ArrayList<>();

    public Evento(String id, String nombre, String fecha, Venue v, Organizador org) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.venue = v;
        this.organizador = org;
    }

    public Localidad crearLocalidad(String id, String nombre, double precio, int aforo) {
        Localidad loc = new Localidad(id, nombre, precio, aforo, this);
        localidades.add(loc);
        return loc;
    }

    public String getId()                  { return id; }
    public String getNombre()              { return nombre; } 
    public String getFecha()               { return fecha; }
    public Venue getVenue()                { return venue; }
    public Organizador getOrganizador()    { return organizador; }
    public List<Localidad> getLocalidades(){ return localidades; }

    @Override public String toString() {
        return nombre + " | " + fecha + " | " + venue;
    }
}