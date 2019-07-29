package Patrones;

public interface Manejador{
    public void crearManejador(double monto, double denominacion);
    public void setNext(Manejador m);
    public boolean retirar(double monto);
    public boolean depositar(double monto, double denominacion);
}
