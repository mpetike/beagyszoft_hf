package hu.bme.mit.battle_city.Network;

import java.io.*;
import java.net.*;
import hu.bme.mit.battle_city.gui.GameField;
import hu.bme.mit.battle_city.gui.Menu;

/**
 * 
 *  TCP server thread, that sends the current game state from the GUI to the client.
 *
 */
public class TCPServerSend implements Runnable {

	Menu menuWindow;
	GameField gameField;
	Socket client;
	ObjectOutputStream out;
	public Thread serverSendThread;
/**
 * 
 * @param connection - client Socket connection object
 * @param gameF - Game Field object
 * @param mWindow - main menu window object
 * @throws IOException
 */
	public TCPServerSend(Socket connection, GameField gameF, Menu mWindow) throws IOException {
		menuWindow = mWindow;
		gameField = gameF;
		client = connection;
		out = new ObjectOutputStream(connection.getOutputStream());

		//
	}
	/**
	 * Waits for a notify from Gamefield, then sends the current gamestate from Gamefield and sends it to the client.
	 */
	public void run() {
		while (!client.isClosed() & menuWindow.gameOn) {
			try {
				synchronized (this) {
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

	public void start() {
		if (serverSendThread == null) {
			serverSendThread = new Thread(this, "serverSend");
			serverSendThread.start();
		}

	}
}