import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.geom.*;
import javax.swing.JLabel;

public class AnotherPrintDemo extends JFrame {
  DrawingCanvas canvas;
  JButton setUpButton = new JButton("Page Setup");
  JButton printButton = new JButton("Print");
  JButton cancelButton = new JButton("Cancel");
  //AnotherPrintDemo(number of records,itemname Array,Quantity Array,Total Array,challan no.,custname,dates);
  public AnotherPrintDemo(String hour,String system) {
    //super();
	setTitle("Printout..");
	System.out.println("In APD constructor..");
    Container container = getContentPane();
	//JScrollPane scr = new JScrollPane();
	canvas = new DrawingCanvas(hour,system);
	//canvas = new DrawingCanvas();   finally not needed..
	System.out.println("In APD After 1st DC Called");
	container.add(canvas);
	//canvas1 = new DrawingCancas(x,aar,bar,car,dar);
 
	//JPanel panel = new JPanel(new GridLayout(1,3));
	JPanel panel = new JPanel(new BorderLayout());
    ButtonListener buttonListener = new ButtonListener();
    
	setUpButton.addActionListener(buttonListener);
    //panel.add(setUpButton);
    printButton.addActionListener(buttonListener);
    panel.add("South",printButton);
    cancelButton.addActionListener(buttonListener);
    //panel.add(cancelButton);
    container.add(BorderLayout.NORTH, panel);
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        dispose();
      }
    });
    setSize(650,1200);
    setVisible(true);
	setResizable(false);
  }
  class ButtonListener implements ActionListener{
    PrinterJob printJob;
    PageFormat pageFormat;
    PrintableCanvas printableCanvas;
    ButtonListener() {
      printJob = PrinterJob.getPrinterJob();
      pageFormat = printJob.defaultPage();
    }
    public void actionPerformed(ActionEvent e) {
      JButton tempButton = (JButton) e.getSource();
      if (tempButton.equals(setUpButton)) {
        pageFormat = printJob.pageDialog(pageFormat);
        printJob.validatePage(pageFormat);
      } else if (tempButton.equals(printButton)) {

	  System.out.println();
		printableCanvas = new PrintableCanvas(pageFormat);
        printJob.setPrintable(printableCanvas);
        
		boolean ok = printJob.printDialog();
        if (ok) {
          try {
            printJob.print();
          } catch (Exception pe) {

			Object[] options = { "OK"};
	int xc = JOptionPane.showOptionDialog(null, "Print Error....!", "Print",JOptionPane.DEFAULT_OPTION, JOptionPane.OK_CANCEL_OPTION
,null, options, options[0]);
            System.out.println("Printing Exception Occured!");
            pe.printStackTrace();
          }
        }
      }else if (tempButton.equals(cancelButton)) {
        printJob.cancel();
      }
    }
  }
  public static void main(String arg[]) {
//Frmo (count,ar1,ar2,ar3,ar4,name,city,adr1,adr2,phone,billno,date,vehicle,amount,note,tinGst,tinCst)
new AnotherPrintDemo("1500","Fuel System");
//  new AnotherPrintDemo(a,aw,aw1,aw2,aw3);
  }
}
class DrawingCanvas extends JPanel
{
  Font font,font1,font2;
  FontMetrics fontMetrics;
  int w, h;
  String tmp = "";
	//String[] num = {"1","2","3","4","5","6"};
	static int gtTrack=0,gt2Track=0;
  static int y;
	public DrawingCanvas()
	{
	
	}

public DrawingCanvas(String hours,String system)
{
    setBackground(Color.white);
	setSize(600,600);
	
	w = this.getWidth();
    h = this.getHeight();
	font = new Font("Dialog", Font.BOLD,28);
	font1 = new Font("Dialog", Font.ITALIC, 13);
	font2 = new Font("Dialog", Font.BOLD, 15);
    fontMetrics = getFontMetrics(font);
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D)g;
    paintContent(g2D,w,h);
	System.out.println("After paintCntent In DC paintComponent..");
	System.out.println();
  }
  
  public void paintContent(Graphics2D g2D,int w,int h)
  {
  
  int ls = 30;
  int setBno = 105;
  int setTin = 10;
  int srNm = 210;
  int headings = 200;
  int yCor = 60;
System.out.println("In DC  in paintContent..");
               //it will continue track where to print grand total.

	g2D.setFont(font);          //Bold and Big.
        int y = 35;
		//g2D.drawString("Java Source and Support", 10,100);
	      g2D.draw(new Line2D.Double(20,35,615,y));      //1 st line
	      g2D.draw(new Line2D.Double(20,36,615,y+1));      //1 st line along
//		  g2D.drawString("Patel Furniture ",ls,yCor);// after 1st 
              String aax = "Lubricate control gear elastic link with clean engine oil. Check all return"+
"springs for the security and distortion.";
	g2D.setFont(font2);
                g2D.drawString("Hour :- ",ls+2,y+20);
                g2D.drawString("System :- ",ls+300,y+20);
        	g2D.drawString("Purpose of Job done:- ",ls+2,y+45);
                g2D.drawString(aax,ls+200,y+45);
	//	g2D.drawString("BILL NO - ",ls+2,34); // after 1st
	//	g2D.drawString("125",setBno,34); // after 1st
		/*g2D.drawString("No.",ls,headings-15); // after 1st
		g2D.drawString("Original for Buyer ",ls+440,yCor); // after 1st
		g2D.drawString("Item Description",ls+80,headings-15); // after 1st
		g2D.drawString("Price",ls+80+240,headings-15); // after 1st
		g2D.drawString("Quantity",ls+80+240+100,headings-15); // after 1st
		g2D.drawString("Total",ls+80+240+100+100,headings-15); // after 1st
		g2D.drawString(" TO :-",ls,120); // after 1st
		//g2D.drawString("Location:-",ls,150); // after 1st
		g2D.drawString("Bill No.:-",ls+420,130); // after 1st
		g2D.drawString("Date.:-",ls+420,150); // after 1st

            g2D.setFont(font1);
            System.out.println("Info   "+cnamePr+"  "+cityPr+"  "+chPr+"  "+datePr);
		 g2D.drawString(chPr,ls+60,120);            // after 1st
		 g2D.drawString(cnamePr,ls+60,140);            // after 1st
		 g2D.drawString(datePr,ls+60,160);            // after 1st
		  g2D.drawString(cityPr,ls+490,130);            // after 1st
		  g2D.drawString(gurPr,ls+490,150);            // after 1st
		  
		  g2D.drawString("Tin No Gst :",ls,yCor+20);            // after Patel Furniture
		  g2D.drawString(ting,ls+70,yCor+20);            // after Patel Furniture

		  g2D.drawString("Tin No Cst :",ls,yCor+35);            // after Patel Furniture
		  g2D.drawString(tinc,ls+70,yCor+35);            // after Patel Furniture

        */		  
			//Box of TO And Address
/*	      g2D.draw(new Line2D.Double(30,yCor+45,435,yCor+45));      // upper Box hl1
	      g2D.draw(new Line2D.Double(30,yCor+105,435,yCor+105));      // upper Box hl2
		  g2D.draw(new Line2D.Double(30,yCor+45,30,yCor+105)); 		  //upper box vl1 
		  g2D.draw(new Line2D.Double(435,yCor+45,435,yCor+105)); 		  //upper box vl2 
		  
		  //Date & Bill No Box
	      g2D.draw(new Line2D.Double(445,yCor+45,605,yCor+45));      // upper Box hl1
	      g2D.draw(new Line2D.Double(445,yCor+105,605,yCor+105));      // upper Box hl2
		  g2D.draw(new Line2D.Double(445,yCor+45,445,yCor+105)); 		  //upper box vl1 
		  g2D.draw(new Line2D.Double(605,yCor+45,605,yCor+105)); 		  //upper box vl2 */
		  
		  

			/*int k = 0;
			float xTot = 0;
			System.out.println("Loop Render Started.. i is and y is "+srNm+"   "+y);
			for(int i = srNm;i <=srNm+(y-1)*15;i = i+15)   //
			{
		//	System.out.println(i);
		//g2D.drawString(tmp.valueOf(k+1),ls,i); // after 3 rd

		g2D.drawString(name1[k],ls+80,i); // after 3 rd
		System.out.println("pr Price is  "+pri[k]);
		g2D.drawString(pri[k],ls+80+240,i); // after 3 rd
		g2D.drawString(qnt[k],ls+80+240+100,i); // after 3 rd
		g2D.drawString(tot[k],ls+80+240+100+100,i); // after 3 rd
		xTot += Float.parseFloat(tot[k]);
		k++;

		gtTrack = i;      //assigning last value of label.
		   }
		  System.out.println("Render Started.. gtTrack is"+gtTrack);*/
/*	      g2D.draw(new Line2D.Double(20,170,615,170));    //2 nd line

	      g2D.draw(new Line2D.Double(20,190,615,190));    //3 rd line
		  g2D.draw(new Line2D.Double(20,gtTrack+20,615,gtTrack+20)); 		  //4 th line 
		  g2D.draw(new Line2D.Double(20,gtTrack+45,615,gtTrack+45)); 		  //5 th line 
		  g2D.draw(new Line2D.Double(20,gtTrack+65,615,gtTrack+65)); 		  //6 th line 
		  g2D.draw(new Line2D.Double(20,gtTrack+90,615,gtTrack+90)); 		  //7 th line 
		  g2D.draw(new Line2D.Double(20,gtTrack+91,615,gtTrack+91)); 		  //7 th line along
		  g2D.draw(new Line2D.Double(20,gtTrack+92,615,gtTrack+92)); 		  //7 th line along
		  g2D.draw(new Line2D.Double(20,35,20,gtTrack+91)); 		  //Vertical 1 st line 
		  g2D.draw(new Line2D.Double(21,35,21,gtTrack+91)); 		  //Vertical 1 st line along
		  g2D.draw(new Line2D.Double(70,170,70,gtTrack+20)); 		  //Vertical 2 nd line 
		  g2D.draw(new Line2D.Double(320,170,320,gtTrack+20)); 		  //Vertical 3 rd line 
		  g2D.draw(new Line2D.Double(430,170,430,gtTrack+20)); 		  //Vertical 4 th line 
		  g2D.draw(new Line2D.Double(530,170,530,gtTrack+20)); 		  //Vertical 5 th line 
		  g2D.draw(new Line2D.Double(615,35,615,gtTrack+91)); 		  //Vertical 6 th line 
		  g2D.draw(new Line2D.Double(616,35,616,gtTrack+91)); 		  //Vertical 6 th line along 

	g2D.setFont(font2);
	    g2D.drawString("Grand Total:",440,gtTrack+40); // after 4 th
	    g2D.drawString(tmp.valueOf(xTot),550,gtTrack+40); // after 4 th
	    g2D.drawString("Vehicle :-",ls,gtTrack+37); // after 4 th
	    g2D.drawString("Amount :-",ls+200,gtTrack+37); // after 4 th
	    g2D.drawString("Note:-",ls,gtTrack+60); // after 5 th
	    g2D.drawString(" Received By:- ",ls,gtTrack+85); // after 6 th
	    g2D.drawString(" -- For Patel Furniture ",ls+340,gtTrack+85); // after 6 th

	g2D.setFont(font1); 
		g2D.drawString(servPr,ls+70,gtTrack+37);            // after 4th
		g2D.drawString(vnosPr,ls+270,gtTrack+37);            // after 4th
		g2D.drawString(notePr,ls+50,gtTrack+60); // after 5 th*/
		  System.out.println("Render Complete.. gtTrack is"+(gtTrack+60));  //approximate 345   
                  JLabel lblFileLink = new JLabel("Audio File Title");
                  add(lblFileLink);
                // To indicate the the link is clickable
                lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                lblFileLink.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            //pdTable pd = new pdTable();
                        }
                    });
	}
}
class PrintableCanvas implements Printable {
  DrawingCanvas canvas;
  PageFormat pageFormat;

    //static String[] name1,qnt,pri,tot;
   // static String[] aar,bar,car,dar;
	//String chPr,cnamePr,datePr,cityPr,gurPr,servPr,vnosPr;
	int c;

  public PrintableCanvas(PageFormat pf){
    pageFormat = pf;
  }
  public int print(Graphics g,PageFormat pageFormat,int pageIndex)
      throws PrinterException {
	  System.out.println("pageIndex is   "+pageIndex);
    if (pageIndex >= 1) {
      return Printable.NO_SUCH_PAGE;
    }
    Graphics2D g2D = (Graphics2D)g;
    	//System.out.println("Print Width"+(int)pageFormat.getImageableWidth());
		//System.out.println("Print Height"+(int)pageFormat.getImageableHeight());
	canvas = new DrawingCanvas();
	System.out.println("In PC print method Second Time DC constructor");
	
    //canvas = new DrawingCanvas(c,aar,bar,car,dar,chPr,cnamePr,datePr,cityPr,gurPr,servPr,vnosPr);
    canvas.paintContent(g2D,(int)pageFormat.getImageableWidth(),(int)pageFormat.getImageableHeight());
	System.out.println("Print Successful..");
	System.out.println();
    return Printable.PAGE_EXISTS;
  }
}