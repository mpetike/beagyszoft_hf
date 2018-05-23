package hu.bme.mit.battle_city.Network;

 
import java.io.*;
import java.net.*;

import javax.swing.SwingUtilities;

import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;
import hu.bme.mit.battle_city.gui.Menu.PanelId;
import hu.bme.mit.battle_city.GameLogic.RenderObjects;

//Server and blocking read
public class TCPClient implements Runnable {
	
   Menu menuWindow;
   GameField gameField;
   public TCPClientSend clientSend;
   public Thread clientThread;
   String host;
   public TCPClient(Menu mWindow, String hostIP) throws IOException {
	  menuWindow = mWindow;
	  host = hostIP;
	  gameField=(GameField) menuWindow.mPanels.get(PanelId.GAME_FIELD);	  
      
   }


public void run() {
	
         try {
        	System.out.println("Waiting for server to accept ");
            Socket client = new Socket(host,menuWindow.serverPort);
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            
            clientSend = new TCPClientSend(client, gameField,menuWindow);
            clientSend.start();
            
            
            menuWindow.showPanel(PanelId.GAME_FIELD);
            gameField.startGame();
            
            while (!client.isClosed() & menuWindow.gameOn) 
            {
            gameField.gameState = (RenderObjects) in.readObject();
  
    		SwingUtilities.invokeLater(new Runnable() 
    	    {
    	      public void run()
    	      {
    	    	  gameField.updateFrame();
    	      }		
    	    });
            }
         
            client.close();
         }
         catch (ConnectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   
         catch (IOException e) {
            e.printStackTrace();
            
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         finally {
	        menuWindow.showPanel(PanelId.GAME_MODE_SELECTOR); 
	        menuWindow.gameOn = false;
	        menuWindow.clientMode = false;
         		}
      }
   

   public void start () {
	      if (clientThread == null) {
	    	  clientThread = new Thread (this, "clientThread");
	    	  clientThread.start ();
	      }
   
   }
}


