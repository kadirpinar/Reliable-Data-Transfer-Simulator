/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdt;

import java.util.Scanner;

/**
 *
 * @author Kadir
 */
public class RDT {

  static  int[] array = new int[15];
    static  int[] arrayy = new int[15];

    public int x;
    public static int Error_rate; 
    public static int count=0;
    public static float R=1000000;
    public static float L=8000;
    public static float RTT=30;
    public static int seq=0;
public static void  frame(int x){
        count+=1;
        System.out.println("rdt_send(data):|\\  |");
        System.out.println("               | \\ |");
        System.out.println("               |  \\|rdt_rcv(packet) packet:"+x);    
}


public static void  framee(int x){
        count+=1;
        System.out.println("rdt_send(data):|\\  |");
        System.out.println("sequence:"+seq%2+"     | \\ |");
        System.out.println("               |  \\|rdt_rcv(packet) packet:"+x);    
}

public static void  frameee(int x){
        count+=1;
        System.out.println("rdt_send(data):|\\  |");
        System.out.println("               | X |");
        System.out.println("               |   |rdt_rcv(packet) packet:"+x);  
        waitt();
        frame(x);
}

public static int  ack(int x){
        int a = (int)(Math.random()*100+1);
        if( a<x){
        timeout();
return 1; 
        }
        seq+=1;
        System.out.println("               |  /|:ACK");
        System.out.println("               | / |");
        System.out.println("               |/  |");    
        return 0;
}
public static void  timeout(){
        System.out.println("               |  /|:NACK");
        System.out.println("               | / |");
        System.out.println("               |/  |");    
        
}

public static int  ackk(int x,int y){
        int a = (int)(Math.random()*100+1);
        if( a<x){
        timeoutt(y);
return 1; 
        }
        seq+=1;
        System.out.println("               |  /|:ACK seq:"+y);
        System.out.println("               | / |");
        System.out.println("               |/  |");    
        return 0;
}
public static void  timeoutt(int x){
        if (x==1){
        x+=1;
        }
        System.out.println("               |  /|:ACK seq:"+(x-1));
        System.out.println("               | / |");
        System.out.println("               |/  |");    
        
}




public static void  waitt(){
        System.out.println("timeout        |   |");
        System.out.println("               |   |");
        System.out.println("               |   |");
}
    public static void main(String[] args) {
      
        System.out.println("Protokol tipini seçiniz \n1)Rdt 1.0\n2)Rdt 2.0\n3)Rdt 2.1\n4)Rdt 2.2\n5)Rdt 3.0");  
        Scanner scan= new Scanner(System.in);
        int input = scan.nextInt();
        if (!(input==1)){
        System.out.println("Hata oranını yazınız ");  
        Error_rate = scan.nextInt();
        }
       
        System.out.println("Gönderilecek paket sayısını giriniz ");  
        int Frame = scan.nextInt();
        
        
        switch(input){
            case 1: 
                for(int i=1;i<=Frame;i++){
                int x = i;
                frame(x);

               if(ack(0)==1){
                i--;
                }        
                }
                System.out.printf("Throughput: %.2f ",(float)Frame/count);
                System.out.printf("Utilization: %.6f",(float)((L/R)/(RTT+L/R)) );
                break;
                
             case 2: 
                for(int i=1;i<=Frame;i++){
                int x = i;
                frame(x);

               if(ack(Error_rate)==1){
                i--;
                }        
                }
                System.out.printf("Throughput: %.2f ",(float)Frame/count);
                System.out.printf("Utilization: %.6f",(float)((L/R)/(RTT+L/R)) );
                break;
            case 3: 
                for(int i=1;i<=Frame;i++){
                int x = i;
                framee(x);

               if(ack(Error_rate)==1){
                i--;
                }        
                }
                System.out.printf("Throughput: %.2f ",(float)Frame/count);
                System.out.printf("Utilization: %.6f",(float)((L/R)/(RTT+L/R)) );
                break;
                
             case 4: 
                for(int i=1;i<=Frame;i++){
                int x = i;
                frame(x);

               if(ackk(Error_rate,i)==1){
                i--;
                }        
                }
                System.out.printf("Throughput: %.2f ",(float)Frame/count);
                System.out.printf("Utilization: %.6f",(float)((L/R)/(RTT+L/R)) );
                break;
                
             case 5: 
               for(int i=1;i<=Frame;i++){
                int x = i;
                int a = (int)(Math.random()*100+1);
                if(a<Error_rate){
                frame(x);
                }
                else{
                frameee(x);
                }
               if(ackk(Error_rate,i)==1){
                i--;
                }        
                }
                System.out.printf("Throughput: %.2f ",(float)Frame/count);
                System.out.printf("Utilization: %.6f",(float)((L/R)/(RTT+L/R)) );
                break;
            
            
        }
        
       
       
        // TODO code application logic here
    }
    
}
