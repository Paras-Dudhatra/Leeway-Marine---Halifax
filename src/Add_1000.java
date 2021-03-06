
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class Add_1000 extends javax.swing.JFrame {

    /**
     * Creates new form Add_1000
     */
    public Add_1000() {
        initComponents();

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    
    this.checkoutValidate("new1123","A","1125");

    }
    
    public boolean checkoutValidate(String serial_arg,String group_arg,String hour_arg){
                
        try{
        Connect ca = new Connect();
    
        Connection cn = ca.getConnection();
        PreparedStatement psc = null;
        ResultSet rsc = null;
        Statement s = null,sc=null;
        
            String qe = "select Hours_Track,Ins_Group,Status,Technician_Name from Maintenance_Tracking where  Serial_Num = ? ORDER BY Hours_Track,Ins_Group";
            psc=cn.prepareStatement(qe);
            psc.setString(1,"new1123");
                rsc = psc.executeQuery();
                int count = 0;
                while(rsc.next()){
                    count++;
                }
                String[] hr = new String[count];
                String[] status = new String[count];
                String[] grup = new String[count];
                psc.close();
            psc=cn.prepareStatement(qe);
            psc.setString(1,serial_arg);
                rsc = psc.executeQuery();
                count = 0;
                while(rsc.next()){
                hr[count] = rsc.getString("Hours_Track");
                status[count] = rsc.getString("Status");
                grup[count] = rsc.getString("Ins_Group");
                count++;
                }
                System.out.println("Array Length "+hr.length);
                for(int i=0;i<hr.length;i++){
                    if(hr[i].equals(hour_arg) && grup[i].equals(group_arg)){
                        
                        if(i == 0){
//                            System.out.println("Index is "+i);
  //                          System.out.println("Index 0 engine is validated for checkout.");-
                            return true;
                        }
                        else{
                        System.out.println("At index "+i);
                                if(status[i-1].equals("Incomplete") ){
                                    //System.out.println("Data not Validated");
                                    return false;

                                }
                                else if(status[i-1].equals("Completed")) {
                                    //System.out.println("Non Index 0 engine validated for checkout.");
                                    return true;
                                }
                        }
                    }
                }

        }catch(SQLException sd){
            sd.printStackTrace();
        }
        return false;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Add_1000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_1000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_1000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_1000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_1000().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
