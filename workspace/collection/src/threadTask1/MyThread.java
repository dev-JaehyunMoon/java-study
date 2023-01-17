package threadTask1;

import java.util.ArrayList;

public class MyThread extends Thread {
   
   ArrayList<Thread> list;
   
   public MyThread(ArrayList<Thread> list) {
      this.list = list;
   }
   
   @Override
   public void run() {
      for (int i = 0; i < 10; i++) {
         try {
            list.stream().forEach(t -> System.out.println(t.getName()));
            Thread.sleep(500);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
   
}