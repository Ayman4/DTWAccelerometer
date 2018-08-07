/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymandtw;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayman
 */
public class GrabData implements Runnable{

    @Override
    public void run() {
        try
        {
            String filename="D:\\GateWay\\Class\\Research Group\\2017\\My Work\\gestures-dataset\\gestures-dataset\\Test.txt";
            PrintWriter pw=new PrintWriter(new File(filename));
            
            if (CheckDB.LastTripID!=null)
            {
            
            URL l = new URL("http://www.hciegypt.com/main/record/Result.php?TripId="+CheckDB.LastTripID+"&GestureId="+CheckDB.LastGesture);
           
             Scanner xn=new Scanner(l.openStream());
                  //String L="";
                while (xn.hasNext())
                {
                    
                   String Line=xn.nextLine();
                   //String NewLine[]=Line.split("<br>");
                   System.out.println("Grabed line: "+Line);
                   String NewLine=Line.replace("<br>", "\r\n");
                   pw.write(NewLine);
                   //L+=Line; 
                }
                   
                
//                String x[]=L.split(",");
//                CheckDB.LastTripID=x[0];
//                CheckDB.LastGesture=x[1];
                xn.close();
                pw.close();
                TestDTWMultiCore.Start();
                
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(GrabData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GrabData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GrabData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(GrabData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
