/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        ArrayList<CuentaAdapter> CuentasUS= new ArrayList<>();//Lista de cuentas con local.us
        AtmEC atm = AtmEC.getInstance();
        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        
        System.out.println("ID de cuentas creadas");
        for(int i=1; i<=10;i++){
            int numeroAleatorio = rd.nextInt(900)+100;
            int numeroAleatorioCuenta = rd.nextInt(10);
            String Acc = String.valueOf(i).concat(String.valueOf(numeroAleatorioCuenta));
            CuentaAdapter cuenta = new CuentaAdapter(Integer.valueOf(Acc),numeroAleatorio);
            CuentasUS.add(cuenta);
            //la impresion de cuentas solo se realiza por efectos del ejercicio para saber a cual acceder
            System.out.println(cuenta.getId());
        }
        
        System.out.println("===================");
        CuentaAdapter cuentaBuscada = null;
        while(cuentaBuscada == null){ //el cajero esta siempre encendido por ello no hay mensaje de salida salvo despues de una transaccion
            System.out.println("Welcome");
            System.out.println("Input your Account ID: ");
            int id = sc.nextInt();
            cuentaBuscada = CuentasUS.stream().filter((c)->c.getId() == id).findAny().orElse(null);
            if (cuentaBuscada == null)
                System.out.println("Non-existent account!");
        }
        atm.transaction(cuentaBuscada);
        
//        int op=0;
//        do{
//        System.out.println("--- Menu Princiapl ---");
//        System.out.println("1. Seleccionar cuentas");
//        System.out.println("2. Realizar Transacciones");
//        System.out.println("3. Salir");
//        System.out.println("Escoga una opcion");
//        op = sc.nextInt();
//        switch(op){
//            case 1:
//                    System.out.println("Ingrese el id de la cuenta a solicitar");
//                    int id = sc.nextInt();
//                     Iterator it = CuentasUS.iterator();
//                     while(it.hasNext()){
//                        CuentaAdapter c = (CuentaAdapter) it.next();
//                        if(c.getId()==id){
//                            System.out.println("cuenta"+c.getId());
//                        }
//                         
//                     }
//                     break;
//            case 2:
//                    System.out.println("");
//                    
//                     
//        }
//          
//        
//        }while(op!=3);
//        
        
        atm.addManejador((Manejador)(new ManejadorDinero(100, 20)));
        atm.addManejador((Manejador)(new ManejadorDinero(100, 10)));
        atm.addManejador((Manejador)(new ManejadorDinero(10, 0.50)));
        atm.addManejador((Manejador)(new ManejadorDinero(10, 0.25)));
        atm.addManejador((Manejador)(new ManejadorDinero(1000, 0.05)));
        
        
        //prueba de add y remove en el manejador
        System.out.println(atm.getDinero());
        atm.removeManejador(20);
        System.out.println(atm.getDinero());
        atm.addManejador((Manejador)(new ManejadorDinero(100, 10)));
        System.out.println(atm.getDinero());
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05

        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático
    }

    
}
