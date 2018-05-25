package hu.bme.mit.battle_city.Network;

import java.io.*;
import java.net.*;
import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;

/**
 * 
 *  TCP client thread, that send keyboard inputs from the GUI to the server.
 *
 */
public class TCPClientSend implements Runnable {

	Menu menuWindow;
	GameField gameField;
	Socket client;
	DataOutputStream out;
	public Thread clientSendThread;

	public TCPClientSend(Socket connection, GameField gameF, Menu mWindow) throws IOException {
		menuWindow = mWindow;
		gameField = gameF;
		client = connection;
		out = new DataOutputStream(connection.getOutputStream());

	}
	/**
	 * Waits for a notify from Gamefield, then sends a key from the userInput Queue to the server.
	 */
	public void run() {
		while (!client.isClosed() & menuWindow.gameOn) {
			try {
				synchronized (this) {
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

	public void start() {
		if (clientSendThread == null) {
			clientSendThread = new Thread(this, "serverSend");
			clientSendThread.start();
		}

	}
}