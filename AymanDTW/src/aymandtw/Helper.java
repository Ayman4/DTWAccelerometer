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
    
                public static  Gesture LoadGesture(String filename) throws FileNotFoundException, IOException
                {
                  Gesture gesture=new Gesture();
                       
                   

                   //     Vector <Double>TemplateX=new Vector <Double>() ;
                     BufferedReader Bf=new BufferedReader(new FileReader("D:\\GateWay\\Class\\Research Group\\2017\\My Work\\gestures-dataset\\gestures-dataset\\"+filename));
                  // BufferedReader Bf=new BufferedReader(new FileReader("D:\\GateWay\\Research Group\\2017\\My Work\\DTW_NEW\\gestures-dataset\\gestures-dataset\\"+filename));
                   String Line=Bf.readLine();
                    while (Line!=null)
                    {
                            String OneRow[];
                            OneRow = Line.split(" |,");
                           
                            //Working on X and Y only of Accel
                            int r1,r2,r3;
                            if (OneRow.length==6)
                            {
                                //System.out.println("Reading Normal");
                                r1=3;
                                r2=4;
                                r3=5;
                            }
                            else
                            {
                                //System.out.println("Test Data");
                                r1=0;
                                r2=1;
                                r3=2;
                            }
                            Double AccX=Double.parseDouble(OneRow[r1]);
                            Double AccY=Double.parseDouble(OneRow[r2]);
                            Double AccZ=Double.parseDouble(OneRow[r3]);    
                            //TemplateX.add(Math.sqrt(AccX*AccX+AccY*AccY));
                            //TemplateX.add((Math.sqrt(AccX*AccX+AccY*AccY+AccZ*AccZ)));
                            
                            gesture.Points.add(new AccPoint(AccX,AccY,AccZ));
                                
                            Line=Bf.readLine();
                    }
                    Bf.close();
                    return gesture;
                }
}
