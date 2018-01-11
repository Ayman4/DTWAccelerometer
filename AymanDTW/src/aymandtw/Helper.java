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

/**
 *
 * @author ayman.ezzat
 */
public class Helper {
    
                public static Gesture LoadGesture(String filename) throws FileNotFoundException, IOException
                {
                  Gesture gesture=new Gesture();
                       
                   

                   //     Vector <Double>TemplateX=new Vector <Double>() ;
                  //   BufferedReader Bf=new BufferedReader(new FileReader("D:\\GateWay\\Class\\Research Group\\2017\\My Work\\gestures-dataset\\gestures-dataset\\"+filename));
                   BufferedReader Bf=new BufferedReader(new FileReader("D:\\GateWay\\Research Group\\2017\\My Work\\DTW_NEW\\gestures-dataset\\gestures-dataset\\"+filename));
                   String Line=Bf.readLine();
                    while (Line!=null)
                    {
                            String OneRow[]=Line.split(" ");
                            //Working on X and Y only of Accel

                            {
                            Double AccX=Double.parseDouble(OneRow[3]);
                            Double AccY=Double.parseDouble(OneRow[4]);
                            Double AccZ=Double.parseDouble(OneRow[5]);    
                            //TemplateX.add(Math.sqrt(AccX*AccX+AccY*AccY));
                            //TemplateX.add((Math.sqrt(AccX*AccX+AccY*AccY+AccZ*AccZ)));
                            gesture.Points.add(new AccPoint(AccX,AccY,AccZ));
                            }    
                            Line=Bf.readLine();
                    }
                    Bf.close();
                    return gesture;
                }
}
