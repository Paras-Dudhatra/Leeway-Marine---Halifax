import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class Job_Card_Description extends javax.swing.JFrame {

    /**
     * Creates new form Job_Card_Description
     * @param a1
     * @param a2
     * @param a3
     * @param hr
     */
    private JPopupMenu jpm;
    private JMenuItem open;
    String serial1="";
    
    public Job_Card_Description(String a1,String hr,String ref,String serial_no,String grp) {
        initComponents();
        setTitle("  Serial Number - "+serial_no);
        setVisible(true);
        
        //System.out.println("Hour is "+hr);
        //System.out.println("a2  "+a2);
        //System.out.println("a1  "+a1);
        serial1=serial_no;

        setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-800,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;
            int ct=0;
            String[] gid=null,loc=null;
            JTabbedPane jtp = new JTabbedPane();
            //System.out.println("Reference  "+ref);
            this.add(new JScrollPane(new Template(hr,a1,serial_no,grp,ref)),BorderLayout.CENTER);

            //Hour system serial number.
            //jtp.addTab(a1+" Group - "+grp,new JScrollPane(new Template(hr,a1,serial_no,grp,ref)));
            try{
            String qe = "select * from Group_Inspection where Location = ?";
            psc=cn.prepareStatement(qe);
            psc.setString(1,a1);
            rsc = psc.executeQuery();

            while(rsc.next())
            {
                ct++;
            }
            //System.out.println("Count is "+ct);
            gid = new String[ct];
            loc = new String[ct];

            psc=cn.prepareStatement(qe);
            psc.setString(1,a1);
            rsc = psc.executeQuery();
            ct = 0;
            while(rsc.next())
            {
                gid[ct] = rsc.getString("Group_ID");
                loc[ct] = rsc.getString("Location");
                ct++;
            }
            }catch(SQLException wee){
                wee.printStackTrace();
            }
            /*for(int i=0;i<ct;i++)
            {
                
                if((gid[i].equals(hr))){
                    System.out.println("JD  "+gid[i]);
                    jtp.addTab(gid[i]+" Hours.", new JScrollPane(new Template(gid[i],a1,serial1)));
                    break;
                }
                else {
                    System.out.println("JD  "+gid[i]);
                    jtp.addTab(gid[i]+" Hours.", new JScrollPane(new Template(gid[i],a1,serial1)));
                }
                
            }*/
        //jtp.addTab("Templates", new JScrollPane(new Template()));
	//jtp.setBounds(80,100,720,600);

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
            .addGap(0, 785, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Job_Card_Description.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Job_Card_Description.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Job_Card_Description.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Job_Card_Description.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Job_Card_Description("Fuel System","1500","Section LJH,Section MJH,Section NJH","new1123","A").setVisible(true);  
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    	   private void showPopup(java.awt.event.MouseEvent evt)
		{
			if(evt.isPopupTrigger())
				{
					jpm.show(evt.getComponent(),evt.getX(),evt.getY());
				}
		}
}