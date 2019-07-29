package Patrones;

public class ManejadorDinero implements Manejador
{
    protected int monto;
    protected int denominacion;
    private Manejador next;

    /*public ManejadorDinero(int monto, int denominacion){
        this.monto = monto; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }*/

    public int getMonto(){ return monto; }
    public int getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }

    @Override
    public boolean retirar(int monto){
        
        return false;
    }
    @Override
    public boolean depositar(int monto, int denominacion){
        
        return false;
    }

    @Override
    public void crearManejador(int monto, int denominacion) {
        this.monto=monto;
        this.denominacion=denominacion;
    }

    @Override
    public void setNext(Manejador m) {
        this.next = m;
    }

    
}