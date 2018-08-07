/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymandtw;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayman
 */
public class LookForUpdatesInDB implements Runnable{

   /* private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    */
    
    @Override
    public void run() {
        try
        {
        URL l = new URL("http://www.hciegypt.com/main/record/Result.php");
           
             Scanner xn=new Scanner(l.openStream());
                  String L="";
                  System.out.println("Look for Trip"+CheckDB.LastTripID);
                while (xn.hasNext())
                {
                    
                   String Line=xn.nextLine();
                   Line=Line.substring(0,Line.length() -4);
                   L+=Line; 
                }
                   
               // System.out.println(L);
                String x[]=L.split(",");
                
                if (CheckDB.LastTripID.equals(CheckDB.PreviousTripId))
                {
                    CheckDB.LastTripID=x[0];
                    
                }
                else
                {
                    CheckDB.PreviousTripId=CheckDB.LastTripID;
                }
                CheckDB.LastGesture=x[1];
                xn.close();
                //this.notifyAll();
//       Class.forName("com.mysql.jdbc.Driver");
//            // Setup the connection with the DB
//       String url = "jdbc:mysql://hciegyptcom.ipagemysql.com:3306/recsens";
//           Class.forName ("com.mysql.jdbc.Driver");
//           connect = DriverManager.getConnection (url,"ayman","OmarJanaNour");     
//       
//       
//      
//            // Statements allow to issue SQL queries to the database
//            statement = connect.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM  Gestures WHERE GestureId ='NA' AND TripId =  '2018-03-06-07-28-01'");
//            while (resultSet.next()) {
//                String user = resultSet.getString("GestureId");
//            }
        }  catch (MalformedURLException ex) {
            Logger.getLogger(LookForUpdatesInDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LookForUpdatesInDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

