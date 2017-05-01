import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.AbstractCellEditor;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
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
public class Job_Card extends javax.swing.JFrame {

    Object[][] ob = null;
    Object[] headerNames = null;
    private JPopupMenu jpm;
    private JMenuItem open,print,checkout;
    String hrr = "",serial_n="",sUser="",sPass="",sCat="";
    int ind = 0;

    public Job_Card(String serial_num,String hours,String user,String pass,String cat) {
        initComponents();
        setLayout(new BorderLayout());
        setVisible(true);
        setTitle("Job Card - "+serial_num+ "              Hour - "+hours);
        
        hrr = hours;
        serial_n=serial_num;
        sUser = user;
        sPass = pass;
        sCat = cat;
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-300,dim.height-200);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        headerNames = new Object[]{"System","Reference","Group","Technician","Status"};

        try{
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;

            //System.out.println("Indec -> "+ind);

        /*String[] levArr = {"A","B","C","D","E","F"};
        
        for(int i=0;i<levArr.length;i++)
        {
            if(levArr[i].equals(level))
            {
                ind = i;
                break;
            }
            else{

            }
        }*/
            
            
            /*for(int p=0;p<ind;p++)
            {
                concat+="'"+levArr[p]+"'"+",";
            }*/
            //concat+="'"+levArr[ind]+"'"+")";
            //in('A')('B')

            //String qe1 = "select * from Group_Inspection where Group_ID in"+"('"+"A"+"')";
            String q = "select * from Hour_Group_Level_Mapping where Hours_Maintenance = "+hours;
//            System.out.println("Query is -> "+qe1);
            sc=cn.createStatement();
            rsc = sc.executeQuery(q);
            int t = 0;
            while(rsc.next())
            {
                t++;
            }
            String[] level = new String[t];
            sc=cn.createStatement();
            rsc = sc.executeQuery(q);
            t = 0;
            while(rsc.next())
            {
                level[t]=rsc.getString("Inspection_Groups");
                t++;
            }

//            System.out.println("Printing Levels....");
            for(int i=0;i<level.length;i++)
            {
  //          System.out.println(level[i]);
            }
            sc.close();
            //String qe1 = "select * from Group_Inspection where Group_ID in"+"('"+"A"+"')";
            //Forming Dynamic query.
            String concat= "select * from Group_Inspection where Group_Category in"+"(";
            for(int p=0;p<level.length-1;p++)
            {
                concat+="'"+level[p]+"'"+",";
            }
            concat+="'"+level[level.length-1]+"'"+")"+"ORDER BY Group_Category";
            //Dynamic Query Formed.
            sc=cn.createStatement();
            rsc = sc.executeQuery(concat);
            int ct = 0;
            while(rsc.next())
            {
                ct++;
            }
            ob = new Object[ct][5];
            sc=cn.createStatement();
            rsc = sc.executeQuery(concat);
            int cnt = 0;
            while(rsc.next())
            {
                ob[cnt][0] = rsc.getString("Location");
//                ob[cnt][1] = rsc.getString("Brief_Description");
                ob[cnt][1] = rsc.getString("Reference");
                ob[cnt][2] = rsc.getString("Group_Category");
                
                //System.out.println(hrr+"  -  "+(ob[cnt][0]).toString()+"     "+(ob[cnt][2]).toString());
                PreparedStatement ps1 = null;
                ResultSet r1 = null;
                String qwr = "select Technician_Name,Status from Inner_System_Status where Serial_Num=? and Hours_Eng=? and Group_Name=? and System_Name=?";
                ps1 = cn.prepareStatement(qwr);
                ps1.setString(1, serial_num);
                ps1.setString(2, hrr);
                ps1.setString(3, (String)(ob[cnt][2]));
                ps1.setString(4, (String)(ob[cnt][0]));
                r1 = ps1.executeQuery();
                while(r1.next())
                {
                    //Always a unique record.
                    ob[cnt][3] = r1.getString("Technician_Name");
                    ob[cnt][4] = r1.getString("Status");
                }
                cnt++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        JTable jTable1 = new JTable(ob,headerNames){
			public boolean isCellEditable(int rowIndex,int colIndex)
				{
				return false;
				}
        };
        //panel.add(new JButton("Center"),BorderLayout.CENTER);
        JScrollPane jscr = new JScrollPane(jTable1);
        this.add(jscr,"Center");
        //jscr.setBounds(50,50,900,700);

        //jTable1.getColumnModel().getColumn(1).setCellRenderer(new JTextAreaColumn());
        //jTable1.getColumnModel().getColumn(1).setCellEditor(new JTextAreaColumn());
        jTable1.setRowHeight(30);


	jTable1.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {                
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
			for(int y1=0;y1<jTable1.getRowCount();y1++)
			{
                      if(jTable1.isRowSelected(y1))
				{
				Object ob = new Object();
				ob = jTable1.getValueAt(y1,0);
			String str1 = ob.toString();
//			System.out.println("Display -> "+str1);
				}
			}
			}
		}
		});
				jTable1.addMouseListener(new MouseAdapter()
				{
                public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                
                int x = jTable1.getSelectedRow();
                
                Object ob,ob1,ob3;
                
                ob = jTable1.getValueAt(x,0);
                ob1 = jTable1.getValueAt(x,1);
                ob3 = jTable1.getValueAt(x,2);

                String str1 = ob.toString();
                String str2 = ob1.toString();
                String grp_id = ob3.toString();

                TextPaneTest ts = new TextPaneTest(grp_id,str1,hrr);
            } else {
            }

        }
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
		open = new JMenuItem("Open");
                print = new JMenuItem("Edit");
                checkout = new JMenuItem("Checkout System");
		jpm.add(open);
                jpm.add(print);
                jpm.add(checkout);
                

	checkout.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent ae)
			{
		Object ob1,ob2,ob3;

                ob1 = jTable1.getValueAt(jTable1.getSelectedRow(),0);
                ob2 = jTable1.getValueAt(jTable1.getSelectedRow(),2);
                ob3 = jTable1.getValueAt(jTable1.getSelectedRow(),4);


                String sys = ob1.toString();
                String  group= ob2.toString();
                String status = ob3.toString();

                PreparedStatement ps=null;                
            if(status.equals("Incomplete")){
                try{
                Connect c = new Connect();
                Connection cn = c.getConnection();
                
                String Qrys1 = "update Inner_System_Status set Technician_Name=?,Status=? where  Serial_Num=? and Hours_Eng=? and Group_Name=? and System_Name=?";
                ps = cn.prepareStatement(Qrys1);
                ps.setString(1,sUser);
                ps.setString(2,"Completed");
                ps.setString(3,serial_n);
                ps.setString(4,hrr);
                ps.setString(5,group);
                ps.setString(6,sys);
                ps.executeUpdate();
                ps.close();
                dispose();
                
                Job_Card ccv = new Job_Card(serial_n,hrr,sUser,sPass,sCat);
                
                }catch(SQLException sd){
                    sd.printStackTrace();
                }
            } else {
                    Object[] options = { "OK"};
	int xc = JOptionPane.showOptionDialog(null, "System is already Checked out !", "Checkout not possible.",JOptionPane.DEFAULT_OPTION, JOptionPane.OK_CANCEL_OPTION
,null, options, options[0]);
                }
                 /*
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
                */
                
//		System.out.println("Open Selected..");
                    }
		});

	open.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent ae)
			{
	for(int y1=0;y1<jTable1.getRowCount();y1++)
			{
                if(jTable1.isRowSelected(y1))
                {
		Object ob;
		Object ob1;
                Object ob2;
                Object ob3;

                ob = jTable1.getValueAt(y1,0);
                ob1 = jTable1.getValueAt(y1,1);
                ob3 = jTable1.getValueAt(y1,2);

                String str1 = ob.toString();
                String str2 = ob1.toString();
                String grp_id = ob3.toString();

                TextPaneTest ts = new TextPaneTest(grp_id,str1,hrr);
                

		//System.out.println(str1);
        		}
		}
//		System.out.println("Open Selected..");
                    }
		});

	print.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent ae)
			{
	for(int y1=0;y1<jTable1.getRowCount();y1++)
			{
                  if(jTable1.isRowSelected(y1))
        		{
                Object ob;
		Object ob1;
                Object ob2;
                Object ob3;

                ob = jTable1.getValueAt(y1,0);
                ob1 = jTable1.getValueAt(y1,1);
                ob2 = jTable1.getValueAt(y1,2);

                String str1 = ob.toString();
                String str2 = ob1.toString();
                String str4 = ob2.toString();
                String grp_id = ob2.toString();

                Job_Card_Description jcd = new Job_Card_Description(str1,hours,str2,serial_num,grp_id);
		//System.out.println(str1);
//new Job_Card_Description("Fuel System","1500","Section LJH,Section MJH,Section NJH","new1123","A").setVisible(true);  
        		}
		}
//		System.out.println("Open Selected..");
                    }
		});
            TableColumn column = null;
            for (int i = 0; i < 2; i++) {
		column = jTable1.getColumnModel().getColumn(i);
                	if (i == 0) {
				column.setPreferredWidth(300); //sport column is bigger
				}
                        if (i == 1) {
                            column.setPreferredWidth(300); //sport column is bigger
                	} 
                        if (i == 2) {
                            column.setPreferredWidth(150); //sport column is bigger
                	} 
                        if (i == 3) {
				column.setPreferredWidth(150); //sport column is bigger
				}
                        if (i == 4) {
                            column.setPreferredWidth(150); //sport column is bigger
                	}
                        
                        else {
		}
	}
            getNewRenderedTable(jTable1);
    }
    
        private static JTable getNewRenderedTable(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                String status = (String)table.getModel().getValueAt(row, 4);
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
    
    	   private void showPopup(java.awt.event.MouseEvent evt)
		{
			if(evt.isPopupTrigger())
				{
					jpm.show(evt.getComponent(),evt.getX(),evt.getY());
				}
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
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Job_Card.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Job_Card.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Job_Card.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Job_Card.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Job_Card("new1123","125","PARASHD","parashd","Admin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables




    private class JTextAreaColumn extends AbstractCellEditor implements TableCellRenderer,TableCellEditor {

        private JTextArea area = new JTextArea();
        private JScrollPane pane = new JScrollPane(area);

        @Override
        public Object getCellEditorValue() {
            return area.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            area.setText(value == null ? "" : value.toString());        
            return pane;
        }

        private void setColor(boolean isSelected, JTable table) {
            area.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            area.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            area.setText(value == null ? "" : value.toString());
            setColor(isSelected,table);
            return pane;
        }

    }
}