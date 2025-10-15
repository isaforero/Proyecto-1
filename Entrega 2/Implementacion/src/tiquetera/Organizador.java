package tiquetera;

import java.util.ArrayList;
import java.util.List;
public class Organizador extends Usuario {
    private final List<Evento> eventos = new ArrayList<>();

    public Organizador(String id, String login, String password, double saldo) {
        super(id, login, password, saldo);
    }

    public Evento crearEvento(String id, String nombre, String fecha, Venue v) {
        Evento e = new Evento(id, nombre, fecha, v, this);
        eventos.add(e);
        return e;
    }

    public List<Evento> getEventos() { return eventos; }
}