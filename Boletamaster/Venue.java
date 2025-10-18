package Boletamaster;


public class Venue {
    private String id;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    private String restricciones;

    public Venue(String id, String nombre, String ubicacion, int capacidad) {
        this.id = id; this.nombre = nombre; this.ubicacion = ubicacion; this.capacidad = capacidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public int getCapacidad() { return capacidad; }
    public String getRestricciones() { return restricciones; }
    public void setRestricciones(String r) { this.restricciones = r; }
}