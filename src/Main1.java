import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class Main1 extends javax.swing.JFrame {

        Object[] headerNames = null;
        Object ob[][] = null,ob1[][]=null;
        JTable jTable1,jTable2;
        JPanel p1,p2;
        JTextField jt1,jt2;
        JMenu jMenu1,jMenu2;
        JMenuBar jMenuBar1;
            String sUser="",sPass="",sCat="";
        /**
     * Creates new form Maintenence_3000
     */
    public Main1() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-400,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //setLayout(null);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu("Ab");
        jMenu2 = new javax.swing.JMenu("Abbb");
        jMenu1.setText("Leeway");
        jMenuBar1.add(jMenu1);
//        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        
        JMenuItem addNewEngine,userMgmt,addUser;
        
        userMgmt = new JMenu("User Management.");
        
        
        
        addNewEngine = new JMenuItem("Add New Engine.");
                    addNewEngine.addActionListener(new ActionListener()
                         {
                         public void actionPerformed(ActionEvent ae)
                             {
                                 StartNewEngine sne = new StartNewEngine();
                             }
                          });
        
        addUser = new JMenuItem("Add New User");
                    addUser.addActionListener(new ActionListener()
                         {
                         public void actionPerformed(ActionEvent ae)
                             {
                                 AddUser auq = new AddUser();
                             }
                          });
        Authentiction a = new Authentiction("faq");
        sUser = a.retUser();
        sPass = a.retPass();
        sCat = a.retCat();
        setTitle("Welcome - "+sUser);
        
                if(sCat.equalsIgnoreCase("Admin")){
            //System.out.println("Admin entered");
                jMenu1.add(userMgmt);
                jMenu1.add(addNewEngine);
                userMgmt.add(addUser);
                

        }
        else if(sCat.equalsIgnoreCase("User")){
            //System.out.println("User entered");
        }else{
            
        }
        
        setLayout(new GridLayout(1,2));
        

            p1 = new JPanel();
            p1.setLayout(new BorderLayout());
            JScrollPane jscr = new JScrollPane(jTable1);
            JPanel p11 = new JPanel();
            p1.add(p11,"North");
            p11.setLayout(new FlowLayout());
            p11.add(new JLabel("Search:-"));
            jt1 = new JTextField(30);
            p11.add(jt1);
                jt1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        updateTable();
                    }
                });
            
        try{
            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;

            jTable1 = new JTable(){
            	public boolean isCellEditable(int rowIndex,int colIndex)
		{
		return false;
		}
            };

            updateTable();
            JScrollPane jscr1 = new JScrollPane(jTable1);
            p1.add(jscr1,"Center");
            JButton b1 = new JButton("Save Old Fitted Injector Sheet");
            p1.add(b1,"South");
          //p1.add(new JButton("Save File to DB"));
          //jscr.setBounds(90,100,800,500);
            this.add(p1);
                        
                
//// Events started..

        /*jt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateTable2();
            }
        });*/




        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                   int key = evt.getKeyCode();
                    if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
                    {
                        //System.out.println(jTable1.getSelectedRow());
                        //System.out.println("At -> "+jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                        try{

                            Connect ca = new Connect();
                            Connection cn = ca.getConnection();
                            PreparedStatement psc = null;
                            ResultSet rsc = null;
                            String serial = (String) jTable1.getValueAt(jTable1.getSelectedRow(),0);
                            //jLabel4.setText(serial);
                            String qe = "select Hours_Track,Ins_Group,Status from Maintenance_Tracking where Serial_Num =?";
                            psc=cn.prepareStatement(qe);
                            psc.setString(1,serial);
                            rsc = psc.executeQuery();
                            jTable2.setModel(DbUtils.resultSetToTableModel(rsc));
                            jTable2.setRowHeight(30);

                        }catch(SQLException se){
                            se.printStackTrace();
                        }
                    }
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                   try{

            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            String serial = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            //jLabel4.setText(serial);
            String qe = "select Hours_Track,Ins_Group,Status,Technician_Name from Maintenance_Tracking where Serial_Num =? ORDER BY Hours_Track,Ins_Group";
            psc=cn.prepareStatement(qe);
            psc.setString(1,serial);
            rsc = psc.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rsc));
            jTable2.setRowHeight(30);
            getNewRenderedTable(jTable2);
            //jTable2.setBackground(new Color(0,153,0));
            //System.out.println("Model Table  "+jTable2s.getModel());
