package tiquetera;

public class Venue {
    private final String nombre;
    private final String ciudad;

    public Venue(String nombre, String ciudad) {
        this.nombre = nombre; this.ciudad = ciudad;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }

    @Override public String toString() {
        return nombre + " (" + ciudad + ")";
    }
}