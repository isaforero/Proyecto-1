package Boletamaster;


public class Administrador extends Usuario {
    private double porcentajeDeServicio; 
    private double cuotaDeEmision;       

    public Administrador(String id, String login, String password) {
        super(id, login, password);
        this.porcentajeDeServicio = 0;
        this.cuotaDeEmision = 0;
    }

    public void configurarTarifas(double porcentaje, double cuota) {
        this.porcentajeDeServicio = porcentaje;
        this.cuotaDeEmision = cuota;
    }

    public boolean cancelarEvento(Evento e) {
        if (e == null) return false;
        e.setEstado("CANCELADO");
        return true;
    }

    public double getPorcentajeDeServicio() { return porcentajeDeServicio; }
    public double getCuotaDeEmision() { return cuotaDeEmision; }
}