//            jTable2.setCellSelectionEnabled(true);
//            jTable2.setColumnSelectionAllowed(false);
            jTable2.setRowSelectionAllowed(true);

        }catch(SQLException se){
            se.printStackTrace();
        }
            }
        });     
                
                
                
//Start rendering New OverHauled data

            jTable2 = new JTable(){
            			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return false;
				}
            };                    
            p2 = new JPanel();
            p2.setLayout(new BorderLayout());
            JScrollPane jscr2 = new JScrollPane(jTable2);
                JPanel p21 = new JPanel();
                p2.add(p21,"North");
                
                p21.setLayout(new FlowLayout());
                p21.add(new JLabel("Search:-"));
                jt2 = new JTextField(30);
                p21.add(jt2);
                jt2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        updateTable2();
                    }
                });

                    JButton b11 = new JButton("             Open            ");
                    p21.add(b11);
                  
                    b11.addActionListener(new ActionListener()
                         {
                         public void actionPerformed(ActionEvent ae)
                             {
                            try{
                              String serial_num = (String) jTable1.getValueAt(jTable1.getSelectedRow(),0);
                              Object xaa =  jTable2.getValueAt(jTable2.getSelectedRow(),0);
                              String x = String.valueOf(xaa);
                              //System.out.println(serial_num+"   "+x);
                              if(x.equals("20000")){
                                  Maintenance_2000 m2 = new Maintenance_2000(serial_num,1);
                                  //System.out.println("2000 is detected..");
                                  //Put object link here.
                              }else{
                                  //System.out.println("Serial Print  -- "+serial_num+"   "+x);
                                  //Job_Card jc = new Job_Card(serial_num,x.trim(),sUser,sPass,sCat);
                                  Job_Card jc = new Job_Card(serial_num,x.trim(),"admin","admin","Admin");
                              }
                          }catch(Exception npe){
                              JOptionPane.showMessageDialog(null,"Please Select Proper row in Both Table");
                          }
                             }
                          });

            p2.add(jscr2,"Center");
            JButton b2 = new JButton("Save - New OverHauled Sheet");
            p2.add(b2,"South");
            //p1.add(new JButton("Save File to DB"));
            //jscr.setBounds(90,100,800,500);
            jTable2.setRowHeight(30);
            this.add(p2);


        }catch(Exception w){
            w.printStackTrace();
        }
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    Main1(String abcxdijfskdf) {

    }

    private static void getNewRenderedTable(final JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                String status = (String)table.getModel().getValueAt(row, 2);
                if ("Completed".equals(status)) {
                    setBackground(new Color(0,153,0));
                    setForeground(Color.BLACK);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });

        //return table;
    }
    
    
        public void updateTable()
        {
            try{
            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            String qe = "select Serial_Num from Maintenance_Tracking where Serial_Num LIKE '"+jt1.getText()+""+"%'  Group by Serial_Num";
            psc=cn.prepareStatement(qe);
                rsc = psc.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rsc));
                jTable1.setRowHeight(30);

    }catch(SQLException se){
            se.printStackTrace();
        }
        }
        public void updateTable2()
        {
       if(jTable1.getSelectedRow() == -1){
           JOptionPane.showMessageDialog(this, "Please Select the Engine Number");
       } else {
        try{

       Connect ca = new Connect();
       Connection cn = ca.getConnection();
       PreparedStatement psc = null;
       ResultSet rsc = null;
       Object ob = jTable1.getValueAt(jTable1.getSelectedRow(),0);
       String sc = ob.toString();
       //System.out.println("Engine ID ->>  "+sc);

       
            String qe = "select Hours_Track,Ins_Group,Status,Technician_Name from Maintenance_Tracking where  Serial_Num = ? and (Hours_Track LIKE '"+jt2.getText()+""+"%' or Ins_Group LIKE '"+jt2.getText()+""+"%') ORDER BY Hours_Track,Ins_Group";
            psc=cn.prepareStatement(qe);
            psc.setString(1,sc);
                rsc = psc.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rsc));
                jTable2.setRowHeight(30);

    }catch(SQLException se){
            se.printStackTrace();
        }
        }
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
                new Main1();
            }
        });
    }
}