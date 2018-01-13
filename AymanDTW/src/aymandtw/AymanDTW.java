/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author ayman.ezzat
 */
public class AymanDTW {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer>  TemplateIndecis=new ArrayList<Integer>();
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // TODO code application logic here
        
        
        
        Instant now = Instant.now(); // Start clock at now
        
        
        
        
        TemplateIndecis.add(1);
        TemplateIndecis.add(9);
        TemplateIndecis.add(17);
        
        
        AllTemplateofGestures tempalteofGes=new AllTemplateofGestures();
       
        double GesturesAccuracy[]=new double[20];
        for (int users=1;users<=8;users++)//Test all gestures
        {
        for (int Gestures=1;Gestures<=20;Gestures++)//Test all gestures
        {
            int CountCorrect=0;
            for (int i=1;i<=20;i++)//loop for all the 16 gestures of gesture 1
            {
             if (!TemplateIndecis.contains(i))   
             {
                // System.out.println("I=" + i);
             String filename="U0"+users+"\\"+String.format("%02d", Gestures)+"\\"+String.format("%02d", i)+".txt";
             Gesture testgesture=Helper.LoadGesture(filename);
             Recognizer rec;
             rec=new DTW();
             int Recognized=rec.Recognize(testgesture, tempalteofGes);
             if (Recognized==Gestures)
             {
                 CountCorrect++;
             }
            }
            }
            GesturesAccuracy[Gestures-1]+=CountCorrect;
            System.out.println("User "+users+ " Gesture " +Gestures  +" Accuracy = " + (CountCorrect/(20.0-TemplateIndecis.size()))*100 + "%");
        }
        }
        double GeneralAccuracy=0;
        double SumAccuracy=0;
        for (int i=1;i<=20;i++)
        {
            GeneralAccuracy=GesturesAccuracy[i-1]/(double)((20.0-TemplateIndecis.size())*8)*100;
            System.out.println("Gesture "+i + " Total Accuracy = "+ GeneralAccuracy) ;
            SumAccuracy+=GeneralAccuracy;
        }
        System.out.println("General Acuracy = " + SumAccuracy/20.0);
        Duration d = Duration.between(now, Instant.now());
        System.out.println("Time Taken: "+d); // Total time taken
        
      //  System.out.println("Gesture Name " + tempalteofGes.AlltemplateofGestures.get(19).TemplateGestures.get(0).Points.get(2).ACCY);
    }
    
}
