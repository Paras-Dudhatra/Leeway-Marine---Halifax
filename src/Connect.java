import java.sql.*;

class Connect
{
	public static void main(String[] args)
	{
	Connect cn = new Connect();
        Connection cx = cn.getConnection();
        //System.out.println("Connected Success");
	}

	public Connect()
	{
	//System.out.println("In Connect");
	}
	
	public Connection getConnection()
	{
		Connection conn=null;
		try{
   String connectString = "jdbc:ucanaccess://" + "ProgramData/Leeway.accdb";
   Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    conn = DriverManager.getConnection(connectString);
		//System.out.println("DB Created..");
		}catch(ClassNotFoundException | SQLException as){
		}
	return conn;
	}

	public Connection getConnectionL1()
	{
		Connection conn=null;
		try{
   String connectString = "jdbc:ucanaccess://" + "ProgramData/Leeway_1.accdb";
   Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    conn = DriverManager.getConnection(connectString);
		//System.out.println("DB Created..");
		}catch(ClassNotFoundException | SQLException as){
		}
	return conn;
	}
        
        public String fillDescription(String hrs)
        {
            String x = " ";
                        try{

            Connect c = new Connect();
            Connection cn = c.getConnection();
            PreparedStatement psc = null;
            ResultSet rsc = null;

            //jComboBox1.removeAllItems();

            String qe = "select * from Hours_Description where Hours = ?";

            psc=cn.prepareStatement(qe);
            psc.setString(1,hrs);
                rsc = psc.executeQuery();
                while(rsc.next())
                {
                    x = rsc.getString("Description");
                }
                psc.close();
                //System.out.println("Retrived -  "+jComboBox1.getSelectedItem());
            }catch(SQLException se){
                se.printStackTrace();
                System.out.println("Error...2");
            }
         return x;
        }
}