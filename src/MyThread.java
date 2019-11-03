import org.omg.IOP.TAG_JAVA_CODEBASE;
import sun.awt.windows.ThemeReader;

public class MyThread extends  Thread{
  public static class P1 extends Thread{
      public P1(){

      }
      public P1(String name){
          super(name);
      }
      private  int count = 100000;

      @Override
      public void run() {
          long sum = 0;
          for(int i = 1; i<=count;i++){
              sum+=i;
          }
          System.out.println("p1:"+sum);
      }
  }
  public static class P2 extends Thread{
      private int count = 100000;

      @Override
      public void run() {
          long sum = 0;
          for(int i = 1 ;i<count ;i+=2){
              sum+=i;
          }
          System.out.println("p2:"+sum);
      }
  }
  public static class P3 extends Thread{
      private int count = 100000;

      @Override
      public void run() {
          long sum = 0;
          for(int i = 1 ; i<= count ;i++){
              sum+=Math.pow(i,2);
          }
          System.out.println("p3:"+sum);
      }
  }

  public static int cal(int count){
      int sum = 0;
      for(int i = 1 ; i<=count ;i++){
          sum+=i;
      }
      return sum;
  }
    public static void main(String[] args) throws InterruptedException {
        long begin =System.currentTimeMillis();
        Thread p1 = new P1();
        p1.start();
        Thread p2 = new P2();
        p2.start();
        Thread p3 = new P3();
        p3.start();
        p1.join();
        p2.join();
        p3.join();
        long end =System.currentTimeMillis();
        System.out.println((end - begin) *1.0 /1000);
        long b = System.currentTimeMillis();
        int  x =cal(100000);
        long e = System.currentTimeMillis();
        System.out.println(e-b);
    }
}
