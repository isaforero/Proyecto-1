package tiquetera;

public abstract class Usuario {
    protected final String id;
    protected final String login;
    private final String password;
    private double saldo;

    public Usuario(String id, String login, String password, double saldo) {
        this.id = id; this.login = login; this.password = password; this.saldo = saldo;
    }

    public String getLogin() { return login; }
    public double getSaldo() { return saldo; }
    
    public boolean autenticar(String pwd) { return password.equals(pwd); }

    public void recargar(double monto) { if (monto > 0) saldo += monto; }

    protected boolean debitar(double monto) {
        if (monto <= 0 || saldo < monto) return false;
        saldo -= monto;
        return true;
    }
}