import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class Report {    

    public Report(String a1,String hr)
    {
            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;
            Statement sc = null;
            int ct=0;
            String[] gid=null,loc=null;
            try{
            String qe = "select * from Group_Inspection where Location = ?";
            psc=cn.prepareStatement(qe);
            psc.setString(1,a1);
            rsc = psc.executeQuery();

            while(rsc.next())
            {
                //System.out.println(rsc.getString("Group_ID")+" is in "+rsc.getString("Location"));
                ct++;
            }
            psc.close();
            System.out.println("Count is "+ct);
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

            //Start rendering pdf here.
            Document document = new Document(PageSize.A4, 30.0f, 0f, 0f, 0f);
            	   //jl1.setFont(new Font("Georgia", Font.BOLD, 15));
            //java.awt.Font fn1 = new Font("Georgia", Font.BOLD,15);
            try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Report\\Leeway.pdf"));
            document.open();
            for(int i=0;i<ct;i++)
                {
                    if((gid[i].equals(hr))){
                        try{
            Font fn1 = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD);
            Font fn2 = new Font(FontFamily.TIMES_ROMAN,12.0f,Font.NORMAL);

                            String qe = "select * from Group_Inspection where Location = ? and Group_ID = ?";
                            psc=cn.prepareStatement(qe);
                            psc.setString(1,a1);
                            psc.setString(2,gid[i]);
                            rsc = psc.executeQuery();
                        System.out.println("Report  "+gid[i]);
                            while(rsc.next())
                            {

                                document.newPage();
                                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("400.GE"), 500, 800, 0);
                                Paragraph p1 = new Paragraph("Hour "+gid[i],fn1);
                                document.add(p1);

                                Paragraph p2 = new Paragraph("Purpose of job done:- \n",fn1);
                                document.add(p2);

                                Paragraph p3 = new Paragraph(rsc.getString("Purpose")+"\n\n",fn2);
                                document.add(p3);


                                Paragraph p5 = new Paragraph("Brief Description \n",fn1);
                                document.add(p5);

                                Paragraph p6 = new Paragraph(rsc.getString("Brief_Description")+"\n\n",fn2);
                                document.add(p6);

                                Paragraph p7 = new Paragraph("Safety Requirements:- \n",fn1);
                                document.add(p7);

                                Paragraph p8 = new Paragraph(rsc.getString("Safety_Requirements")+"\n\n",fn2);
                                document.add(p8);

                                Paragraph p9 = new Paragraph("Personal & time required :- \n\n",fn1);
                                document.add(p9);

                                Paragraph p11 = new Paragraph("                                                      ",fn1);
                                document.add(p11);

                                PdfPTable table = new PdfPTable(3);
                                // the cell object
                                PdfPCell cell;
                                cell = new PdfPCell();
                                cell.setColspan(3);
                                    table.addCell("Number");
                                    table.addCell("Qualification");
                                    table.addCell("Time_Hr");
                                    //Filling data in table.
                            PreparedStatement psc1 =null;
                            ResultSet rsc1 = null;
                            String qeq = "select * from Time_Required where Hour = ? and System = ?";
                            psc1=cn.prepareStatement(qeq);
                            psc1.setString(1,gid[i]);
                            psc1.setString(2,a1);
                            rsc1 = psc1.executeQuery();
                            while(rsc1.next())
                            {
                                table.addCell(rsc1.getString("Number"));
                                table.addCell(rsc1.getString("Qualification"));
                                table.addCell(rsc1.getString("Time_Hr"));
                            }
                            document.add(table);
                            psc1.close();
                            rsc1.close();

                            Paragraph p10 = new Paragraph("Tools and appliances required :- \n",fn1);
                            document.add(p10);
                            document.add(p11);

                                PdfPTable table1 = new PdfPTable(4);
                                // the cell object
                                PdfPCell cell1;
                                cell1 = new PdfPCell();
                                cell1.setColspan(4);
                                    table1.addCell("Quantity");
                                    table1.addCell("Designation");
                                    table1.addCell("Part Number");
                                    table1.addCell("NSN");
                                    //Filling data in table.
                            String qeq1 = "select * from Tools_Appliances where Hours = ? and System = ?";
                            psc1=cn.prepareStatement(qeq1);
                            psc1.setString(1,gid[i]);
                            psc1.setString(2,a1);
                            rsc1 = psc1.executeQuery();
                            while(rsc1.next())
                            {
                                table1.addCell(rsc1.getString("Quantity"));
                                table1.addCell(rsc1.getString("Designation"));
                                table1.addCell(rsc1.getString("Part_Number"));
                                table1.addCell(rsc1.getString("NSN"));
                            }
                            psc1.close();
                            rsc1.close();

                                    document.add(table1);
                                    document.close();
                            }
                            psc.close();
                        }catch(SQLException ssd){
                                ssd.printStackTrace();
                        }
                        //System.out.println(gid[i]);
                        //jtp.addTab(gid[i]+" Hours.", new JScrollPane(new Template(gid[i],a1)));
                        //Call the function that render the pdfs.

                        break;
                    }
                    else {
                        System.out.println("Report  "+gid[i]);
                            
                                    try{
                            String qe = "select * from Group_Inspection where Location = ? and Group_ID = ?";
                            psc=cn.prepareStatement(qe);
                            psc.setString(1,a1);
                            psc.setString(2,gid[i]);
                            rsc = psc.executeQuery();

                            Font fn1 = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD);
                            Font fn2 = new Font(FontFamily.TIMES_ROMAN,12.0f,Font.NORMAL);

                            while(rsc.next())
                            {
                                document.newPage();
                                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("400.GE"), 500, 800, 0);
                                Paragraph p1 = new Paragraph("Hour "+gid[i],fn1);
                                document.add(p1);

                                Paragraph p2 = new Paragraph("Purpose of job done:- \n",fn1);
                                document.add(p2);

                                Paragraph p3 = new Paragraph(rsc.getString("Purpose")+"\n\n",fn2);
                                document.add(p3);


                                Paragraph p5 = new Paragraph("Brief Description \n",fn1);
                                document.add(p5);

                                Paragraph p6 = new Paragraph(rsc.getString("Brief_Description")+"\n\n",fn2);
                                document.add(p6);

                                Paragraph p7 = new Paragraph("Safety Requirements:- \n",fn1);
                                document.add(p7);

                                Paragraph p8 = new Paragraph(rsc.getString("Safety_Requirements")+"\n\n",fn2);
                                document.add(p8);

                                Paragraph p9 = new Paragraph("Personal & time required :- \n\n",fn1);
                                document.add(p9);

                                Paragraph p11 = new Paragraph("                                                      ",fn1);
                                document.add(p11);

                                PdfPTable table = new PdfPTable(3);
                                // the cell object
                                PdfPCell cell;
                                cell = new PdfPCell();
                                cell.setColspan(3);
                                    table.addCell("Number");
                                    table.addCell("Qualification");
                                    table.addCell("Time_Hr");
                                    //Filling data in table.
                            PreparedStatement psc1 =null;
                            ResultSet rsc1 = null;
                            String qeq = "select * from Time_Required where Hour = ? and System = ?";
                            psc1=cn.prepareStatement(qeq);
                            psc1.setString(1,gid[i]);
                            psc1.setString(2,a1);
                            rsc1 = psc1.executeQuery();
                            while(rsc1.next())
                            {
                                table.addCell(rsc1.getString("Number"));
                                table.addCell(rsc1.getString("Qualification"));
                                table.addCell(rsc1.getString("Time_Hr"));
                            }
                            document.add(table);
                            psc1.close();
                            rsc1.close();

                            Paragraph p10 = new Paragraph("Tools and appliances required :- \n",fn1);
                            document.add(p10);
                            document.add(p11);

                                PdfPTable table1 = new PdfPTable(4);
                                // the cell object
                                PdfPCell cell1;
                                cell1 = new PdfPCell();
                                cell1.setColspan(4);
                                    table1.addCell("Quantity");
                                    table1.addCell("Designation");
                                    table1.addCell("Part Number");
                                    table1.addCell("NSN");
                                    //Filling data in table.
                            String qeq1 = "select * from Tools_Appliances where Hours = ? and System = ?";
                            psc1=cn.prepareStatement(qeq1);
                            psc1.setString(1,gid[i]);
                            psc1.setString(2,a1);
                            rsc1 = psc1.executeQuery();
                            while(rsc1.next())
                            {
                                table1.addCell(rsc1.getString("Quantity"));
                                table1.addCell(rsc1.getString("Designation"));
                                table1.addCell(rsc1.getString("Part_Number"));
                                table1.addCell(rsc1.getString("NSN"));
                            }
                            document.add(table1);
                            psc1.close();
                            rsc1.close();
                            }
                        }catch(SQLException ssd){
                                ssd.printStackTrace();
                        }
                        //jtp.addTab(gid[i]+" Hours.", new JScrollPane(new Template(gid[i],a1)));
                    }

                }
            writer.close();
            } catch (DocumentException e)
                {
                   e.printStackTrace();
                } catch (FileNotFoundException e)
                {
                   e.printStackTrace();
                }


            //Opening a rendered file
                    String path = "Report\\Leeway.pdf";
                    File file = new File(path);
                        /*try {
                            //Desktop.getDesktop().open(file);
                        }catch (IOException exception){
                            exception.printStackTrace();
                        }*/
    }
}
