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
    static ArrayList<CuentaAdapter> CuentasUS = new ArrayList<>();
    public static void main(String[] args)
    {
        IniciarlizarCuentas();
        Scanner sc = new Scanner(System.in);
        
        //Lista de cuentas con local.us
        
        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        
       
        String op="0";
        do{
        System.out.println("--- Menu Princiapl ---");
        System.out.println("1. Seleccionar cuentas");
        System.out.println("2. Realizar Transacciones");
        System.out.println("3. Salir");
        System.out.println("Escoga una opcion");
        op = sc.next();
        int id=0;
        switch(op){
            case "1":
                    System.out.println("Ingrese el id de la cuenta a solicitar");
                    id = sc.nextInt();
                    System.out.println("Esta es la cuenta de banco con ID: "+Cuenta(id).getId());
                    
                     break;
            case "2":
                    //Aqui debe llamarse al metodo transaction(Cuenta(id))  de la clase AtmEC 
                    break;
                    
                     
        }
          
        
        }while(!(op.equals("3")));
        }
        
   
       
        
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05

        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático
    
    
     static void IniciarlizarCuentas(){ 
         
         for(int i=1; i<=10;i++){
         Random rd = new Random();   
            int numeroAleatorio = rd.nextInt(900)+100;
            CuentaAdapter cuenta = new CuentaAdapter(i,numeroAleatorio);
            CuentasUS.add(cuenta);
        }
         
     }
    
    static CuentaAdapter Cuenta(int id){
        for(CuentaAdapter cuenta: CuentasUS){
            if(cuenta.getId()==id){
                return cuenta;
            }
        }
        return null;
    } 

    
}
