package Boletamaster;

import java.util.ArrayList;

public class PaqueteTiquetes extends Tiquete {
    private double precioTotal;
    private String beneficios;
    private ArrayList<Tiquete> tiquetesIncluidos;

    public PaqueteTiquetes(String id, double precioTotal, String beneficios) {
        super(id, precioTotal, null);
        this.precioTotal = precioTotal;
        this.beneficios = beneficios;
        this.tiquetesIncluidos = new ArrayList<>();
    }

    public void agregar(Tiquete t) { tiquetesIncluidos.add(t); }

    public boolean esTemporada() {
        Evento e = null;
        for (Tiquete t : tiquetesIncluidos) {
            if (t.getLocalidad() == null) continue;
            Evento ev = t.getLocalidad().getEvento();
            if (e == null) e = ev;
            else if (e != ev) return true;
        }
        return false;
    }
}