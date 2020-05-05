/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import interfaces.ServidorChat;
import java.awt.PopupMenu;
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
    User user = new User();

    // static ArrayList<String> lerMensagem = new ArrayList<String>();
    public ViewChat() {
        initComponents();
        setLocationRelativeTo(null);
        try {
            jtaMural.append("Seja Bem-vindo ao chat! \n\n");
            chat = (ServidorChat) Naming.lookup("rmi://192.168.0.139:1098/ServidorChat");
            chat.lerMensagem();
            String usuario = JOptionPane.showInputDialog("Digite seu nome: ");
            user.setNome(usuario);
            chat.usuarios().add(usuario);

        } catch (Exception ex) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");

        jtaMural.setEditable(false);
        jtaMural.setColumns(20);
        jtaMural.setRows(5);
        jScrollPane1.setViewportView(jtaMural);
        jtaMural.getAccessibleContext().setAccessibleName("");

        jtaMensagem.setColumns(20);
        jtaMensagem.setRows(5);
        jtaMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtaMensagemKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtaMensagem);

        jbEnviar.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jbEnviar.setForeground(new java.awt.Color(51, 255, 0));
        jbEnviar.setText("Enviar");
        jbEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarActionPerformed(evt);
            }
        });

        list1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list1ActionPerformed(evt);
            }
        });
        scrollPane1.add(list1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jbEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addContainerGap())
        );

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

        Thread thread = new Thread(new Runnable() {

            int cont = chat.usuarios().size();

            @Override
            public void run() {
                while (true) {

                    try {
                        if (chat.usuarios().size() > cont) {

                            list1.add(chat.usuarios().get(chat.usuarios().size() -1));
                            cont++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ViewChat.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        thread.start();
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

        try {
            final ServidorChat chat = (ServidorChat) Naming.lookup("rmi://192.168.0.139:1098/ServidorChat");

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbEnviar;
    private javax.swing.JTextArea jtaMensagem;
    private static javax.swing.JTextArea jtaMural;
    private static java.awt.List list1;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
