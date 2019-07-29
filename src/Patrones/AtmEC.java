/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class AtmEC {
    /*
    protected final Currency currency=Locale.UK;
    protected double dinero = 0;
    protected ArrayList <Manejador> manejadores; // Cada manejador puede entregar dinero de una sola denominación

    // -----------------
    public AtmEC() {
      manejadores = new ArrayList<Manejador>();
    }
    */
    
    private static AtmEC instance;
    private Currency moneda;
    private double dinero;
    private Manejador manejador;
    
    private AtmEC(Currency moneda, Manejador manejador){
        this.moneda = moneda;
        this.dinero = calcularDineroInicial(manejador);
        this.manejador = manejador;
    }
    
    public static AtmEC getInstance(Currency moneda, Manejador manejador){
        if(instance == null){
            instance = new AtmEC(moneda,manejador);
        }
        return instance;
    }
    
    // -----------------
    public double getTotal() {
        return this.dinero;
    }

    // -----------------
    public boolean sacarDinero(double dinero) {
       manejador.retirar(dinero); //false cuando dinero<al dinero del ATM y cuando no hay la cantidad necesaria de billetes para retirar
    }

    // -----------------
    public void ingresarDinero(double dinero, int denominacion) {
        manejador.depositar(dinero, denominacion);
    }
    
    public void addManejador(Manejador m){
        if(this.manejador == null){
            manejador = m;
        }else{
            manejador.setNext(m);
        }
    }
    
    public Manejador removeManejador(int i){
        
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public static void transaction(Account cuenta){
        // here is where most of the work is
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice = in.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = in.nextFloat();
                if(amount > cuenta.getAmount() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // Todo: verificar que se puede realizar el retiro del atm

                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount);

                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta); 
                }
            break; 
            case 2:
                    // option number 2 is depositing 
                    float deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = in.nextFloat();
                    // Todo: actualizar tanto la cuenta como el atm
                    
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta);
            break; 
            case 3:
                    // Todo: mostrar el balance de la cuenta
                    // "Your balance is "+balance
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public static void anotherTransaction(Account cuenta){
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        anotherTransaction = in.nextInt();
        if(anotherTransaction == 1){
            transaction(cuenta); // call transaction method
        } else if(anotherTransaction == 2){
            System.out.println("Thanks for choosing us. Good Bye!");
        } else {
            System.out.println("Invalid choice\n\n");
            anotherTransaction(cuenta);
        }
    }

    private double calcularDineroInicial(Manejador manejador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
