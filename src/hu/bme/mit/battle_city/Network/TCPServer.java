package hu.bme.mit.battle_city.Network;

 
import java.io.*;
import java.net.*;
import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;
import hu.bme.mit.battle_city.gui.Menu.PanelId;
 
//Server and blocking read
public class TCPServer implements Runnable {
	
   private ServerSocket serverSocket;
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
      while(true) {
         try {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket conn = serverSocket.accept();  // ez alatt wait for client vagy valami
            
            serverSend = new TCPServerSend(conn, gameField);
            serverSend.start();

            menuWindow.showPanel(PanelId.GAME_FIELD);
            gameField.startGame();
            
            System.out.println("Just connected to " + conn.getRemoteSocketAddress());
          
            DataInputStream in = new DataInputStream(conn.getInputStream());
            
            while (true) // while client is connected
            {
            // server csak bill inputot kap és csak gamestatet küld és fordítva a kliensnél
            int read=in.readInt(); // 4byte (= int) kiolvasás,ha jött adat, ha nem akkor vár
            System.out.println(read);
            
			gameField.clientInput.add(read);
            }
 
            
           // server.close();
            
         } catch (SocketTimeoutException s) {   // client closed exception ide
            System.out.println("Socket timed out!");
            break;
         } catch (IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }

   public void start () {
	      if (serverThread == null) {
	    	  serverThread = new Thread (this, "serverThread");
	    	  serverThread.start ();
	      }
   
   }
}