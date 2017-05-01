
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.TableColumn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class M_20000_Fork_Connecting_Rod_Form extends javax.swing.JFrame {

    /**
     * Creates new form M_20000_BladeConnectingRod
     */
    Object[] headerNames = null;
    Object ob[][] = null;
    JTable jTable1;
    JPanel p1,p2;
    JTextField t1,t2,t3,t4,t5,t6,mtu,remarks;
    
    public M_20000_Fork_Connecting_Rod_Form(String serial_no) {
        initComponents();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-400,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //setLayout(null);
        setLayout(new BorderLayout());
        setTitle("Fork Connecting Rods");

        headerNames = new Object[]{"Rod ","Serial No","Location","  A","   B","   C","Ovality"};
        ob = new Object[48][7];
        
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
                            String path = "ProgramData\\Icons\\4_Fork_Connecting_Rod_Form.png";
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

            String qe1 = "select * from M_20k_Fork_Connecting_Rod where Serial_Eng = ?";
            psc=cn.prepareStatement(qe1);
            psc.setString(1,serial_no);
            rsc = psc.executeQuery();
            //sc=cn.createStatement();
            //rsc = sc.executeQuery(qe1);
            int cnt = 0;
            while(rsc.next())
            {
                ob[cnt][0] = rsc.getString("Connecting_Rod");
                ob[cnt][1] = rsc.getString("Serial_Num");
                ob[cnt][2] = rsc.getString("Location");
                ob[cnt][3] = rsc.getString("A");
                ob[cnt][4] = rsc.getString("B");
                ob[cnt][5] = rsc.getString("C");
                ob[cnt][6] = rsc.getString("Ovality");
                cnt++;
                //{"Rod ","Serial No","Location","  A","   B","   C","Ovality"};
            }
        psc.close();

                    jTable1 = new JTable(ob,headerNames){
			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return colIndex == 1 ||colIndex == 3 || colIndex == 4 || colIndex == 5 || colIndex == 6;
				}
                            };
        //panel.add(new JButton("Center"),BorderLayout.CENTER);
            JScrollPane jscr = new JScrollPane(jTable1);
            this.add(jscr,"Center");
            //jscr.setBounds(90,100,800,500);
            jTable1.setRowHeight(30);
	    
            String qe2 = "select * from M_20k_Fork_Connecting_Rod_1 where Serial_Num_F = ?";
            psc=cn.prepareStatement(qe2);
            psc.setString(1,serial_no);
            rsc = psc.executeQuery();
            while(rsc.next()){
                t1.setText(rsc.getString("Serial_Num_F"));
                t2.setText(rsc.getString("Installation_F"));
                t3.setText(rsc.getString("En_Type_F"));
                t4.setText(rsc.getString("Measure_By_F"));
                t5.setText(rsc.getString("Designation_F"));
                t6.setText(rsc.getString("Date_P_F"));
                mtu.setText(rsc.getString("MTU_F"));
                remarks.setText(rsc.getString("Remarks_F"));
            }
        }catch(SQLException sd){
            sd.printStackTrace();
        }

            TableColumn column = null;
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);


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
                ob8 = jTable1.getValueAt(i,0);
                ob9 = jTable1.getValueAt(i,2);

                String s1 = ob1.toString();
                String s2 = ob2.toString();
                String s3 = ob3.toString();
                String s4 = ob4.toString();
                String s5 = ob5.toString();
                String s8 = ob8.toString();
                String s9 = ob9.toString();

     //           System.out.println(s1+"  "+s2+"   "+s3+"   "+s4);
            String Qrys = "update M_20k_Fork_Connecting_Rod set Serial_Num = ?,A = ?,B = ?,C = ?,Ovality = ? where  Connecting_Rod = ? and Location = ?  and Serial_Eng = ?";
            ps = cn.prepareStatement(Qrys);
            ps.setString(1,s1);
            ps.setString(2,s2);
            ps.setString(3,s3);
            ps.setString(4,s4);
            ps.setString(5,s5);
            ps.setString(6,s8);
            ps.setString(7,s9);
            ps.setString(8,serial_no);
            ps.executeUpdate();
            ps.close();
            }
//System.out.println("Update-1 success !");
            String Qrys1 = "update M_20k_Fork_Connecting_Rod_1 set En_Type_F = ?,Measure_By_F = ?,Designation_F = ?,Installation_F = ?,Date_P_F = ?,MTU_F=?,Remarks_F=? where  Serial_Num_F=?";
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
//          System.out.println("Update-2 success !");
            
            }catch(SQLException sd){
                sd.printStackTrace();
            }
            }
        });
        //b1.setBounds(10,10,60,20);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
    }// </editor-fold>                        

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
                new M_20000_Fork_Connecting_Rod_Form("new1123");
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
