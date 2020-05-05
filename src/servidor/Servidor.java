/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfaces.ServidorChat;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Cpd01
 */
public class Servidor {

    private String ipServidor = "192.168.0.112";
    
    public Servidor() {
        
        try {
            Registry registry = LocateRegistry.createRegistry(1098);
            ServidorChat server = new ServidorImp();
            Naming.rebind("rmi://"+ipServidor+":1098/ServidorChat", server);

        } catch (Exception ex) {
            ex.printStackTrace();;

        }
    }

    public static void main(String[] args) {
        new Servidor();
        System.out.println("Servidor iniciado!");
    }

}
