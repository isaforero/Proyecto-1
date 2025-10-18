package Boletamaster;

public class TiqueteNumerado extends Tiquete {
    private String asiento; 

    public TiqueteNumerado(String id, double precio, Localidad localidad, String asiento) {
        super(id, precio, localidad);
        this.asiento = asiento;
    }

    public String getAsiento() { return asiento; }
}