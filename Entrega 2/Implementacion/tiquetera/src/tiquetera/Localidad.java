package tiquetera;


public class Localidad {
    private final String id;
    private final String nombre;
    private final double precio;
    private int disponibles;
    private final Evento evento; 

    public Localidad(String id, String nombre, double precio, int aforo, Evento evento) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponibles = Math.max(0, aforo);
        this.evento = evento;
    }

    public String getId()        { return id; }
    public String getNombre()    { return nombre; }
    public double getPrecio()    { return precio; }
    public int getDisponibles()  { return disponibles; }
    public Evento getEvento()    { return evento; } 

    public boolean venderUno() {
        if (disponibles <= 0) return false;
        disponibles--;
        return true;
    }

    @Override public String toString() {
        return nombre + " | $" + String.format("%.2f", precio) + " | disp=" + disponibles;
    }
}