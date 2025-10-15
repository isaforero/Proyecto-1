package tiquetera;

import java.util.*;

public class Main {

    static class Usuario {
        String nombre;
        String user;
        String pass;
        double saldo; 

        Usuario(String nombre, String user, String pass, double saldo){
            this.nombre = nombre;
            this.user   = user;
            this.pass   = pass;
            this.saldo  = saldo;
        }
    }

    static class Venue {
        String nombre; String ciudad;
        Venue(String nombre, String ciudad){ this.nombre = nombre; this.ciudad = ciudad; }
    }

    static class Evento {
        String nombre; String fecha;
        Venue venue;
        List<Localidad> localidades = new ArrayList<>();
        Evento(String nombre, String fecha, Venue venue){
            this.nombre = nombre; this.fecha = fecha; this.venue = venue;
        }
    }

    static class Localidad {
        String nombre; double precio; int disponibles;
        Localidad(String nombre, double precio, int disponibles){
            this.nombre = nombre; this.precio = precio; this.disponibles = disponibles;
        }
    }

    static class Tiquete {
        String evento; String localidad; double precioPagado;
        Tiquete(String evento, String localidad, double precioPagado){
            this.evento = evento; this.localidad = localidad; this.precioPagado = precioPagado;
        }
        public String toString(){
            return evento + " | " + localidad + " | $" + String.format("%.2f", precioPagado);
        }
    }

    // Base de datos
    static List<Usuario> usuarios = new ArrayList<>();
    static List<Evento>  eventos  = new ArrayList<>();
    static List<Tiquete> tiquetesDelCliente = new ArrayList<>();

    // Cargos simples
    static double porcentajeServicio = 0.10; 
    static double cuotaEmisionFija   = 2.00;

    // Control de sesión
    static boolean seguirLogueado = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        cargarDatosDePrueba(); 

        Usuario actual = null;

