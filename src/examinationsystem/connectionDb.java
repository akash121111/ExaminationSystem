/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationsystem;
import java.sql.*;

/**
 *
 * @author Dev
 */
public class connectionDb {
    public static String url="jdbc:derby://localhost:1527/ExaminationSystem";
    public static String driverName="org.apache.derby.jdbc.ClientDriver";
    public static String username="Akash";
    public static String password="Akash";
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    
    public static Connection getConnection(){
        try
        {
            Class.forName(driverName);
            con =DriverManager.getConnection(url, username, password);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
