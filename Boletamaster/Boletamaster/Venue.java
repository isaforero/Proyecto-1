package Boletamaster;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venue {
    private String id;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    private String restricciones;

    private ArrayList<LocalDate> fechasOcupadas = new ArrayList<>();

    public Venue(String id, String nombre, String ubicacion, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public boolean reservarFecha(LocalDate fecha) {
        if (fechasOcupadas.contains(fecha)) return false;
        fechasOcupadas.add(fecha);
        return true;
    }

    public String getNombre() { return nombre; }
    public String getRestricciones() { return restricciones; }
    public void setRestricciones(String r) { this.restricciones = r; }
}

