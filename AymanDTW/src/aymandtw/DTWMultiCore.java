/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymandtw;

import static aymandtw.AymanDTW.TemplateIndecis;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author ayman
 */
public class DTWMultiCore {
    public static ArrayList<Integer>  TemplateIndecis=new ArrayList<Integer>();
    public static AllTemplateofGestures tempalteofGes;
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // TODO code application logic here
        //BufferedWriter BW=new BufferedWriter(new FileWriter("D:\\GateWay\\Class\\Research Group\\2017\\My Work\\AymanDTW\\TestSample4.txt",true));
        File file = new File("D:\\\\GateWay\\\\Class\\\\Research Group\\\\2017\\\\My Work\\\\AymanDTW\\\\TestSample4.txt"); //Your file
FileOutputStream fos = new FileOutputStream(file);
PrintStream ps = new PrintStream(fos);
System.setOut(ps);
        int cores = Runtime.getRuntime().availableProcessors();
        
        System.out.println("Available processor cores is "+cores);
        //cores=1;
        Instant now = Instant.now(); // Start clock at now
        double MaxAccAvg=0;
        int MaxK=0;
        int MaxJ=0;
        int MaxL=0;
        int MaxM=0;
        int MaxCountTrial=0;
        int CountTrials=0;
        //Cross Folding
        for (int l=1;l<=20;l++)
            for (int k=l+1;k<=20;k++)
            {
            for (int j=k+1;j<=20;j++)
            {
                for (int m=j+1;m<=20;m++)
                {
                CountTrials++;
                String S="Trial " +CountTrials + " with L,K,J,M "+l +","+ k + ","+j+ ","+m;
                System.out.println(S);
                //BW.write(S);
                //BW.newLine();
                S="MaxAccuracy so far = " + MaxAccAvg/8 + "Found at K= " + MaxK + " J = " + MaxJ +" L = " + MaxL +" M = " + MaxM +  " Trial " + MaxCountTrial;
                System.out.println(S);
               // BW.write(S);
               // BW.newLine();
                TemplateIndecis.clear();
                TemplateIndecis.add(l);
                TemplateIndecis.add(k);
                TemplateIndecis.add(j);
                TemplateIndecis.add(m);
        
        
       
        tempalteofGes=new AllTemplateofGestures();
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //ExecutorService threadPool = Executors.newWorkStealingPool();
        
        // Each future will have the average of the list of random doubles
        List<Future<Double>> futures = new ArrayList<Future<Double>>(cores);
        
        // Populate the list of futures by submitting callables to the thread pool
        for(int i=0;i<cores;i++) {
            futures.add(i,threadPool.submit(new Worker(i+1))); // non-blocking
        }
        
        Double avg = 0.0;
        for(int i=0;i<cores;i++) {
            // Future::get() is a blocking call until the task is done
            // while we block (wait) on one task to finish the others are still working
            avg += futures.get(i).get();
        }
        S="Average is: "+avg/8;
        System.out.println(S);
        //BW.write(S);
       // BW.newLine();
        if (avg>MaxAccAvg)
        {
            MaxAccAvg=avg;
            MaxJ=j;
            MaxK=k;
            MaxL=l;
            MaxM=m;
            MaxCountTrial=CountTrials;
            S="MaxAccuracy Updated = " + MaxAccAvg/8 + "Found at K= " + MaxK + " J = " + MaxJ +" L = " + MaxL + " MAxM "+MaxM+" trial" + CountTrials;
            System.out.println(S);    
           // BW.write(S);
           // BW.newLine();
        }
       //BW.flush();
        }
       }
      }
        String S="MaxAccuracy = " + MaxAccAvg/8 + "Found at K= " + MaxK + " J = " + MaxJ+ "Max L= "+ MaxL + "Max M= "+ MaxM;
        System.out.println(S);    
       // BW.write(S);
       // BW.newLine();
        Duration d = Duration.between(now, Instant.now());
        System.out.println("Time Taken: "+d); // Total time taken
       // BW.write(""+d);
        //BW.newLine();
        //BW.close();
    }
    
}
class Worker implements Callable<Double> {
    Integer UserId;
    
    /**
     * Size of the list of random numbers.
     * Need to find a "sweet spot" for the memory 
     * your machine has for this to take a decent
     * amount of time and to be able to use memory
     * without hitting the disk.
     */
    
    
    
    // Constructor
    Worker(Integer id) {
        this.UserId = id;
        //this.Alltemplates=Alltemplate;
    }
    
    /**
     * The function that does the work. If you didn't want to 
     * average random numbers, which I'm sure is the case
     * replace this with something meaningful.
     */
    @Override
    public Double call() throws Exception {
        Double avg = 0.0;
        int AllCorrect=0;
        int CountAllLoops=0;
        for (int Gestures=1;Gestures<=20;Gestures++)//Test all gestures
        {
            int CountCorrect=0;
            for (int i=1;i<=20;i++)//loop for all the 16 gestures of gesture 1
            {
             if (!DTWMultiCore.TemplateIndecis.contains(i))   
             {
             CountAllLoops++;
                // System.out.println("I=" + i);
             String filename="U0"+UserId+"\\"+String.format("%02d", Gestures)+"\\"+String.format("%02d", i)+".txt";
             Gesture testgesture=Helper.LoadGesture(filename);
             Recognizer rec;
             rec=new DTW();
             int Recognized=rec.Recognize(testgesture,DTWMultiCore.tempalteofGes);
             //System.out.println("User "+ UserId + " Test Gest " + Gestures + " Sample " + i + " Recognized as "+ Recognized);
             if (Recognized==Gestures)
             {
                 CountCorrect++;
                 AllCorrect++;
             }
            }
            }
            //GesturesAccuracy[Gestures-1]+=CountCorrect;
          //  System.out.println("User "+UserId+" Correct = "+CountCorrect+ " Gesture " +Gestures  +" Accuracy = " + (CountCorrect/(20.0-DTWMultiCore.TemplateIndecis.size()))*100 + "%");
            //avg+=(CountCorrect/(20.0-DTWMultiCore.TemplateIndecis.size()))*100;
        }
        
        avg=((double)AllCorrect/CountAllLoops)*100;
        System.out.println("Average of User " + UserId +" = "+ avg);
        return avg;
    }
}
