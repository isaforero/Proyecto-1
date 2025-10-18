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
        this.tiquetesIncluidos = new ArrayList<Tiquete>();
    }

    public void agregar(Tiquete t) { 
        if (t != null) tiquetesIncluidos.add(t); 
    }

    public ArrayList<Tiquete> getTiquetesIncluidos() {
        return tiquetesIncluidos;
    }

    public boolean esTemporada() {
        Evento primero = null;
        for (int i = 0; i < tiquetesIncluidos.size(); i++) {
            Tiquete t = tiquetesIncluidos.get(i);
            if (t.getLocalidad() == null) continue;
            Evento ev = t.getLocalidad().getEvento();
            if (primero == null) primero = ev;
            else if (ev != primero) return true;
        }
        return false;
    }

    @Override
    public double calcularPrecioTotal(Administrador admin) {
        return precioTotal + admin.getCuotaDeEmision();
    }
}