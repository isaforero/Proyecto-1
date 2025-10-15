package tiquetera;

class Administrador extends Usuario {
    private double porcentajeServicio = 0.1; // 10%

    public Administrador(String id, String login, String password, double saldo) {
        super(id, login, password, saldo);
    }

    public double aplicarServicio(double precioBase) {
        return precioBase + (precioBase * porcentajeServicio);
    }
}
