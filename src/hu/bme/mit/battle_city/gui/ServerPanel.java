package hu.bme.mit.battle_city.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import hu.bme.mit.battle_city.Network.TCPServer;
import hu.bme.mit.battle_city.Network.TCPServerSend;
import hu.bme.mit.battle_city.gui.Menu.PanelId;

public class ServerPanel extends MenuPanel {


	private static final long serialVersionUID = -7806073092005970911L;

	/**
	 * show my ip, button start server-wait for client loop
	 * start gamefield
	 * back button
	 * @param menu 
	 */
	private JButton mBtnStart;
	private JButton mBtnBack;
	private JLabel l1;  

	public ServerPanel(Menu menuWindow) {
			super(menuWindow);
			
			
			
			mBtnStart = new JButton("Start server");
			mBtnStart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					l1.setText("Waiting for client..."); 
					try {
						menuWindow.server = new TCPServer(menuWindow);
						menuWindow.server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});		
			setLayout(null);
		    l1=new JLabel("My IP");
	        try{  

	            String ip=java.net.InetAddress.getLocalHost().getHostAddress();  
	            l1.setText("Server IP is: "+ip);  
	            }catch(Exception ex){System.out.println(ex);}  
 
			mBtnBack = getBackButton(PanelId.MULTIPLAYER_PANEL);
			
		    l1.setBounds(170,150, 300,30); 
		    mBtnStart.setBounds(150, 250, 250, 50);
			mBtnBack.setBounds(150, 450, 250, 50);
			
			Font bSize18 = new Font("Arial", Font.PLAIN, 18);
			l1.setFont(bSize18);
			mBtnStart.setFont(bSize18);
			mBtnBack.setFont(bSize18);
			add(l1);
			add(mBtnStart);
			add(mBtnBack);
	}

}
