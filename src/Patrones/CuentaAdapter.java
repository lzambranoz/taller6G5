/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.Currency;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scmz2607
 */
public class CuentaAdapter implements Cuenta{
    protected Account cuenta;
    protected Currency moneda;
    protected Locale locale;

    public CuentaAdapter(int id, double monto) {
        this.cuenta = new Account(id,monto);
        this.locale = Locale.US;
        this.moneda = Currency.getInstance(locale);
    }

    @Override
    public double Balance() {
        return this.cuenta.getAmount();
    }

    @Override
    public boolean Retirar(double monto) {
        String estado = this.cuenta.withdraw(monto);
        try {
            if (estado.startsWith("Error")) {
                throw new TransactionException("Saldo insuficiente");
            }else{
                //aqui implementar metodo recursivo
            }
            return true;
        } catch (TransactionException ex) {
            Logger.getLogger(CuentaAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean Depositar(double monto) {
        this.cuenta.deposit(monto);
        return true;
    }

    @Override
    public int getId() {
        return this.cuenta.getId();
    }
}
