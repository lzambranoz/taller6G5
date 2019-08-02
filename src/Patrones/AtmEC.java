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

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDinero() {
        return dinero;
    }
    
    public boolean sacarDinero(double monto) { //asumir que siempre se deposita de la misma demonicacion
       if(dinero!=0 && manejador.retirar(monto)){ //false cuando no tiene dinero en la cuenta o no hay posibilidad de retirar del cajero
           dinero -= monto;
           return true;
       }
       return false;
    }
    

    public boolean ingresarDinero(int cant, double denominacion) {
        if (manejador.depositar(cant, denominacion)){
            dinero += (cant * denominacion);
            return true;
        }
        return false;
    }
    
    public void addManejador(Manejador m){
        this.getInstance().manejador = addManejador(m, this.manejador,null);
    }
    
    private Manejador addManejador(Manejador m, Manejador mainManejador, Manejador previo){
        if(mainManejador == null){
            mainManejador = m;
            dinero += m.getCantidad()*m.getDenominacion();
        }else if (m.getDenominacion()== mainManejador.getDenominacion()){
            mainManejador.setCantidad(m.getCantidad()+mainManejador.getCantidad());
            dinero+=m.getCantidad() * m.getDenominacion();
        }else if (m.getDenominacion()>mainManejador.getDenominacion()){
            m.setNext(mainManejador);
            if (previo != null)
                previo.setNext(m);
            return m;
        }else 
            mainManejador.setNext(addManejador(m, mainManejador.getNext(),mainManejador));
        return mainManejador;
    }

    public void setManejador(Manejador manejador) {
        this.manejador = manejador;
    }
    
    public Manejador removeManejador(double i){
        Manejador saliente = new ManejadorDinero(0,0);
        this.manejador = removeManejador(saliente, i,this.manejador,null);
        return saliente;
    }
    private Manejador removeManejador(Manejador m, double den,Manejador mainManejador, Manejador previo){
        if(mainManejador == null)
            m = mainManejador;
        else if (den == mainManejador.getDenominacion()){
            m = new ManejadorDinero(mainManejador.getCantidad(), mainManejador.getDenominacion());
            dinero -= m.getCantidad()*m.getDenominacion();
            if (previo != null){
                previo.setNext(mainManejador.getNext());
                mainManejador = previo;
            }
        }else if (den < mainManejador.getDenominacion())
            mainManejador = removeManejador(m, den, mainManejador.getNext(), mainManejador);
        return mainManejador;
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
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    if(sacarDinero(amount)){
                        cuenta.Retirar(amount);
                        System.out.println("Your transaction was completed, take your money.\n\n");
                    }
                    anotherTransaction(cuenta);
                }
            break; 
            case 2:
                // option number 2 is depositing - se supone que el ingreso de dinero es de una misma denominacion
                double deposit;
                int cant;
                System.out.println("Please enter how many banknotes you would wish to deposit: ");
                cant = scanner.nextInt();
                System.out.println("Please enter denomination you would wish to deposit: "); 
                deposit = scanner.nextDouble();
                // Todo: actualizar tanto la cuenta como el atm
                // Todo: Mostrar resumen de transacción o error
                // "You have withdrawn "+amount+" and your new balance is "+balance;
                this.manejador.depositar(cant,deposit);//actualizacion de los manejadores
                this.setDinero(this.getDinero()+(deposit*cant)); //actualizacion atm
                cuenta.Depositar(deposit * cant);
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
                System.out.println("ATM balance is: "+this.getInstance().getDinero());
                Manejador manejMostrado = this.getInstance().manejador;
                do{
                    System.out.printf("%d banknotes in $%f denomination\n",manejMostrado.getCantidad(),manejMostrado.getDenominacion());
                }while((manejMostrado = manejMostrado.getNext())!= null);
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
