package tiquetera;

public class Tiquete {
    private final String evento;
    private final String localidad;
    private final double precioPagado;

    public Tiquete(String evento, String localidad, double precioPagado) {
        this.evento = evento;
        this.localidad = localidad;
        this.precioPagado = precioPagado;
    }

    public String getEvento()        { return evento; }
    public String getLocalidad()     { return localidad; }
    public double getPrecioPagado()  { return precioPagado; }

    @Override public String toString() {
        return evento + " | " + localidad + " | $" + String.format("%.2f", precioPagado);
    }
}
