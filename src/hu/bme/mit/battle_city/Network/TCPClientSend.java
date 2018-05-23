package hu.bme.mit.battle_city.Network;


import java.io.*;
import java.net.*;
import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;
 
//Server and blocking read
public class TCPClientSend implements Runnable {
	
   Menu menuWindow;
   GameField gameField;
   Socket client;
   DataOutputStream out;
   public Thread clientSendThread;
   public TCPClientSend( Socket connection,GameField gameF) throws IOException {
	   
	  gameField=gameF;	  
      client = connection;
 	  out = new DataOutputStream(connection.getOutputStream());

      // 
   }
   public void run() {
      while(true) {
         try {
        	 synchronized (this) 
        	 { 
        		 wait();
        	 }
   
        	 Integer key = gameField.userInput.poll(); 
             out.writeInt(key);
 
            
         } catch (IOException e) {
            e.printStackTrace();
            break;
         } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }
   
   public void start () {
	      if (clientSendThread == null) {
	    	  clientSendThread = new Thread (this, "serverSend");
	    	  clientSendThread.start ();
	      }
   
   }
}