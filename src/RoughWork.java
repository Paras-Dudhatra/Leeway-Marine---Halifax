
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHD
 */
public class RoughWork {
    
    public static void main(String[] arg)
    {
        RoughWork rw = new RoughWork();
    }
    
    public RoughWork()
    {
        
        try{
       Connect ca = new Connect();
       Connection cn = ca.getConnection();
       PreparedStatement psc = null;
       ResultSet rsc = null;
       
            String qe = "select * from Group_Inspection";

            psc=cn.prepareStatement(qe);
                rsc = psc.executeQuery();
                while(rsc.next())
                {
                    System.out.println(rsc.getString("Group_ID")+" \n "+rsc.getString("Location")+"  \n"+rsc.getString("Attention_Required")+"\n");
                }
                psc.close();
        }catch(SQLException sd){
            sd.printStackTrace();
        }
    }
    
}