        while (true) {
            if (actual == null) {
                System.out.println("\n BOLETAMASTER ");
                System.out.println("1- Iniciar sesión");
                System.out.println("2- Registrarse");
                System.out.println("0- Salir");
                System.out.print("Elige opción: ");
                String op = in.nextLine().trim();

                if (op.equals("1")) {
                    actual = login(in);
                    if (actual == null) {
                        System.out.println("Credenciales incorrectas.");
                    } else {
                        System.out.println("Bienvenido, " + actual.nombre + " (cliente)");
                    }
                } else if (op.equals("2")) {
                    registrar(in);
                } else if (op.equals("0")) {
                    System.out.println("¡Chao!");
                    break;
                } else {
                    System.out.println("Opción inválida.");
                }

            } else {
                menuCliente(in, actual);

                if (!seguirLogueado) {
                    actual = null;
                    seguirLogueado = true;
                    tiquetesDelCliente.clear();
                }
            }
        }
        in.close();
    }

    // Menu
    static void menuCliente(Scanner in, Usuario cliente){
        System.out.println("\n MENÚ CLIENTE (saldo: $" + String.format("%.2f", cliente.saldo) + ") ");
        System.out.println("1- Ver eventos");
        System.out.println("2- Ver localidades");
        System.out.println("3- Comprar 1 tiquete");
        System.out.println("4- Mis tiquetes");
        System.out.println("5- Recargar saldo");
        System.out.println("9- Cerrar sesión");
        System.out.print("Opción: ");
        String op = in.nextLine().trim();

        switch (op) {
            case "1" -> listarEventos();
            case "2" -> verLocalidadesDeEvento(in);
            case "3" -> comprarTiquete(in, cliente);
            case "4" -> verMisTiquetes();
            case "5" -> recargarSaldo(in, cliente);
            case "9" -> { System.out.println("Cerrando sesión..."); seguirLogueado = false; }
            default -> System.out.println("Opción inválida.");
        }
    }

    // Flujos
    static Usuario login(Scanner in){
        System.out.print("Usuario: ");
        String u = in.nextLine().trim();
        System.out.print("Contraseña: ");
        String p = in.nextLine().trim();

        for (Usuario x : usuarios) {
            if (x.user.equals(u) && x.pass.equals(p)) return x;
        }
        return null;
    }

    static void registrar(Scanner in){
        System.out.println("\n REGISTRO DE NUEVO USUARIO ");
        System.out.print("Nombre: ");
        String nombre = in.nextLine().trim();
        System.out.print("Usuario: ");
        String user = in.nextLine().trim();
        

        for (Usuario x : usuarios){
            if (x.user.equals(user)) {
                System.out.println("Ese usuario ya existe. Intenta otro.");
                return;
            }
        }

        System.out.print("Contraseña: ");
        String pass = in.nextLine().trim();
        Usuario nuevo = new Usuario(nombre, user, pass, 100.0); 
        usuarios.add(nuevo);
        System.out.println("¡Cliente registrado con éxito! Puedes iniciar sesión con tu nuevo usuario.");
    }

    static void listarEventos(){
        System.out.println("\n EVENTOS ");
        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);
            System.out.println(i + "- " + e.nombre + " | " + e.fecha + " | " + e.venue.nombre + " ("+e.venue.ciudad+")");
        }
    }

    static void listarEventosConLocalidades(){
        System.out.println("\n EVENTOS Y LOCALIDADES ");
        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);
            System.out.println(i + "- " + e.nombre + " | " + e.fecha + " | " + e.venue.nombre);
            for (int j = 0; j < e.localidades.size(); j++) {
                Localidad l = e.localidades.get(j);
                System.out.println("    " + j + ") " + l.nombre + " | $" + l.precio + " | disp=" + l.disponibles);
            }
        }
    }

    static void verLocalidadesDeEvento(Scanner in){
        listarEventos();
        System.out.print("Elige el número del evento: ");
        int idx = leerEntero(in);
        if (idx < 0 || idx >= eventos.size()){ System.out.println("Evento inválido."); return; }
        Evento e = eventos.get(idx);
        System.out.println("\nLocalidades de: " + e.nombre);
        for (int j = 0; j < e.localidades.size(); j++) {
            Localidad l = e.localidades.get(j);
            System.out.println(j + "- " + l.nombre + " | $" + l.precio + " | disp=" + l.disponibles);
        }
    }

    static void comprarTiquete(Scanner in, Usuario cliente){
        listarEventosConLocalidades();
        System.out.print("Elige el número del evento: ");
        int ie = leerEntero(in);
        if (ie < 0 || ie >= eventos.size()){ System.out.println("Evento inválido."); return; }
        Evento e = eventos.get(ie);

        System.out.print("Elige el número de la localidad: ");
        int il = leerEntero(in);
        if (il < 0 || il >= e.localidades.size()){ System.out.println("Localidad inválida."); return; }
        Localidad l = e.localidades.get(il);

        if (l.disponibles <= 0) {
            System.out.println("No hay tiquetes disponibles en " + l.nombre);
            return;
        }

        double base = l.precio;
        double total = base + base * porcentajeServicio + cuotaEmisionFija;

        if (cliente.saldo < total) {
            System.out.println("Saldo insuficiente. Necesitas $" + String.format("%.2f", total));
            return;
        }

        // efectuar compra
        cliente.saldo -= total;
        l.disponibles -= 1;
        Tiquete t = new Tiquete(e.nombre, l.nombre, total);
        tiquetesDelCliente.add(t);

        System.out.println("\nCompra realizada");
        System.out.println("Evento: " + e.nombre);
        System.out.println("Localidad: " + l.nombre);
        System.out.println("Total pagado: $" + String.format("%.2f", total));
        System.out.println("Saldo restante: $" + String.format("%.2f", cliente.saldo));
    }

    static void recargarSaldo(Scanner in, Usuario cliente){
        System.out.print("Monto a recargar: ");
        double monto = leerDouble(in);
        if (monto <= 0) {
            System.out.println("Monto inválido.");
            return;
        }
        cliente.saldo += monto;
        System.out.println("Recarga exitosa. Nuevo saldo: $" + String.format("%.2f", cliente.saldo));
    }

    static void verMisTiquetes(){
        if (tiquetesDelCliente.isEmpty()){
            System.out.println("No tienes tiquetes.");
            return;
        }
        System.out.println("\n Tiquetes ");
        for (int i = 0; i < tiquetesDelCliente.size(); i++) {
            System.out.println(i + "- " + tiquetesDelCliente.get(i));
        }
    }

    static int leerEntero(Scanner in){
        try { return Integer.parseInt(in.nextLine().trim()); }
        catch(Exception e){ return -1; }
    }

    static double leerDouble(Scanner in){
        try { return Double.parseDouble(in.nextLine().trim()); }
        catch(Exception e){ return -1; }
    }

    // ejemplos
    static void cargarDatosDePrueba(){
        usuarios.add(new Usuario("Pablo Cliente", "cliente", "123", 200));

        Venue v1 = new Venue("Movistar Arena","Bogotá");
        Venue v2 = new Venue("Teatro Mayor","Bogotá");

        Evento e1 = new Evento("Concierto bad bunny", "2025-12-10", v1);
        e1.localidades.add(new Localidad("VIP", 80, 5));
        e1.localidades.add(new Localidad("General", 40, 8));

        Evento e2 = new Evento("Obra de teatro comedia", "2025-11-02", v2);
        e2.localidades.add(new Localidad("Platea", 60, 4));
        e2.localidades.add(new Localidad("Galería", 30, 6));

        eventos.add(e1); eventos.add(e2);
    }
}