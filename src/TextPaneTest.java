import java.awt.*;    
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;    
import javax.swing.border.*;    
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;

public class TextPaneTest extends JFrame {

    private JPanel topPanel;
    private JTextPane tPane;
    private int counter;
    private Color[] colours = { Color.BLACK };
    String hr = "";
    private String group="",loc="";

    public TextPaneTest(String grop,String location,String hour) {
        counter = 0;
        
        setTitle("Job Card Description.");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width-850,dim.height-150);
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    group=grop;
    loc=location;
    hr = hour;
    setLayout(new BorderLayout());
    JButton jb = new JButton("Print");
    this.add(jb,"South");
        this.createAndDisplayGUI();
            jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {

                    boolean done = tPane.print();
                    if (done) {
                        JOptionPane.showMessageDialog(null, "Printing is done");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error while printing");
                    }
                } catch (Exception pex) {
                    JOptionPane.showMessageDialog(null, "Error while printing");
                    pex.printStackTrace();
                }
            }
        });        
    }

    private void createAndDisplayGUI() {    


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

        tPane = new JTextPane();
        tPane.setEditorKit(new HTMLEditorKit());
        tPane.setContentType("text/html");
    
        //tPane.setBorder(eb);
    //    tPane.setMargin(new Insets(5, 5, 5, 5));
        JScrollPane scroller = new JScrollPane();
        scroller.setViewportView(tPane);

        try{
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;

            String q = "select * from Group_Inspection where Group_Category = ? and Location=?";
            psc=cn.prepareStatement(q);
            psc.setString(1,group);
            psc.setString(2,loc);
            rsc = psc.executeQuery();
            int heading = 17;
            int normal = 13;

                String under = "_________________________________________________________________________________\n\n";
                String hou = "Hour  "+" - "+hr+"\n\n";
                appendToPane(tPane, hou, colours[counter],heading);
                appendToPane(tPane, under, colours[counter],normal);
                
                /*Graphics2D g2 = (Graphics2D) g;

                        g2.setColor(new Color(255, 0, 0, 128));

                        FontMetrics fm = g2.getFontMetrics();
                        int textHeight = fm.getHeight();

                        for (int i = textHeight; i < getHeight(); i += (6 * textHeight)) {
                            g2.drawLine(0, i + 1, getWidth(), i + 1);
                        }*/

                

            while(rsc.next())
            {
                String grp1 = "Group "+" - "+group+"\n\n";
                appendToPane(tPane, grp1, colours[counter],heading);
                appendToPane(tPane, under, colours[counter],normal);
                
                String location = "Location :-"+rsc.getString("Location")+"\n\n";
                appendToPane(tPane, location, colours[counter],heading);
                appendToPane(tPane, under, colours[counter],normal);
                
                String hBriefDes = "Brief Description :"+"\n\n";
                appendToPane(tPane, hBriefDes, colours[counter],heading);
                appendToPane(tPane, under, colours[counter],normal);
                
                String desc = rsc.getString("Brief_Description")+"\n\n";
                appendToPane(tPane, desc, colours[counter],normal);
                
                String hSafe = "Safety Requirements"+"\n\n";
                appendToPane(tPane, hSafe, colours[counter],heading);
                appendToPane(tPane, under, colours[counter],normal);
                
                String safe = rsc.getString("Safety_Requirements")+"\n\n";
                appendToPane(tPane, safe, colours[counter],normal);
                
                String hPurpose = "Purpose"+"\n\n";
                appendToPane(tPane, hPurpose, colours[counter],heading);
                appendToPane(tPane, under, colours[counter],normal);
                
                String purpose = rsc.getString("Purpose")+"\n\n";
                appendToPane(tPane, purpose, colours[counter],normal);
            }
        }catch(SQLException sd){
            sd.printStackTrace();
        }


        getContentPane().add(scroller);
        setVisible(true);
    }

    private void appendToPane(JTextPane tp, String msg, Color c, int x) {

        StyledDocument doc = tp.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.FontSize,x);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_CENTER);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextPaneTest("C","Lubrication System","100");
            }
        });
    }
}