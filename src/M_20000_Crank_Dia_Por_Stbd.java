
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class M_20000_Crank_Dia_Por_Stbd extends javax.swing.JFrame {
    Object[] headerNames = null;
    Object ob[][] = null;
    JTable jTable1;
    JPanel p1,p2;
    private JTextField mtu,remarks;
    
    
    
    public M_20000_Crank_Dia_Por_Stbd(String ser_no) {
        initComponents();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-400,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //setLayout(null);
        setLayout(new BorderLayout());
        setTitle("Crankpin Diameter POR and STBD.      Serial-Number - "+ser_no);

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
        
        p1 = new JPanel();
        this.add(p1,"North");
        p1.setLayout(new BorderLayout());
        JButton jb = new JButton("OPEN PARTS IMAGE");
        p1.add(jb);
                    jb.addActionListener(new ActionListener()
                         {
                         public void actionPerformed(ActionEvent ae)
                             {
                                                      try {
                        if (Desktop.isDesktopSupported()) {
                            String path = "ProgramData\\Icons\\2_Crankpin_Diameter_POR_and_STBD.png";
                          Desktop.getDesktop().open(new File(path));
                        }
                      } catch (IOException ioe) {
                        ioe.printStackTrace();
                     }
                             }
                          });

        headerNames = new Object[]{"Journal No.","Port_Eng_1","Port_Eng_2","Port_Eng_3","Stbd_Eng_1","Stbd_Eng_2","Stbd_Eng_3"};
        ob = new Object[16][7];

        try{
            Connect c = new Connect();
            Connection cn = c.getConnectionL1();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;
            String qe1 = "select * from M_20k_Crank_Dia_Por_Stbd where Serial_Num = ?";
            psc=cn.prepareStatement(qe1);
            psc.setString(1,ser_no);
            rsc = psc.executeQuery();
            //sc=cn.createStatement();
            //rsc = sc.executeQuery(qe1);
            int cnt = 0;
            while(rsc.next())
            {
                ob[cnt][0] = rsc.getString("Journal_Num");
                ob[cnt][1] = rsc.getString("Port_Eng_1");
                ob[cnt][2] = rsc.getString("Port_Eng_2");
                ob[cnt][3] = rsc.getString("Port_Eng_3");
                ob[cnt][4] = rsc.getString("Stbd_Eng_1");
                ob[cnt][5] = rsc.getString("Stbd_Eng_2");
                ob[cnt][6] = rsc.getString("Stbd_Eng_3");
                cnt++;
            }
        psc.close();

                    jTable1 = new JTable(ob,headerNames){
			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return colIndex == 1 || colIndex == 2 || colIndex == 3 || colIndex == 4 || colIndex == 5 || colIndex == 6;
				}
                            };
        //panel.add(new JButton("Center"),BorderLayout.CENTER);
            JScrollPane jscr = new JScrollPane(jTable1);
            this.add(jscr,"Center");
            //jscr.setBounds(90,100,800,500);
            jTable1.setRowHeight(30);
	    
            String qe2 = "select * from M_20k_Crank_Dia_Por_Stbd_1 where Serial_Num = ?";
            psc=cn.prepareStatement(qe2);
            psc.setString(1,ser_no);
            rsc = psc.executeQuery();
            while(rsc.next()){
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
            
  //          System.out.println("Button pressed");
   //         System.out.println("Count is "+jTable1.getRowCount());
            for(int i=0;i<jTable1.getRowCount();i++){

                //"Cyl No","Serial No","Position","A","B","C","Ovality","A","B"

                Object p1 = jTable1.getValueAt(i,1);
                Object p2= jTable1.getValueAt(i,2);
                Object p3 = jTable1.getValueAt(i,3);
                Object s1 = jTable1.getValueAt(i,4);
                Object s2 = jTable1.getValueAt(i,5);
                Object s3= jTable1.getValueAt(i,6);
                Object jr_num = jTable1.getValueAt(i,0);

                String st1 = p1.toString();
                String st2 = p2.toString();
                String st3 = p3.toString();
                String st4 = s1.toString();
                String st5 = s2.toString();
                String st6 = s3.toString();
                String jrn_str = jr_num.toString();
     //           System.out.println(s1+"  "+s2+"   "+s3+"   "+s4);
            String Qrys = "update M_20k_Crank_Dia_Por_Stbd set Port_Eng_1 = ?,Port_Eng_2 = ?,Port_Eng_3 = ?,Stbd_Eng_1 = ?,Stbd_Eng_2 = ?,Stbd_Eng_3 = ? where  Journal_Num = ? and Serial_Num = ?";
            ps = cn.prepareStatement(Qrys);
            ps.setString(1, st1);
            ps.setString(2, st2);
            ps.setString(3, st3);
            ps.setString(4, st4);
            ps.setString(5, st5);
            ps.setString(6, st6);
            ps.setString(7, jrn_str);
            ps.setString(8,ser_no);
            ps.executeUpdate();
            ps.close();
            }
System.out.println("Update-1 success !");
            String Qrys1 = "update M_20k_Crank_Dia_Por_Stbd_1 set MTU=?,Remarks=? where  Serial_Num=?";
            ps = cn.prepareStatement(Qrys1);
            ps.setString(1, mtu.getText());
            ps.setString(2, remarks.getText());
            ps.setString(3, ser_no);
            ps.executeUpdate();
            ps.close();
            System.out.println("Update-2 success !");
            
            }catch(SQLException sd){
                sd.printStackTrace();
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
            java.util.logging.Logger.getLogger(M_20000_Crank_Dia_Por_Stbd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(M_20000_Crank_Dia_Por_Stbd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(M_20000_Crank_Dia_Por_Stbd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(M_20000_Crank_Dia_Por_Stbd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new M_20000_Crank_Dia_Por_Stbd("new1123").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
