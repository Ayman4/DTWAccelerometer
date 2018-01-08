/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ayman.ezzat
 */
public class AllTemplateofGestures {
      Vector <TemplateGesture>AlltemplateofGestures=new Vector <TemplateGesture>() ;
      public AllTemplateofGestures() throws IOException
      {
          for (int i=1;i<=20;i++)
          {
              TemplateGesture tempalteofGes=new TemplateGesture(String.format("%02d", i));
              AlltemplateofGestures.add(tempalteofGes);
          }
      }
}
