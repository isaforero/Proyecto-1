package Boletamaster;


import java.time.LocalDateTime;

public abstract class Tiquete {
    protected String id;
    protected String estado;          
    protected boolean transferible;
    protected Cliente propietario;   
    protected double precio;
    protected LocalDateTime fechaCompra;

    protected Localidad localidad;    

    public Tiquete(String id, double precio, Localidad localidad) {
        this.id = id;
        this.precio = precio;
        this.localidad = localidad;
        this.estado = "DISPONIBLE";
        this.transferible = true;
        if (localidad != null) localidad.agregarTiquete(this);
    }


    public double calcularPrecioTotal(Administrador admin) {
        double total = precio;
        total = total + (total * admin.getPorcentajeDeServicio());
        total = total + admin.getCuotaDeEmision();
        return total;
    }


    public boolean transferir(Cliente destino) {
        if (!transferible) return false;
        if (!"VENDIDO".equals(estado)) return false;
        propietario = destino;
        estado = "TRANSFERIDO";
        return true;
    }

    void marcarVendido(Cliente c) {
        propietario = c;
        estado = "VENDIDO";
        fechaCompra = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getEstado() { return estado; }
    public Cliente getPropietario() { return propietario; }
    public Localidad getLocalidad() { return localidad; }
}