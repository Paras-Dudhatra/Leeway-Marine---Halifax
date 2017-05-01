import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class Maintenence_3000 extends javax.swing.JFrame {

        Object[] headerNames = null;
        Object ob[][] = null,ob1[][]=null;
        JTable jTable1,jTable2;
        JPanel p1,p2;
        /**
     * Creates new form Maintenence_3000
     */
    public Maintenence_3000(String ser_no,String fake) {
        initComponents();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-400,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //setLayout(null);
        setLayout(new GridLayout(1,2));
        setTitle("Maintenance Sheet - 3000 Hours.        Serial Number - "+ser_no);
        
        headerNames = new Object[]{"Inj Number","Cylinder","Breaing Presure(bar)","Leakdown(Sec)","Trip drip(Y/N)"};
        ob = new Object[16][5];
        ob1 = new Object[16][5];
        
        try{
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;
//Start rendering Old Engine data
            String qe1 = "select * from Old_Fitted_Injectors_3000 where Serial_Num = ?";
            psc=cn.prepareStatement(qe1);
            psc.setString(1,ser_no);
            rsc = psc.executeQuery();
            //sc=cn.createStatement();
            //rsc = sc.executeQuery(qe1);
            int cnt = 0;
            while(rsc.next())
            {
                ob[cnt][0] = rsc.getString("Injector_Num");
                ob[cnt][1] = rsc.getString("Cylinder");
                ob[cnt][2] = rsc.getString("Breaking_Pressure");
                ob[cnt][3] = rsc.getString("Leak_Down");
                ob[cnt][4] = rsc.getString("Trip_Drip");
                cnt++;
            }
            psc.close();

                    jTable1 = new JTable(ob,headerNames){
			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return colIndex == 0 ||colIndex == 2 || colIndex == 3 || colIndex == 4;
				}
	
        };
        


            p1 = new JPanel();
            p1.setLayout(new BorderLayout());
            JScrollPane jscr = new JScrollPane(jTable1);
            p1.add(new JLabel("    Old Fitted Injectors:-"),"North");
            
            p1.add(jscr,"Center");
            JButton b1 = new JButton("Save Old Fitted Injector Sheet");
            p1.add(b1,"South");
            //p1.add(new JButton("Save File to DB"));
            //jscr.setBounds(90,100,800,500);
            jTable1.setRowHeight(30);
            this.add(p1);
	
            b1.addActionListener(new ActionListener()
		{
                public void actionPerformed(ActionEvent ae)
                    {
                if (jTable1.isEditing())
                jTable1.getCellEditor().stopCellEditing();
                                try{
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement ps = null;
            ResultSet rsc = null;
            Statement sc = null;

            for(int i=0;i<jTable1.getRowCount();i++)
                {
                Object obx = jTable1.getValueAt(i,0);
                Object ob2 = jTable1.getValueAt(i,2);
                Object ob3 = jTable1.getValueAt(i,3);
                Object ob4 = jTable1.getValueAt(i,4);
                Object ob6 = jTable1.getValueAt(i,1);

                String s1 = obx.toString();
                String s2 = ob2.toString();
                String s3 = ob3.toString();
                String s4 = ob4.toString();
                String s6 = ob6.toString();
            String Qrys = "update Old_Fitted_Injectors_3000 set Injector_Num = ?,Breaking_Pressure = ?,Leak_Down = ?,Trip_Drip = ? where  Serial_Num = ? and Cylinder = ?";
            ps = cn.prepareStatement(Qrys);
            ps.setString(1,s1);
            ps.setString(2,s2);
            ps.setString(3,s3);
            ps.setString(4,s4);
            ps.setString(5,ser_no);
            ps.setString(6,s6);
            ps.executeUpdate();
            ps.close();
                }
            }catch(SQLException vv){
                vv.printStackTrace();
            }
          }
	});

//Start rendering New OverHauled data
            String qe2 = "select * from New_OverHauled_Injectors_3000 where Serial_Num_N = ?";
            psc=cn.prepareStatement(qe2);
            psc.setString(1,ser_no);
            rsc = psc.executeQuery();
            //sc=cn.createStatement();
            //rsc = sc.executeQuery(qe1);
            int cnt2 = 0;
            while(rsc.next())
            {
                ob1[cnt2][0] = rsc.getString("Injector_Num_N");
                ob1[cnt2][1] = rsc.getString("Cylinder_N");
                ob1[cnt2][2] = rsc.getString("Breaking_Pressure_N");
                ob1[cnt2][3] = rsc.getString("Leak_Down_N");
                ob1[cnt2][4] = rsc.getString("Trip_Drip_N");
                cnt2++;
            }

                    jTable2 = new JTable(ob1,headerNames){
			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return colIndex == 0 ||colIndex == 2 || colIndex == 3 || colIndex == 4;
				}
	
        };
            p2 = new JPanel();
            p2.setLayout(new BorderLayout());
            JScrollPane jscr2 = new JScrollPane(jTable2);
            p2.add(new JLabel("New OverHauled Injector Sheet:-"),"North");
            p2.add(jscr2,"Center");
            JButton b2 = new JButton("Save - New OverHauled Sheet");
            p2.add(b2,"South");
            //p1.add(new JButton("Save File to DB"));
            //jscr.setBounds(90,100,800,500);
            jTable2.setRowHeight(30);
            this.add(p2);

            b2.addActionListener(new ActionListener()
		{
                public void actionPerformed(ActionEvent ae)
                    {
                if (jTable2.isEditing())
                jTable2.getCellEditor().stopCellEditing();
                                try{
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement ps = null;
            ResultSet rsc = null;
            Statement sc = null;

            for(int i=0;i<jTable2.getRowCount();i++)
                {
                Object obx = jTable2.getValueAt(i,0);
                Object ob2 = jTable2.getValueAt(i,2);
                Object ob3 = jTable2.getValueAt(i,3);
                Object ob4 = jTable2.getValueAt(i,4);
                Object ob6 = jTable2.getValueAt(i,1);

                String s1 = obx.toString();
                String s2 = ob2.toString();
                String s3 = ob3.toString();
                String s4 = ob4.toString();
                String s6 = ob6.toString();
            String Qrys = "update New_OverHauled_Injectors_3000 set Injector_Num_N = ?,Breaking_Pressure_N = ?,Leak_Down_N = ?,Trip_Drip_N = ? where  Serial_Num_N = ? and Cylinder_N = ?";
            ps = cn.prepareStatement(Qrys);
            ps.setString(1,s1);
            ps.setString(2,s2);
            ps.setString(3,s3);
            ps.setString(4,s4);
            ps.setString(5,ser_no);
            ps.setString(6,s6);
            ps.executeUpdate();
            ps.close();
                }
            }catch(SQLException vv){
                vv.printStackTrace();
            }
          }
	});

        }catch(SQLException sd){
            sd.printStackTrace();
        }
    }

    Maintenence_3000(String abcxdijfskdf) {

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
            java.util.logging.Logger.getLogger(Maintenence_3000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Maintenence_3000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Maintenence_3000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Maintenence_3000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Maintenence_3000("new1123","fake").setVisible(true);
            }
        });
    }

    
    public void createTemplate_3000(String serial_num)
    {
        try{
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;

            sc=cn.createStatement();
            //Old Injector Table A
            for(int i=1;i<=8;i++)
                {
            sc.execute("insert into Old_Fitted_Injectors_3000(Serial_Num,Injector_Num,Cylinder,Breaking_Pressure,Leak_Down,Trip_Drip)values('"+serial_num+"','-','A"+i+"','-','-','-')");
                }


            //Old Injector Table B
            for(int i=1;i<=8;i++)
                {
            sc.execute("insert into Old_Fitted_Injectors_3000(Serial_Num,Injector_Num,Cylinder,Breaking_Pressure,Leak_Down,Trip_Drip)values('"+serial_num+"','-','B"+i+"','-','-','-')");
                }


            //New Injector Table A
            for(int i=1;i<=8;i++)
                {
            sc.execute("insert into New_OverHauled_Injectors_3000(Serial_Num_N,Injector_Num_N,Cylinder_N,Breaking_Pressure_N,Leak_Down_N,Trip_Drip_N)values('"+serial_num+"','-','A"+i+"','-','-','-')");
                }


            //New Injector Table B
            for(int i=1;i<=8;i++)
                {
            sc.execute("insert into New_OverHauled_Injectors_3000(Serial_Num_N,Injector_Num_N,Cylinder_N,Breaking_Pressure_N,Leak_Down_N,Trip_Drip_N)values('"+serial_num+"','-','B"+i+"','-','-','-')");
                }
            sc.close();
            System.out.println("Insert Executed Successfully");
            }catch(SQLException vv){
                vv.printStackTrace();
            }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
