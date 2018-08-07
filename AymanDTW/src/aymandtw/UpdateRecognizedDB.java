/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymandtw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author ayman
 */
public class UpdateRecognizedDB implements Runnable{

    public String RecognizedGestId;
        public    String TripId=CheckDB.LastTripID;

    
    @Override
    public void run() {
        try
        {
        String SaveRecordURL="http://www.hciegypt.com/main/record/Update.php";
       
        URL url = new URL(SaveRecordURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        OutputStream out = con.getOutputStream();
        
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            String Post_data = URLEncoder.encode("RecognizedGestId", "UTF-8") + "=" + URLEncoder.encode(RecognizedGestId, "UTF-8")+"&";
            Post_data+=URLEncoder.encode("TripId", "UTF-8") + "=" + URLEncoder.encode(TripId, "UTF-8");
            bf.write(Post_data);
            System.out.println("Called the Update " + Post_data);
            bf.flush();
            bf.close();
            out.close();
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            String line="";
            while ((line=br.readLine())!=null)
            {
                System.out.println("Line= " + line);

            }
            br.close();
            is.close();
            
            con.disconnect();
            
        }
        catch (Exception e)
        {
            System.out.println("e"+e.getMessage());
        }
    }
    
}
