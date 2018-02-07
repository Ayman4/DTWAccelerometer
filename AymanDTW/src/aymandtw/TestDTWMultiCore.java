/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import static aymandtw.DTWMultiCore.TemplateIndecis;
import static aymandtw.DTWMultiCore.tempalteofGes;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author ayman.ezzat
 */
public class TestDTWMultiCore {
     public static ArrayList<Integer>  TemplateIndecis=new ArrayList<Integer>();
   
   
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException
    {
      int cores = Runtime.getRuntime().availableProcessors();
        
        System.out.println("Available processor cores is "+cores);
        //cores=1;
        Instant now = Instant.now(); // Start clock at now
         TemplateIndecis.clear();
                TemplateIndecis.add(1);
                TemplateIndecis.add(9);
                TemplateIndecis.add(4);
                TemplateIndecis.add(17);
           
            //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //ExecutorService threadPool = Executors.newWorkStealingPool();
        
        // Each future will have the average of the list of random doubles
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>(cores);
        
        // Populate the list of futures by submitting callables to the thread pool
        for(int i=0;i<cores;i++) {
            futures.add(i,threadPool.submit(new Worker2(i+1))); // non-blocking
        }
        
       List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<cores;i++) {
            // Future::get() is a blocking call until the task is done
            // while we block (wait) on one task to finish the others are still working
            //avg = ;
            list.add(futures.get(i).get());
        }
         Duration d = Duration.between(now, Instant.now());
        System.out.println("Time Taken: "+d); // Total time taken
        
        System.out.println("\nExample 3 - Count all with Map");
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	for (Integer temp : list) {
		Integer count = map.get(temp);
		map.put(temp, (count == null) ? 1 : count + 1);
	}
	printMap(map);
       Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        
	System.out.println("\n Max");
	//Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(map);
        
	       System.out.println("value"+maxEntry.getKey());
        

  }

  public static void printMap(Map<Integer, Integer> map){

	for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
		System.out.println("Key : " + entry.getKey() + " Value : "
			+ entry.getValue());
	}
        
    }
}
 class Worker2 implements Callable<Integer> {
    Integer UserId;
     public static AllTemplateofGesturesTest tempalteofGes;
    /**
     * Size of the list of random numbers.
     * Need to find a "sweet spot" for the memory 
     * your machine has for this to take a decent
     * amount of time and to be able to use memory
     * without hitting the disk.
     */
    
    
    
    // Constructor
    Worker2(Integer id) {
        this.UserId = id;
        //this.Alltemplates=Alltemplate;
    }
    
    /**
     * The function that does the work. If you didn't want to 
     * average random numbers, which I'm sure is the case
     * replace this with something meaningful.
     */
    @Override
    public Integer call() throws Exception {
       
        tempalteofGes=new AllTemplateofGesturesTest(this.UserId);
           
            String filename="U0"+1+"\\03\\05.txt";
             Gesture testgesture=Helper.LoadGesture(filename);
             Recognizer rec;
             rec=new DTW();
             Integer Recognized=rec.Recognize(testgesture,tempalteofGes);
             System.out.println("User ID= "+this.UserId+", Rec ="+ Recognized);
        return Recognized;
    }
}   

