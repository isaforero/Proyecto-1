package Boletamaster;


import java.util.ArrayList;

public class Cliente extends Usuario {
    private double saldo;
    private ArrayList<Tiquete> tiquetes;   

    public Cliente(String id, String login, String password) {
        super(id, login, password);
        this.saldo = 0;
        this.tiquetes = new ArrayList<Tiquete>();
    }

    public void abonarSaldo(double valor) {
        if (valor > 0) this.saldo += valor;
    }

    public Pago comprarTiquetes(ArrayList<Tiquete> items, String metodoPago, Administrador admin) {
        if (items == null || items.size() == 0) return null;

        double total = 0;
        for (int i=0; i<items.size(); i++) {
            Tiquete t = items.get(i);
            if (!"DISPONIBLE".equals(t.getEstado())) return null;
            total += t.calcularPrecioTotal(admin);  
        }

        if ("SALDO".equals(metodoPago) && total > saldo) return null;

        Pago pago = new Pago(total, metodoPago);
        for (int i=0; i<items.size(); i++) pago.agregarItem(items.get(i));
        pago.confirmar(this); 

        if ("SALDO".equals(metodoPago)) this.saldo -= total;
        return pago;
    }

    public boolean transferirTiquete(Tiquete t, Cliente destino) {
        if (t == null || destino == null) return false;
        if (t.getPropietario() != this) return false;
        return t.transferir(destino);
    }

    public double getSaldo() { return saldo; }
    public ArrayList<Tiquete> getTiquetes() { return tiquetes; }
    void agregarTiquete(Tiquete t) { if (t != null) tiquetes.add(t); }
}