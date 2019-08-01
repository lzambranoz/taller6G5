/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmEC {
    
    private static AtmEC instance;
    private Currency moneda;
    private double dinero;
    private Manejador manejador;
    
    private AtmEC(){
        this.moneda = Currency.getInstance(Locale.US);
        this.dinero = 0;
        this.manejador = null;
    }
    
    public static AtmEC getInstance(){
        if(instance == null){
            instance = new AtmEC();
        }
        return instance;
    }
    
    public double getTotal() {
        return this.dinero;
    }

    public boolean sacarDinero(double dinero) { //asumir que siempre se deposita de la misma demonicacion
       if(manejador.retirar(dinero)){ //false cuando dinero<al dinero del ATM y cuando no hay la cantidad necesaria de billetes para retirar
           return true;
       }else{
           return false;
       }
    }

    public void ingresarDinero(int cantidad, double denominacion) {
        manejador.depositar(cantidad, denominacion);
    }
    
    public void addManejador(Manejador m){
        if(this.manejador == null){
            manejador = m;
        }else{
            manejador.setNext(m); //cambiar, siempre debe agregar al siguiente del siguiente
        }
    }

    public void setManejador(Manejador manejador) {
        this.manejador = manejador;
    }
    
    public Manejador removeManejador(double i){//cambie a double porque la denominacion es double y debo buscar eso, verdad?
        Manejador m = new ManejadorDinero(0,0);
        if(this.manejador.getDenominacion() == i){
            m = this.manejador;
            this.setManejador(this.manejador.getNext());
        }else if(this.manejador.getNext().getDenominacion() == i){
            m = this.manejador.getNext();
            Manejador after = this.manejador.getNext().getNext();
            this.manejador.setNext(after);
        }else{
            //aqui deberia aplicarle el recursivo
        }
        
        return m;
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public void transaction(CuentaAdapter cuenta){
        // here is where most of the work is
        Scanner scanner = new Scanner(System.in);
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice = scanner.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = scanner.nextFloat();
                if(amount > cuenta.Balance() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // Todo: verificar que se puede realizar el retiro del atm
                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount); //ESTO RETORNA UN BOOLEANO, cual es el fin
                    if(sacarDinero(amount)){
                        cuenta.Retirar(amount); //Aqui lanza el error
                        instance.sacarDinero(amount); 
                    }
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta); 
                }
            break; 
            case 2:
                // option number 2 is depositing 
                float deposit; 
                System.out.println("Please enter amount you would wish to deposit: "); 
                deposit = scanner.nextFloat();
                // Todo: actualizar tanto la cuenta como el atm
                // Todo: Mostrar resumen de transacción o error
                // "You have withdrawn "+amount+" and your new balance is "+balance;
                //cuenta.Depositar(deposit, );
                
                
                
                
                
                anotherTransaction(cuenta);
                break; 
            case 3:
                // Todo: mostrar el balance de la cuenta
                // "Your balance is "+balance
                System.out.println("Your balance is: "+cuenta.Balance());
                anotherTransaction(cuenta); 
                break;
            case 4:
                // Todo: mostrar el balance del ATM con los billetes en cada manejador
                System.out.println("Balance ");
                anotherTransaction(cuenta); 
                break;
            default:
                System.out.println("Invalid option:\n\n"); 
                anotherTransaction(cuenta);
                break;
        }
    }
    public void anotherTransaction(CuentaAdapter cuenta){
        
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        int opcionTransaccion;
        Scanner scanner = new Scanner(System.in);
        opcionTransaccion = scanner.nextInt();
        
        switch(opcionTransaccion){
            case 1:
                transaction(cuenta);
                break;
            case 2:
                System.out.println("Thanks for choosing us. Good Bye!");
                break;
            default:
                System.out.println("Invalid choice\n\n");
                anotherTransaction(cuenta);
                break;    
        }
    }
 
}
