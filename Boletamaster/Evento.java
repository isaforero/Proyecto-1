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

    public Evento(String id, String nombre, Venue venue,
                  LocalDate fecha, LocalTime hora, String tipo) {
        this.id = id; this.nombre = nombre; this.venue = venue;
        this.fecha = fecha; this.hora = hora; this.tipo = tipo;
        this.estado = "PROGRAMADO";
        this.localidades = new ArrayList<Localidad>();
    }

    public void agregarLocalidad(Localidad l) { if (l != null) localidades.add(l); }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public Venue getVenue() { return venue; }
}