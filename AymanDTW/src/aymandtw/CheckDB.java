/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymandtw;

/**
 *
 * @author ayman
 */
public class CheckDB {
    static String LastTripID;
    static String LastGesture;
    static String PreviousTripId;
    public static void main(String[] args)
    {
        PreviousTripId=new String("");
        LastTripID=new String ("");
        while (true)
        {
        try
        {
        LookForUpdatesInDB Look=new LookForUpdatesInDB();
        Thread x=new Thread(Look);
        x.start();
        Thread.sleep(3000);
        GrabData Grabber=new GrabData();
        Thread y=new Thread(Grabber);
        y.start();
        y.sleep(3000);
        x.sleep(2000);
        //x.notifyAll();
        }
        catch (Exception e)
        {
             System.out.println(e.getMessage());
        }
        }
    }
    
}
