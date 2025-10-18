package Boletamaster;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pago {
    private static int SEC = 1;

    private int idPago;
    private LocalDateTime fecha;
    private double monto;
    private String metodoPago;   
    private String estadoPago;  

    private ArrayList<Tiquete> items; 

    public Pago(double monto, String metodoPago) {
        this.idPago = SEC++;
        this.fecha = LocalDateTime.now();
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = "PENDIENTE";
        this.items = new ArrayList<Tiquete>();
    }

    public void agregarItem(Tiquete t) { 
        if (t != null) items.add(t); 
    }

    public void confirmar(Cliente comprador) {
        for (int i = 0; i < items.size(); i++) {
            Tiquete it = items.get(i);
            if (!"DISPONIBLE".equals(it.getEstado())) {
                estadoPago = "RECHAZADO";
                return;
            }
            if (it instanceof PaqueteTiquetes) {
                PaqueteTiquetes p = (PaqueteTiquetes) it;
                ArrayList<Tiquete> internos = p.getTiquetesIncluidos();
                for (int j = 0; j < internos.size(); j++) {
                    if (!"DISPONIBLE".equals(internos.get(j).getEstado())) {
                        estadoPago = "RECHAZADO";
                        return;
                    }
                }
            }
        }


        for (int i = 0; i < items.size(); i++) {
            Tiquete it = items.get(i);
            it.marcarVendido(comprador);    
            comprador.agregarTiquete(it);   

            if (it instanceof PaqueteTiquetes) {
                PaqueteTiquetes p = (PaqueteTiquetes) it;
                ArrayList<Tiquete> internos = p.getTiquetesIncluidos();
                for (int j = 0; j < internos.size(); j++) {
                    Tiquete tInt = internos.get(j);
                    tInt.marcarVendido(comprador); 
                    comprador.agregarTiquete(tInt); 
                }
            }
        }

        estadoPago = "APROBADO";
    }

    public int getIdPago() { return idPago; }
    public String getEstadoPago() { return estadoPago; }
}