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
public class TemplateGestureTest {
    public String TemplateGestureName;
    public Vector <Gesture>TemplateGestures=new Vector <Gesture>() ;
   
    public TemplateGestureTest(String name,int user) throws FileNotFoundException, IOException
    {
                
                this.TemplateGestureName=name;
                 for (int i=1;i<=20;i++)
                    {
                       if (TestDTWMultiCore.TemplateIndecis.contains(i))
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
