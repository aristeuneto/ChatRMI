/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfaces.ServidorChat;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cpd01
 */
public class ServidorImp extends UnicastRemoteObject implements ServidorChat {

    private ArrayList<String> mensagens;
    private ArrayList<String> usuarios;
    private String ipServidor;

    public ServidorImp() throws RemoteException {
        super();
        mensagens = new ArrayList<String>();
        usuarios = new ArrayList<String>();
    }

    @Override
    public void enviarMensagem(String mensagem) throws RemoteException {

        mensagens.add(mensagem);

    }

    @Override
    public ArrayList<String> lerMensagem() throws RemoteException {
        return mensagens;

    }

    @Override
    public ArrayList<String> getUsuarios() throws RemoteException {
        return usuarios;
    }

    @Override
    public void setUsuario(String usuario) throws RemoteException {
        usuarios.add(usuario);

    }

    @Override
    public String ipServidor() throws RemoteException {
        try {
            ipServidor = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return ipServidor;
    }

}
