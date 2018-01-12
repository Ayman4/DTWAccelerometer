/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import java.time.Instant;
import java.util.Vector;
import java.util.concurrent.Callable;

/**
 *
 * @author ayman.ezzat
 */
public class DTW implements Recognizer 
{
            
    @Override
    public int Recognize(Gesture Test, AllTemplateofGestures template) {
                    
        
                    int result=-1;
        
                    double MinValue=99999;
                    int MinIndex=0;
                    for (int i=0;i< template.AlltemplateofGestures.size();i++)// loop on all gestures
                    {
                        for (int j=0;j<template.AlltemplateofGestures.get(i).TemplateGestures.size();j++) // loop on each gesture templates
                        {
                        DTWoriginal dtw = new DTWoriginal(Test.Points, template.AlltemplateofGestures.get(i).TemplateGestures.get(j).Points);
                    
                        if (dtw.warpingDistance<MinValue)
                        {
                            MinValue=dtw.warpingDistance;
                            MinIndex=i;
//                            GestureIndex=GesturesDS;
//                            userIndex=user;
                        }
                        }
                   }
                 // System.out.println("Gesture is "+ (MinIndex+1) + " Min distance is " + MinValue);
                  result=MinIndex+1;
  return result;
    }

  
}