package Patrones;

public interface Manejador{
    public void setNext(Manejador m);
    public boolean retirar(double monto);
    public boolean depositar(int cantidad, int denominacion);
    
    public int getCantidad();
    public double getDenominacion();
    public void setCantidad(int cantidad);
    
}
