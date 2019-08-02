package Patrones;

import java.util.LinkedList;

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

    @Override //solo si lo completa lo retira del manejador
    public boolean retirar(double monto) {
        LinkedList<Integer> listBilletes = new LinkedList<>();
        double montoFaltante = monto;
        Manejador manejInicio = this;
        do{
            double saldoManejador = manejInicio.getCantidad() * manejInicio.getDenominacion();
            if (montoFaltante >= saldoManejador){
                montoFaltante -= saldoManejador;
                listBilletes.add(manejInicio.getCantidad());
            }else{
                int cantBilletes = (int) (montoFaltante / manejInicio.getDenominacion());
                montoFaltante -= (cantBilletes * manejInicio.getDenominacion());
                listBilletes.add(cantBilletes);
            }
        }while(montoFaltante > 0 || (manejInicio = manejInicio.getNext())!=null);
        if (montoFaltante == 0){
            manejInicio = this;
            for(int cantB: listBilletes){
                manejInicio.setCantidad(manejInicio.getCantidad()-cantB);
                manejInicio = manejInicio.getNext();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean depositar(int cantidad, double den) {
        if(den != this.denominacion)
            return false;
        this.cantidad += cantidad;
        return true;
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

    public void setDenominacion(double denominacion) {
        this.denominacion = denominacion;
    }
    
    @Override
    public Manejador getNext() {
        return this.next;
    }
    
    

}