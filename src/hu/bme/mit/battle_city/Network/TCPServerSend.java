package hu.bme.mit.battle_city.Network;


import java.io.*;
import java.net.*;
import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;
 
//Server and blocking read
public class TCPServerSend implements Runnable {
	
   Menu menuWindow;
   GameField gameField;
   Socket client;
   ObjectOutputStream out;
   public Thread serverSendThread;
   public TCPServerSend( Socket connection,GameField gameF, Menu mWindow) throws IOException {
	  menuWindow=mWindow;
	  gameField=gameF;	  
      client = connection;
 	  out = new ObjectOutputStream(connection.getOutputStream());

      // 
   }
   public void run() {
      while(!client.isClosed() & menuWindow.gameOn) {
         try {
        	 synchronized (this) 
        	 { 
        		 wait();
        	 }

         	out.reset();
            out.writeObject(gameField.gameState);
 
            
         } catch (IOException e) {
            e.printStackTrace();
            
         } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
      }
   }
   
   public void start () {
	      if (serverSendThread == null) {
	    	  serverSendThread = new Thread (this, "serverSend");
	    	  serverSendThread.start ();
	      }
   
   }
}