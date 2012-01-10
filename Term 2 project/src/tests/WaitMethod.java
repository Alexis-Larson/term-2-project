package tests;
public class WaitMethod extends Thread{
  String st="Hello";
  public static void main(String args[])  {
    WaitMethod wait=new WaitMethod();
    wait.start();
    new Example(wait);
  }
  public void run(){
    try{
    synchronized(this)
    {
    	wait();
    	System.out.println("Message is changed to :"+st);    
    }
    }catch(Exception e){}
 }
  public void valchange(String st){    
    this.st=st;    
    try {
    synchronized(this){
    notifyAll();   
    }
    }catch(Exception e){}
  }
}
class Example extends Thread{
  WaitMethod wait;
  Example(WaitMethod wait)  {
  this.wait=wait;
  start();
  }
  public void run(){
   try{
    System.out.println("Message is : "+wait.st);
    wait.valchange("Hello World");
  }catch(Exception e){}
  }
}
    