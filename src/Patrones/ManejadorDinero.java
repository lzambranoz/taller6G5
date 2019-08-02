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
        if (monto==0 || monto<0) {
            return false;
        }if (monto%this.denominacion==0 && this.cantidad>0) {
            if (retirar(this.cantidad-this.denominacion)) {
                this.cantidad=(int)(this.cantidad-(monto/this.denominacion));
                return true;
            }return false;
            
        } else{
            if (next!=null) {
                return next.depositar(cantidad, denominacion);
            }
            else{
                return false;
            }
        }
    }

    @Override
    public boolean depositar(int cantidad, double denominacion) {
        if (this.denominacion == denominacion) {
            this.cantidad = (int) (this.cantidad + (cantidad/denominacion));
            return true;
        }else{
            if (next!=null) {
                return next.depositar(cantidad, denominacion);
            }
            else{
                return false;
            }
        }
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