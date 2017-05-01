import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
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
public class Main extends javax.swing.JFrame {

    		Connection cn=null;
                Statement s = null;
                private JPopupMenu jpm;
                private JMenuItem open,addUser;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Welcome to Diesel Build Book");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 20, 278, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 0, 0);

        jScrollPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane5MouseClicked(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable2);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(480, 120, 396, 282);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(530, 90, 350, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Search");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(490, 90, 37, 15);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(280, 90, 176, 22);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Search");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(230, 90, 37, 15);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Open >>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(228, 420, 650, 23);

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseReleased(evt);
            }
        });
        jScrollPane3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane3KeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(230, 120, 223, 282);

        jMenu1.setText("Leeway");
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        updateTable2();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jScrollPane3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3KeyReleased

    private void jScrollPane3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3MouseReleased

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked

    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
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
                String serial = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
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
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
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
            //jTable2.setBackground(new Color(0,153,0));
            getNewRenderedTable(jTable2);
            //System.out.println("Model Table  "+jTable2.getModel());
            jTable2.setCellSelectionEnabled(true);
            jTable2.setColumnSelectionAllowed(false);
            jTable2.setRowSelectionAllowed(false);

        }catch(SQLException se){
            se.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        updateTable();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try{
            //JTree tree = (JTree) evt.getSource();
            /*            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
            String selectedNodeName = selectedNode.toString();
            //            String parentSelected = selectedNode.getParent().toString();
            String rootSelected = selectedNode.getRoot().toString();
            if (selectedNode.isLeaf()) {

                //System.out.println(selectedNodeName+"   "+parentSelected+"  "+rootSelected);
                StringTokenizer st = new StringTokenizer(selectedNodeName,"-");
                //System.out.println("1 - > "+st.nextToken());
                //System.out.println("Parent "+parentSelected);
                String x = st.nextToken();
                if(x.equals("20000")){
                    Maintenance_2000 m2 = new Maintenance_2000();
                    //System.out.println("2000 is detected..");
                    //Put object link here.
                }else{
                    Job_Card jc = new Job_Card(x.trim(),sUser,sPass,sCat);
                }
            }*/
            String serial_num = (String) jTable1.getValueAt(jTable1.getSelectedRow(),0);
            Object xaa =  jTable2.getValueAt(jTable2.getSelectedRow(),0);
            String x = String.valueOf(xaa);
            System.out.println(serial_num+"   "+x);
            if(x.equals("20000")){
                Maintenance_2000 m2 = new Maintenance_2000(serial_num,1);
                //System.out.println("2000 is detected..");
                //Put object link here.
            }else{
                //System.out.println("Serial Print  -- "+serial_num+"   "+x);
                Job_Card jc = new Job_Card(serial_num,x.trim(),sUser,sPass,sCat);
            }
        }catch(Exception npe){
            JOptionPane.showMessageDialog(null,"Please Select Proper row in Both Table");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jScrollPane5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane5MouseClicked

    }//GEN-LAST:event_jScrollPane5MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() == 2) {
            System.out.println("Double Table Clicked.");
        } else {
        }
    }//GEN-LAST:event_jTable2MouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
    
    private static JTable getNewRenderedTable(final JTable table) {
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

        return table;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    JMenuItem description,cylHead,summaryReport,engCertOfConf,shippingDocs,testCert;
    JMenuItem injPpAcceptTest,injPpTimingTest,injTest;
    JMenuItem frsWtrPpTest,rawWtrPpTest,heatExchanger,oilCooler,superCharger,mainBearingBore,camShaft,connectngRod;

    JMenuItem addNewEngine;

    javax.swing.JMenu fuelSystem,userMgmt;
    DefaultMutableTreeNode[] tobj = new DefaultMutableTreeNode[96];

    String sUser="",sPass="",sCat="";

    public Main(String x){
        
    }
    public Main(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-400,dim.height-100);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(new BorderLayout());
        //this.add(jLabel1,BorderLayout.NORTH);
        JPanel p1 = new JPanel();
        
        this.add(p1,BorderLayout.CENTER);
        p1.setLayout(new GridLayout(2,2));
        //p1.add(jLabel3);
        //p1.add(jTextField1);
        //p1.add(jLabel5);
        //p1.add(jTextField2);

        Authentiction a = new Authentiction("faq");
        
        sUser = a.retUser();
        sPass = a.retPass();
        sCat = a.retCat();
        setVisible(true);
        
        setTitle("Welcome - "+sUser);
        //System.out.println("111  In Main "+sUser+"    "+sCat+"   "+sPass);
//        Report rt = new Report("Fuel System","3000");
//        System.out.println("Report Constructor called....");
        setResizable(true);
        setTitle("Welcome - "+sUser);
        //System.out.println("In Main "+sUser+"    "+sCat+"   "+sPass);
        updateTable();
        //Maintenence_3000 m3 = new Maintenence_3000("Abcxdijfskdf");
        //m3.createTemplate_3000("eng_123");
        System.out.println("Boolean Display  "+checkoutInternal("new1123","A","125"));
        //checkoutInternal(String ser1,String grpid,String hour1)
        userMgmt = new JMenu("User Management.");
        addNewEngine = new JMenuItem("Add New Engine.");
        addUser = new JMenuItem("Add New User");


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


        //DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

//        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        
//        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");


//  System.out.println("Paras Dudhatra.......");

    try{

       Connect ca = new Connect();
       Connection cn = ca.getConnection();
       PreparedStatement psc = null;
       ResultSet rsc = null;
       
            String qe = "select Group_ID from Group_Inspection Group by Group_ID";

            psc=cn.prepareStatement(qe);
                rsc = psc.executeQuery();
                int xcn = 0;
                while(rsc.next())
                {
                    xcn++;
                }
                psc.close();
                psc=cn.prepareStatement(qe);
                rsc = psc.executeQuery();
                String[] hour_array = new String[xcn];
                xcn=0;
                while(rsc.next())
                {
                    hour_array[xcn] = rsc.getString("Group_ID");
                    xcn++;
                }
                psc.close();
    }catch(SQLException se){
            se.printStackTrace();
        }

            addUser.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent ae)
			{
                            AddUser ad = new AddUser();
                        }
                });
            addNewEngine.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent ae)
			{
                            StartNewEngine se = new StartNewEngine();
                        }
                });
            
            jTable2.addMouseListener(new MouseAdapter()
		{
		public void mousePressed(MouseEvent e)
			{
			showPopup(e);
			}
		public void mouseReleased(MouseEvent e)
			{
			showPopup(e);	
			}				
		});

                jpm = new JPopupMenu();
		open = new JMenuItem("Checkout this Maintenance");
		jpm.add(open);
	open.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent ae)
			{
		Object ob;
		Object ob1;
                Object ob2;
                Object ob3;

                ob = jTable1.getValueAt(jTable1.getSelectedRow(),0);

                ob1 = jTable2.getValueAt(jTable2.getSelectedRow(),0);
                ob2 = jTable2.getValueAt(jTable2.getSelectedRow(),1);
                ob3 = jTable2.getValueAt(jTable2.getSelectedRow(),2);

                String eng_id = ob.toString();
                String hour = ob1.toString();
                String  group= ob2.toString();
                String status = ob3.toString();
                Main msd = new Main("Faq");
                
                if(checkoutInternal(eng_id,group,hour)){
                    if(msd.checkoutValidate(eng_id, group, hour)){
                                if(status.equals("Completed")){
                                     JOptionPane.showMessageDialog(msd, "Already Checked Out");
                             } else {
                             Checkout cko = new Checkout(eng_id,hour,group,sUser);
                             }
                    } else {
                        Object[] options = { "OK"};
            int xc = JOptionPane.showOptionDialog(null, "Please Complete the previous maintenance", "Checkout is not validated.",JOptionPane.DEFAULT_OPTION, JOptionPane.OK_CANCEL_OPTION
    ,null, options, options[0]);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please Complete the whole System before checkout");
                }
                
//		System.out.println("Open Selected..");
                    }
		});
    }
    
       /* private void enterNewEngine(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("Enter the Engine Serial Number:-", SwingConstants.RIGHT));
        p.add(labels,BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField username = new JTextField("VISHVESH");
        controls.add(username);
        p.add(controls, BorderLayout.CENTER);

        //LayoutManager l = new GroupLayout(p);
        //p.setLayout(l);
        JOptionPane.showMessageDialog(frame,p,"Log In",JOptionPane.QUESTION_MESSAGE);
        System.out.println("User name "+username.getText());
    }*/

       /* private void showLogin(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));
        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("User Name", SwingConstants.RIGHT));
        labels.add(new JLabel("Password", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField username = new JTextField("admin");
        controls.add(username);
        JPasswordField password = new JPasswordField("admin");
        username.addAncestorListener(new RequestFocusListener(false));
        controls.add(password);
        p.add(controls, BorderLayout.CENTER);        

        username.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    password.requestFocus();
            }
      }
        });
        //LayoutManager l = new GroupLayout(p);
        //p.setLayout(l);
       /* Object[] options = { "OK"};
	int xc = JOptionPane.showOptionDialog(null, "enter QUANTITY", "Quantity",JOptionPane.DEFAULT_OPTION, JOptionPane.OK_CANCEL_OPTION
,null, options, options[0]);
        JOptionPane.showMessageDialog(frame,p,"Log In",JOptionPane.DEFAULT_OPTION);
        
        
        System.out.println("User name "+username.getText());
        String passText = new String(password.getPassword());

        try{
            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;

            String qe = "select * from User_Management where User_ID=? and Password_User=?";

            psc=cn.prepareStatement(qe);
            psc.setString(1,username.getText());
            psc.setString(2,passText);
                
                rsc = psc.executeQuery();
                while(rsc.next())
                {
                    user = rsc.getString("User_ID");
                    pass = rsc.getString("Password_User");
                    cat = rsc.getString("Category_User");
                }
                psc.close();
                System.out.println("Formatting "+user+"  "+pass+"  "+cat);
                if(user.equals("") && pass.equals("") && cat.equals("")){

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
                    showLogin(this);
                }
                else{
                    sUser=user;
                    sPass=pass;
                    sCat=cat;
                }
    }catch(SQLException df){
        df.printStackTrace();
    }
    }*/
        public void updateTable()
        {
            try{
            Connect ca = new Connect();
            Connection cn = ca.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            String qe = "select Serial_Num from Maintenance_Tracking where Serial_Num LIKE '"+jTextField1.getText()+""+"%'  Group by Serial_Num";
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

       
            String qe = "select Hours_Track,Ins_Group,Status,Technician_Name from Maintenance_Tracking where  Serial_Num = ? and (Hours_Track LIKE '"+jTextField2.getText()+""+"%' or Ins_Group LIKE '"+jTextField2.getText()+""+"%') ORDER BY Hours_Track,Ins_Group";
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
    	   private void showPopup(java.awt.event.MouseEvent evt)
		{
			if(evt.isPopupTrigger())
				{
					jpm.show(evt.getComponent(),evt.getX(),evt.getY());
				}
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
            psc.setString(1,serial_arg);
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
//                System.out.println("Array Length "+hr.length);
                for(int i=0;i<hr.length;i++){
                    if(hr[i].equals(hour_arg) && grup[i].equals(group_arg)){
                        
                        if(i == 0){
//                            System.out.println("Index is "+i);
  //                          System.out.println("Index 0 engine is validated for checkout.");-
                            return true;
                        }
                        else{
//                        System.out.println("At index "+i);
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

    
    private static boolean checkoutInternal(String ser1,String grpid,String hour1){
                try{
                    System.out.println(ser1+"  "+grpid+"    "+hour1);
        Connect ca = new Connect();
    
        Connection cn = ca.getConnection();
        PreparedStatement psc = null;
        ResultSet rsc = null;
        Statement s = null,sc=null;
        
            String qe = "select Status from Inner_System_Status where  Serial_Num = ? and Hours_Eng=? and Group_Name=?";
            psc=cn.prepareStatement(qe);
            psc.setString(1,ser1);
            psc.setString(2,hour1);
            psc.setString(3,grpid);
                rsc = psc.executeQuery();
                
                while(rsc.next()){
                    if(rsc.getString("Status").equals("Incomplete"))
                    {
                        return false;
                    }
                    else {
                        
                    }
                }
                }catch(SQLException sd){
                    sd.printStackTrace();
                }
                return true;
    }
}

class RequestFocusListener implements AncestorListener
{
    private boolean removeListener;
    public RequestFocusListener()
    {
        this(true);
    }
    public RequestFocusListener(boolean removeListener)
    {
        this.removeListener = removeListener;
    }

    @Override
    public void ancestorAdded(AncestorEvent e)
    {
        JComponent component = e.getComponent();
        component.requestFocusInWindow();

        if (removeListener)
            component.removeAncestorListener( this );
    }

    @Override
    public void ancestorMoved(AncestorEvent e) {}

    @Override
    public void ancestorRemoved(AncestorEvent e) {}
}