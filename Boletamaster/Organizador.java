package Boletamaster;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Organizador extends Cliente {
    private ArrayList<Evento> listaEventos;

    public Organizador(String id, String login, String password) {
        super(id, login, password);
        this.listaEventos = new ArrayList<Evento>();
    }

    public Evento crearEvento(String id, String nombre, Venue venue,
                              LocalDate fecha, LocalTime hora, String tipoEvento) {
        Evento e = new Evento(id, nombre, venue, fecha, hora, tipoEvento);
        listaEventos.add(e);
        return e;
    }

    public Localidad crearLocalidad(Evento e, String id, String nombre,
                                    double precioBase, boolean numerada, int aforo) {
        Localidad l = new Localidad(id, nombre, precioBase, numerada, aforo, e);
        e.agregarLocalidad(l);
        return l;
    }

    public Oferta crearOferta(Localidad l, String id, double descuento,
                              java.time.LocalDateTime inicio,
                              java.time.LocalDateTime fin) {
        Oferta o = new Oferta(id, descuento, inicio, fin);
        l.agregarOferta(o);
        return o;
    }

    public ArrayList<Evento> getListaEventos() { 
        return listaEventos; 
    }
}