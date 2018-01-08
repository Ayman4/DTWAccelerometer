/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ayman.ezzat
 */
public class TemplateGesture {
    String TemplateGestureName;
    Vector <Gesture>TemplateGestures=new Vector <Gesture>() ;
    public TemplateGesture(String name) throws FileNotFoundException, IOException
    {
                
                this.TemplateGestureName=name;
                for (int user=1;user<=8;user++) // loop on all the users and get the first 4 tempaltes for each one of them to  be the template for the gesture
                {
                
                    for (int i=1;i<=6;i++)
                    {
                    Gesture gesture=new Gesture();
                    gesture.GestureName=TemplateGestureName;
                    gesture.UID=""+user;
                   String filename="U0"+user+"\\"+this.TemplateGestureName+"\\"+String.format("%02d", i)+".txt";
                   gesture=Helper.LoadGesture(filename);
                    //NormalizeCurveNew(TemplateX,30);
                     TemplateGestures.add(gesture);
                    }
                 
                }
    }
}
