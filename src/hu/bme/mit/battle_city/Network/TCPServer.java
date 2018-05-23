package hu.bme.mit.battle_city.Network;

 
import java.io.*;
import java.net.*;
import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;
import hu.bme.mit.battle_city.gui.Menu.PanelId;
 
//Server and blocking read
public class TCPServer implements Runnable {
	
   public ServerSocket serverSocket;
   Menu menuWindow;
   GameField gameField;
   public TCPServerSend serverSend;
   public Thread serverThread;
   public TCPServer(Menu mWindow) throws IOException {
	  menuWindow = mWindow;
	  gameField=(GameField) menuWindow.mPanels.get(PanelId.GAME_FIELD);	  
      serverSocket = new ServerSocket(menuWindow.serverPort);
      

      // 
   }

   public void run() {
      
         try {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket conn = serverSocket.accept();  

            serverSend = new TCPServerSend(conn, gameField,menuWindow);
            serverSend.start();

            menuWindow.showPanel(PanelId.GAME_FIELD);
            gameField.startGame();
            
            System.out.println("Just connected to " + conn.getRemoteSocketAddress());
          
            DataInputStream in = new DataInputStream(conn.getInputStream());
            
            while (!conn.isClosed() & menuWindow.gameOn) 
            {
            int read=in.readInt(); 
            System.out.println(read);
            
			gameField.clientInput.add(read);
            }
            serverSocket.close();
            conn.close();
          
         } catch (IOException e) {
            e.printStackTrace();
         }
         try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         menuWindow.showPanel(PanelId.GAME_FIELD);
         menuWindow.gameOn = false;
         gameField.gameEngine.GameOver = true;
}
   


   public void start () {
	      if (serverThread == null) {
	    	  serverThread = new Thread (this, "serverThread");
	    	  serverThread.start ();
	      }
   
   }
}