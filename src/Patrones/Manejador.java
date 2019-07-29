package Patrones;

public interface Manejador{
    public void crearManejador(int monto, int denominacion);
    public void setNext(Manejador m);
    public boolean retirar(int monto);
    public boolean depositar(int monto, int denominacion);
}
