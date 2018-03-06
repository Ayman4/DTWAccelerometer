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
                            
                            gesture.Points.add(new AccPoint(Math.sqrt(AccX*AccX+AccY*AccY+AccZ*AccZ),AccY,AccZ));
                            //gesture.Points.add(new AccPoint(Math.sqrt(AccY*AccY+AccX*AccX),0.0,0.0));
                            //gesture.Points.add(new AccPoint(AccX,AccY,AccZ));
                                
                            Line=Bf.readLine();
                    }
                    Bf.close();
                    NormalizeCurveNew(gesture.Points,140);
                    return gesture;
                }
      public static void NormalizeCurveNew(Vector<AccPoint> Curve, int count)
        {
            int points = count / 2 + 1;

            if (points > Curve.size())
                ExpandPoints(Curve, points);
            else
                ReducePoints(Curve, points);
        }
        //Reducing Part
        public static int ReducePoints(Vector<AccPoint> points, int count)
        {
            int index;
            int excessive_points = points.size() - count;

            for (int i = 0; i < excessive_points; i++)
            {
                index = GetFirstIndexMinSegment(points);

                AddMidPoint(points, index);
                RemovePoint(points, index + 2);
                RemovePoint(points, index);
            }

            return excessive_points;
        }
        public static int GetFirstIndexMinSegment(Vector<AccPoint> points)
        {
            double[] lengths = new double[points.size() - 1];
            lengths = GetSegmentsLengths(points);

            int ret = 0;
            for (int i = 1; i < lengths.length; i++)
            {
                if (lengths[ret] > lengths[i])
                    ret = i;
            }

            return ret;
        }
        
         public static void RemovePoint(Vector<AccPoint>  points, int index)
        {
            if (index == 0) //If index is start, then removes next one
                points.remove(1);
            else if (index == points.size() - 1)	//If index is the last, remove the earlier
                points.remove(points.size() - 2);
            else
                points.remove(index);
        }
        //Expanding Part
        
        public static int ExpandPoints(Vector<AccPoint> points, int count)
        {

            int index;
            int gap_points = count - points.size();

            for (int i = 0; i < gap_points; i++)
            {
                index = GetFirstIndexMaxSegment(points);
                AddMidPoint(points, index);
            }

            return gap_points;
        }
        public static void AddMidPoint(Vector<AccPoint> points, int index)
        {
            AccPoint mid_point=new AccPoint((points.get(index).ACCX+points.get(index+ 1).ACCX)/2, Double.NaN, Double.NaN);
            points.add(index + 1, mid_point);
        }
        public static int GetFirstIndexMaxSegment(Vector<AccPoint> points )
        {
            double[] lengths = new double[points.size() - 1];
            lengths = GetSegmentsLengths(points);
            int ret = 0;
            for (int i = 1; i < lengths.length; i++)
            {
                if (lengths[ret] < lengths[i])
                    ret = i;
            }

            return ret;
        }
        public static double[] GetSegmentsLengths(Vector<AccPoint>  points)
        {
            double[] vect = new double[points.size() - 1];

            for (int i = 0; i < points.size() - 1; i++)
                vect[i] = Math.abs(points.get(i).ACCX-points.get(i + 1).ACCX);

            return vect;
        }
}
