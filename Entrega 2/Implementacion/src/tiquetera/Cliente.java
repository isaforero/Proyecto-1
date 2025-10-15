package tiquetera;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente extends Usuario {
    private final List<Tiquete> tiquetes = new ArrayList<>();

    public Cliente(String id, String login, String password, double saldo) {
        super(id, login, password, saldo);
    }

 
    public void comprarUno(Localidad loc) {
        if (loc == null) {
            System.out.println("Localidad inválida.");
            return;
        }
        if (loc.getDisponibles() <= 0) {
            System.out.println("No hay tiquetes disponibles en " + loc.getNombre());
            return;
        }

        double base  = loc.getPrecio();
        double total = base + base * 0.10 + 2.0; 

        if (!debitar(total)) {
            System.out.println("Saldo insuficiente. Necesitas $" + String.format("%.2f", total));
            return;
        }
        if (!loc.venderUno()) { 
            System.out.println("Se agotó.");
            return;
        }

        Tiquete t = new Tiquete(loc.getEvento().getNombre(), loc.getNombre(), total);
        tiquetes.add(t);

        System.out.println("Compra exitosa: " + t);
        System.out.println("Nuevo saldo: $" + String.format("%.2f", getSaldo()));
    }

    public void listarTiquetes() {
        System.out.println("Tiquetes de " + getLogin() + ":");
        if (tiquetes.isEmpty()) { System.out.println(" - (ninguno)"); return; }
        for (Tiquete t : tiquetes) System.out.println(" - " + t);
    }

    public List<Tiquete> getTiquetes() {
        return Collections.unmodifiableList(tiquetes);
    }
}