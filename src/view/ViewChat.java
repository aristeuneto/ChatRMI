/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import interfaces.ServidorChat;
import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidor.User;

/**
 *
 * @author Cpd01
 */
public class ViewChat extends javax.swing.JFrame {

    /**
     * Creates new form ViewChat
     */
    static ServidorChat chat;
    int cont;
    static User user = new User();
    static String usuario;

    public ViewChat() {
        initComponents();
        setLocationRelativeTo(null);
        try {
            jtaMural.append("Seja Bem-vindo ao chat! \n\n");
            chat = (ServidorChat) Naming.lookup("rmi://"+chat.ipServidor()+":1098/ServidorChat");
            chat.lerMensagem();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMural = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaMensagem = new javax.swing.JTextArea();
        jbEnviar = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();
        list1 = new java.awt.List();
        jLabel1 = new javax.swing.JLabel();
        jlbUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtaMural.setEditable(false);
        jtaMural.setColumns(20);
        jtaMural.setRows(5);
        jScrollPane1.setViewportView(jtaMural);
        jtaMural.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 31, 325, 260));

        jtaMensagem.setColumns(20);
        jtaMensagem.setRows(5);
        jtaMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtaMensagemKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtaMensagem);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 301, 330, 83));

        jbEnviar.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jbEnviar.setForeground(new java.awt.Color(51, 255, 0));
        jbEnviar.setText("Enviar");
        jbEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(jbEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 301, -1, 83));

        list1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list1ActionPerformed(evt);
            }
        });
        scrollPane1.add(list1);

        getContentPane().add(scrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 27, 98, 264));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 0));
        jLabel1.setText("Usuários Online");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlbUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbUsuario.setForeground(new java.awt.Color(0, 102, 255));
        jlbUsuario.setText("Usuário");
        getContentPane().add(jlbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarActionPerformed
        try {
            chat.enviarMensagem(user.getNome() + " diz: " + jtaMensagem.getText());
        } catch (RemoteException ex) {
            Logger.getLogger(ViewChat.class.getName()).log(Level.SEVERE, null, ex);
        }

        jtaMensagem.setText("");

    }//GEN-LAST:event_jbEnviarActionPerformed

    private void jtaMensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaMensagemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                chat.enviarMensagem(user.getNome() + " diz: " + jtaMensagem.getText());
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }

            jtaMensagem.setText("");

        }
    }//GEN-LAST:event_jtaMensagemKeyPressed
    public static void lerUsuarios() throws RemoteException {

        final ServidorChat chat;
        try {
            chat = (ServidorChat) Naming.lookup("rmi://"+chat.ipServidor()+":1098/ServidorChat");

            usuario = JOptionPane.showInputDialog("Digite seu nome: ");
            user.setNome(usuario);
            chat.setUsuario(usuario);//1
            jlbUsuario.setText("Usuário: " + usuario);
           // JOptionPane.showMessageDialog(null, chat.getUsuarios().size());
            Thread thread = new Thread(new Runnable() {

                int cont = chat.getUsuarios().size();
                int contLocal = 0;

                @Override
                public void run() {
                    while (true) {

                        if (contLocal < cont) {

                            try {

                                list1.add(chat.getUsuarios().get(contLocal));
                                contLocal++;
                            } catch (Exception ex) {
                                Logger.getLogger(ViewChat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }
            });
            thread.start();
        } catch (Exception ex) {
            Logger.getLogger(ViewChat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void lerMensagens() {

        try {
            final ServidorChat chat = (ServidorChat) Naming.lookup("rmi://192.168.0.112:1098/ServidorChat");

            Thread thread = new Thread(new Runnable() {

                int cont = chat.lerMensagem().size();//2

                @Override
                public void run() {
                    try {

                        while (true) {
                            if (chat.lerMensagem().size() > cont) {
                                jtaMural.append(chat.lerMensagem().get(chat.lerMensagem().size() - 1) + "\n");
                                cont++;
                            }
                        }
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }

                }
            }
            );
            thread.start();
            lerUsuarios();
        } catch (Exception ex) {
            ex.printStackTrace();;
        }

    }


    private void list1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_list1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewChat().setVisible(true);
            }
        });

        lerMensagens();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbEnviar;
    private static javax.swing.JLabel jlbUsuario;
    private javax.swing.JTextArea jtaMensagem;
    private static javax.swing.JTextArea jtaMural;
    private static java.awt.List list1;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
