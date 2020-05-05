/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfaces.ServidorChat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Cpd01
 */
public class ServidorImp extends UnicastRemoteObject implements ServidorChat {

    private ArrayList<String> mensagens;
    private ArrayList<String> usuarios;

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

}
