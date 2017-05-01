
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class Authentiction extends javax.swing.JFrame {

    private String user="",password="",cat="";
    private static String sUser="",sPass="",sCat="";

    public Authentiction() {
        initComponents();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    this.setVisible(true);
    setTitle("Log in.");
            jTextField1.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jPasswordField1.requestFocus();
                }
            }
        });
            
            
            jPasswordField1.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!(jPasswordField1.getPassword().equals(""))){
                                try{
            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;

            String qe = "select * from User_Management where User_ID=? and Password_User=?";

            psc=cn.prepareStatement(qe);
            psc.setString(1,jTextField1.getText());
            String pass = new String(jPasswordField1.getPassword());
            psc.setString(2,pass);
                
                rsc = psc.executeQuery();
                if(rsc.next())
                {
                    user = rsc.getString("User_ID");
                    password = rsc.getString("Password_User");
                    cat = rsc.getString("Category_User");
                }
                else {
                JOptionPane opt = new JOptionPane("Incorrect Username or password", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // no buttons
                final JDialog dlg = opt.createDialog("Error");
                    new Thread(new Runnable()
                          {
                            public void run()
                            {
                              try
                              {
                                Thread.sleep(1000);
                                dlg.dispose();
                              }
                              catch ( Throwable th )
                              {
                                //tracea("setValidComboIndex(): error :\n"
                                   // + cThrowable.getStackTrace(th));
                              }
                            }
                          }).start();
                    dlg.setVisible(true);
                    System.exit(0);
                    Authentiction ad = new Authentiction();
                }
                psc.close();
//                System.out.println("True Formatting "+user+"  "+password+"  "+cat);
                if(user.equals("") || password.equals("") || cat.equals("")){
//                System.out.println("False Formatting "+user+"  "+pass+"  "+cat);
                JOptionPane opt = new JOptionPane("Incorrect Username or password", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // no buttons
                    final JDialog dlg = opt.createDialog("Error");
                    new Thread(new Runnable()
                          {
                            public void run()
                            {
                              try
                              {
                                Thread.sleep(1000);
                                dlg.dispose();

                              }
                              catch ( Throwable th )
                              {
                                //tracea("setValidComboIndex(): error :\n"
                                   // + cThrowable.getStackTrace(th));
                              }
                            }
                          }).start();
                    dlg.setVisible(true);
                System.exit(0);
                    Authentiction an = new Authentiction();
                }
                else{
//                    System.out.println("Data Validated");
                    sUser=user;
                    sPass=password;
                    sCat=cat;
//                    System.out.println("In Auth "+sUser+"    "+sCat+"   "+sPass);
                    dispose();
                    Main1 m = new Main1();
                    m.setVisible(true);
                }
    }catch(SQLException df){
        df.printStackTrace();
    }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Password :");

        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Cancel");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Username :");

        jButton2.setForeground(new java.awt.Color(0, 51, 51));
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(7, 7, 7)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;

            String qe = "select * from User_Management where User_ID=? and Password_User=?";

            psc=cn.prepareStatement(qe);
            psc.setString(1,jTextField1.getText());
            String pass = new String(jPasswordField1.getPassword());
            psc.setString(2,pass);
                
                rsc = psc.executeQuery();
                if(rsc.next())
                {
                    user = rsc.getString("User_ID");
                    password = rsc.getString("Password_User");
                    cat = rsc.getString("Category_User");
                }
                else {
                JOptionPane opt = new JOptionPane("Incorrect Username or password", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // no buttons
                final JDialog dlg = opt.createDialog("Error");
                    new Thread(new Runnable()
                          {
                            public void run()
                            {
                              try
                              {
                                Thread.sleep(1000);
                                dlg.dispose();
                              }
                              catch ( Throwable th )
                              {
                                //tracea("setValidComboIndex(): error :\n"
                                   // + cThrowable.getStackTrace(th));
                              }
                            }
                          }).start();
                    dlg.setVisible(true);
                    System.exit(0);
                    Authentiction ad = new Authentiction();
                }
                psc.close();
//                System.out.println("True Formatting "+user+"  "+password+"  "+cat);
                if(user.equals("") || password.equals("") || cat.equals("")){
//                System.out.println("False Formatting "+user+"  "+pass+"  "+cat);
                JOptionPane opt = new JOptionPane("Incorrect Username or password", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // no buttons
                    final JDialog dlg = opt.createDialog("Error");
                    new Thread(new Runnable()
                          {
                            public void run()
                            {
                              try
                              {
                                Thread.sleep(1000);
                                dlg.dispose();

                              }
                              catch ( Throwable th )
                              {
                                //tracea("setValidComboIndex(): error :\n"
                                   // + cThrowable.getStackTrace(th));
                              }
                            }
                          }).start();
                    dlg.setVisible(true);
                System.exit(0);
                    Authentiction an = new Authentiction();
                }
                else{
                    System.out.println("Data Validated");
                    sUser=user;
                    sPass=password;
                    sCat=cat;
                    System.out.println("In Auth "+sUser+"    "+sCat+"   "+sPass);
                    this.dispose();
                    Main1 m = new Main1();
                    m.setVisible(true);
                }
    }catch(SQLException df){
        df.printStackTrace();
    }
    }//GEN-LAST:event_jButton2ActionPerformed
            public Authentiction(String a){
                
            }
            public String retUser(){
                return sUser;
            }
            public String retPass(){
                return sPass;
            }
            public String retCat(){
                return sCat;
            }

            
            /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Authentiction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentiction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentiction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentiction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Authentiction();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
