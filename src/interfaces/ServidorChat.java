/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Cpd01
 */
public interface ServidorChat extends Remote {

    public void enviarMensagem(String mensagem) throws RemoteException;

    public ArrayList<String> lerMensagem() throws RemoteException;

    public ArrayList<String> getUsuarios() throws RemoteException;
    
    public void setUsuario(String usuario) throws RemoteException;
    
    public String ipServidor()throws RemoteException;
}
