
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */


public class M_20000_BladeConnectingRod extends javax.swing.JFrame {

    /**
     * Creates new form M_20000_BladeConnectingRod
     */
    Object[] headerNames = null;
    Object ob[][] = null;
    JTable jTable1;
    JPanel p1,p2;
    JTextField t1,t2,t3,t4,t5,t6,mtu,remarks;
    
    public M_20000_BladeConnectingRod(String serial_no) {
        //initComponents();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-400,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //setLayout(null);
        setLayout(new BorderLayout());
        setTitle("Blade Connecting Rods");

        headerNames = new Object[]{"Cyl No","Serial No","Position","Big-end A","Big-end B","Big-end C","Ovality","Small-end A","Small-end B"};
        ob = new Object[16][9];

/*        JPanel p3;
        p3 = new JPanel(new BorderLayout());
        p3.setSize(300,100);
        this.add(p3,"East");
        JTable tb = new JTable();
        JScrollPane pane1 = new JScrollPane(tb);
        p3.add(pane1,BorderLayout.CENTER);
        //ImageIcon image = new ImageIcon("D:\\Backup Data\\PHOTOS\\Hostel\\Photo0885.jpg");
        //JLabel label = new JLabel("", image, JLabel.CENTER);
        //label.setSize(300,100);
        //p3.add( label, BorderLayout.CENTER );
        //p3.add(new ImagePanel());
*/
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                dispose();

            }
        });
        
        p1 = new JPanel();
        this.add(p1,"North");
        p1.setLayout(new GridLayout(4,2));
        
        p1.add(new JLabel("Serial Number:-"));
        t1 = new JTextField();
        t1.setEditable(false);
        p1.add(t1);

        p1.add(new JLabel("Installation:-"));
        t2 = new JTextField();
        p1.add(t2);

        p1.add(new JLabel("Engine Type:-"));
        t3 = new JTextField();
        p1.add(t3);

        p1.add(new JLabel("Measured By:-"));
        t4 = new JTextField();
        p1.add(t4);

        p1.add(new JLabel("Designation:-"));
        t5 = new JTextField();
        p1.add(t5);

        p1.add(new JLabel("Date:-"));
        t6 = new JTextField();
        p1.add(t6);

        p2 = new JPanel();
        this.add(p2,"South");
        p2.setLayout(new GridLayout(3,1));

        p2.add(new JLabel("MTU :-"));
        mtu = new JTextField();
        p2.add(mtu);

        p2.add(new JLabel("Remarks :-"));
        remarks = new JTextField();
        p2.add(remarks);
        JButton b1 = new JButton("Save");
        p2.add(b1);
        
        JButton b2 = new JButton("OPEN PARTS IMAGE");
        p1.add(b2);
        
                    b2.addActionListener(new ActionListener()
                         {
                         public void actionPerformed(ActionEvent ae)
                             {
                                                      try {
                        if (Desktop.isDesktopSupported()) {
                            String path = "ProgramData\\Icons\\1_Blade_Connecting_Rods.png";
                          Desktop.getDesktop().open(new File(path));
                        }
                      } catch (IOException ioe) {
                        ioe.printStackTrace();
                     }
                             }
                          });
        
        try{
            Connect c = new Connect();
            Connection cn = c.getConnectionL1();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;

            String qe1 = "select * from M_20k_Bld_Cnctng_Rds where Serial_Num = ?";
            psc=cn.prepareStatement(qe1);
            psc.setString(1,serial_no);
            rsc = psc.executeQuery();
            //sc=cn.createStatement();
            //rsc = sc.executeQuery(qe1);
            int cnt = 0;
            while(rsc.next())
            {
                ob[cnt][0] = rsc.getString("Cyl_Num");
                ob[cnt][1] = rsc.getString("Serial_No");
                ob[cnt][2] = rsc.getString("Position");
                ob[cnt][3] = rsc.getString("Big_A");
                ob[cnt][4] = rsc.getString("Big_B");
                ob[cnt][5] = rsc.getString("Big_C");
                ob[cnt][6] = rsc.getString("Big_Ovality");
                ob[cnt][7] = rsc.getString("Small_A");
                ob[cnt][8] = rsc.getString("Small_B");
                cnt++;
            }
        psc.close();

                    jTable1 = new JTable(ob,headerNames){
			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return colIndex == 1 ||colIndex == 3 || colIndex == 4 || colIndex == 5 || colIndex == 6 || colIndex == 7 || colIndex == 8;
				}
                            };
        //panel.add(new JButton("Center"),BorderLayout.CENTER);
            JScrollPane jscr = new JScrollPane(jTable1);
            this.add(jscr,"Center");
            //jscr.setBounds(90,100,800,500);
            jTable1.setRowHeight(30);
	    
            String qe2 = "select * from M_20k_Bld_Cnctng_Rds_1 where Serial_Num = ?";
            psc=cn.prepareStatement(qe2);
            psc.setString(1,serial_no);
            rsc = psc.executeQuery();
            while(rsc.next()){
                t1.setText(rsc.getString("Serial_Num"));
                t2.setText(rsc.getString("Installation"));
                t3.setText(rsc.getString("En_Type"));
                t4.setText(rsc.getString("Measure_By"));
                t5.setText(rsc.getString("Designation"));
                t6.setText(rsc.getString("Date_P"));
                mtu.setText(rsc.getString("MTU"));
                remarks.setText(rsc.getString("Remarks"));
            }
        }catch(SQLException sd){
            sd.printStackTrace();
        }


        b1.addActionListener(new ActionListener(){
         
            public void actionPerformed(ActionEvent ae){
            
            //jTable1.setRowSelectionInterval(jTable1.getSelectedRow(),jTable1.getSelectedRow());
            if (jTable1.isEditing())
                jTable1.getCellEditor().stopCellEditing();
            try{
            Connect c = new Connect();
            Connection cn = c.getConnectionL1();
            PreparedStatement ps = null;
            Object ob1,ob2,ob3,ob4,ob5,ob6,ob7,ob8,ob9;
  //          System.out.println("Button pressed");
   //         System.out.println("Count is "+jTable1.getRowCount());
            for(int i=0;i<jTable1.getRowCount();i++){

                //"Cyl No","Serial No","Position","A","B","C","Ovality","A","B"

                ob1 = jTable1.getValueAt(i,1);
                ob2 = jTable1.getValueAt(i,3);
                ob3 = jTable1.getValueAt(i,4);
                ob4 = jTable1.getValueAt(i,5);
                ob5 = jTable1.getValueAt(i,6);
                ob6 = jTable1.getValueAt(i,7);
                ob7 = jTable1.getValueAt(i,8);
                ob8 = jTable1.getValueAt(i,0);
                ob9 = jTable1.getValueAt(i,2);

                String s1 = ob1.toString();
                String s2 = ob2.toString();
                String s3 = ob3.toString();
                String s4 = ob4.toString();
                String s5 = ob5.toString();
                String s6 = ob6.toString();
                String s7 = ob7.toString();
                String s8 = ob8.toString();
                String s9 = ob9.toString();

     //           System.out.println(s1+"  "+s2+"   "+s3+"   "+s4);
            String Qrys = "update M_20k_Bld_Cnctng_Rds set Serial_No = ?,Big_A = ?,Big_B = ?,Big_C = ?,Big_Ovality = ?,Small_A = ?,Small_B = ? where  Cyl_Num = ? and Position = ?  and Serial_Num = ?";
            ps = cn.prepareStatement(Qrys);
            ps.setString(1,s1);
            ps.setString(2,s2);
            ps.setString(3,s3);
            ps.setString(4,s4);
            ps.setString(5,s5);
            ps.setString(6,s6);
            ps.setString(7,s7);
            ps.setString(8,s8);
            ps.setString(9,s9);
            ps.setString(10,serial_no);
            ps.executeUpdate();
            ps.close();
            }
//System.out.println("Update-1 success !");
            String Qrys1 = "update M_20k_Bld_Cnctng_Rds_1 set En_Type = ?,Measure_By = ?,Designation = ?,Installation = ?,Date_P = ?,MTU=?,Remarks=? where  Serial_Num=?";
            ps = cn.prepareStatement(Qrys1);
            ps.setString(1,t3.getText());
            ps.setString(2,t4.getText());
            ps.setString(3,t5.getText());
            ps.setString(4,t2.getText());
            ps.setString(5,t6.getText());
            ps.setString(6,mtu.getText());
            ps.setString(7,remarks.getText());
            ps.setString(8,t1.getText());
            ps.executeUpdate();
            ps.close();
//            System.out.println("Update-2 success !");
            
            }catch(SQLException sd){
                sd.printStackTrace();
            }
            }
        });
        //b1.setBounds(10,10,60,20);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            java.util.logging.Logger.getLogger(M_20000_BladeConnectingRod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(M_20000_BladeConnectingRod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(M_20000_BladeConnectingRod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(M_20000_BladeConnectingRod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new M_20000_BladeConnectingRod("new1123");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}