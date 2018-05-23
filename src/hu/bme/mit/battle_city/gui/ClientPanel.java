package hu.bme.mit.battle_city.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import hu.bme.mit.battle_city.Network.TCPClient;
import hu.bme.mit.battle_city.gui.Menu.PanelId;

public class ClientPanel extends MenuPanel {


	private static final long serialVersionUID = -7806073092005970911L;

	/**
	 * add server ip, button connect to server-wait loop
	 * start gamefield
	 * back button
	 * @param menu 
	 */
	private JButton mBtnAddIP;
	private JButton mBtnBack;
	private JTextField tf;
    JLabel l1; 
    
	public ClientPanel(Menu menuWindow) {
		super(menuWindow);
		
        tf=new JTextField();  

		
		mBtnAddIP = new JButton("Connect to server");
		mBtnAddIP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
		    String hostIP=tf.getText(); 
		   
            System.out.println(hostIP);
            
			l1.setText("Connecting to server..."); 
			try {
				menuWindow.gameOn = true;
				menuWindow.client = new TCPClient(menuWindow,hostIP);
				menuWindow.client.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
			}
		});		
		
		setLayout(null);
 
	    l1=new JLabel("Add server IP");  
		mBtnBack = getBackButton(PanelId.MULTIPLAYER_PANEL);
		
	    l1.setBounds(220,50, 200,30); 
	    mBtnAddIP.setBounds(150, 250, 250, 50);
		mBtnBack.setBounds(150, 450, 250, 50);
        tf.setBounds(150,150, 250,50); 
		
		Font bSize18 = new Font("Arial", Font.PLAIN, 18);
		l1.setFont(bSize18);
		mBtnAddIP.setFont(bSize18);
		mBtnBack.setFont(bSize18);
		tf.setFont(bSize18);
		
		add(l1);
		add(tf);
		add(mBtnAddIP);
		add(mBtnBack);
}

}
