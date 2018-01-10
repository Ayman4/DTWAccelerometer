/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import java.io.IOException;

/**
 *
 * @author ayman.ezzat
 */
public class AymanDTW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AllTemplateofGestures tempalteofGes=new AllTemplateofGestures();
        //System.out.println("Size " + tempalteofGes.AlltemplateofGestures.size());
        double GesturesAccuracy[]=new double[20];
        for (int users=1;users<=8;users++)//Test all gestures
        {
        for (int Gestures=1;Gestures<=20;Gestures++)//Test all gestures
        {
            int CountCorrect=0;
            for (int i=5;i<=20;i++)//loop for all the 16 gestures of gesture 1
            {
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
            GesturesAccuracy[Gestures-1]+=CountCorrect;
            System.out.println("User "+users+ " Gesture " +Gestures  +" Accuracy = " + (CountCorrect/16.0)*100 + "%");
        }
        }
        for (int i=1;i<=20;i++)
        {
            System.out.println("Gesture "+i + " Total Accuracy = "+ GesturesAccuracy[i-1]/(double)(16*8)*100);
        }
        
        
      //  System.out.println("Gesture Name " + tempalteofGes.AlltemplateofGestures.get(19).TemplateGestures.get(0).Points.get(2).ACCY);
    }
    
}
