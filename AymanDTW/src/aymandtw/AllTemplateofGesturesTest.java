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
public class AllTemplateofGesturesTest {
      Vector <TemplateGestureTest>AlltemplateofGestures=new Vector <TemplateGestureTest>() ;
      public int userId;
      public AllTemplateofGesturesTest(int userId) throws IOException
      {
          for (int i=1;i<=20;i++)
          {
              TemplateGestureTest tempalteofGes=new TemplateGestureTest(String.format("%02d", i),userId);
              AlltemplateofGestures.add(tempalteofGes);
          }
      }
}
