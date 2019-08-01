package Patrones;

public class ManejadorDinero implements Manejador
{
    protected int cantidad;
    protected double denominacion;
    private Manejador next;
    
    public ManejadorDinero(int cantidad, double denominacion){
        this.cantidad = cantidad;
        this.denominacion = denominacion;
    }

    @Override
    public void setNext(Manejador m) {
        this.next = m;
    }

    @Override
    public boolean retirar(double monto) {
        return false;
    }

    @Override
    public boolean depositar(int cantidad, double denominacion) {
        return false;
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public double getDenominacion() {
        return this.denominacion;
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public Manejador getNext() {
        return this.next;
    }

}