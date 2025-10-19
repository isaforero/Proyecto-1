package Boletamaster;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Evento {
    private String id;
    private String nombre;
    private String tipo;
    private String estado;
    private LocalDate fecha;
    private LocalTime hora;
    private Venue venue;
    private ArrayList<Localidad> localidades;

    public Evento(String id, String nombre, Venue v, LocalDate fecha, LocalTime hora, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.venue = v;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.estado = "PROGRAMADO";
        this.localidades = new ArrayList<>();
    }

    public void agregarLocalidad(Localidad l) { localidades.add(l); }
    public ArrayList<Localidad> getLocalidades() { return localidades; }

    public boolean estaDisponible() { 
        return !"CANCELADO".equals(estado); 
    }

    public String getNombre() { return nombre; }
    public Venue getVenue() { return venue; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}