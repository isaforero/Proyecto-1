package Boletamaster;

public abstract class Tiquete {
    protected String id;
    protected String estado;
    protected boolean transferible;
    protected Cliente propietario;
    protected double precio;
    protected Localidad localidad;

    public Tiquete(String id, double precio, Localidad localidad) {
        this.id = id;
        this.precio = precio;
        this.localidad = localidad;
        this.estado = "DISPONIBLE";
        this.transferible = true;
    }

    public double calcularPrecioTotal(Administrador admin) {
        double total = precio;
        total += total * admin.getPorcentajeServicio();
        total += admin.getCuotaEmision();
        return total;
    }

    public void marcarVendido(Cliente c) {
        propietario = c;
        estado = "VENDIDO";
        if (localidad != null) localidad.marcarVendido(); 
    }

    public boolean transferir(Cliente destino) {
        if (!transferible || !"VENDIDO".equals(estado)) return false;
        propietario = destino;
        estado = "TRANSFERIDO";
        return true;
    }

    public String getId() { return id; }
    public String getEstado() { return estado; }
    public Cliente getPropietario() { return propietario; }
    public Localidad getLocalidad() { return localidad; }
    public double getPrecio() { return precio; }
}