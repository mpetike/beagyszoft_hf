package hu.bme.mit.battle_city.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

import hu.bme.mit.battle_city.GameLogic.AiTank;
import hu.bme.mit.battle_city.GameLogic.CannonShell;
import hu.bme.mit.battle_city.GameLogic.Explosion;
import hu.bme.mit.battle_city.GameLogic.GameWorld;
import hu.bme.mit.battle_city.GameLogic.PlayerTank;
import hu.bme.mit.battle_city.GameLogic.RenderObjects;
import hu.bme.mit.battle_city.gui.Menu.PanelId;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A játék grafikus megjelenítését végzo osztály
 */
public class GameField extends MenuPanel implements KeyListener, Serializable {


    Menu mWindow;
    ObjectImages objIm;
    boolean[][] currentLevel;
    public Queue<Integer> userInput; 
    public Queue<Integer> clientInput;     
    public GameWorld gameEngine;
    public RenderObjects gameState;
    
    
    Timer timer; 
	private static final long serialVersionUID = 6958968330216408636L;

/**
 * 
 * @param menuWindow
 */
	public GameField(Menu menuWindow) {
		super(menuWindow);
		objIm = new ObjectImages();
		mWindow = menuWindow;
	    addKeyListener(this);
	    
	    // game over return to menu delay
		int delay = 2000;
		timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                    
            	mWindow.showPanel(PanelId.GAME_MODE_SELECTOR);

            }
        });
	}

	
	public void startGame() {


        // init gamelogic stb. start new thread
    	userInput = new LinkedList<Integer>();
    	if  (!mWindow.clientMode)
    	{
    		if(mWindow.gameMode)
    		{
		    	clientInput = new LinkedList<Integer>();
    		}
    		
	    	gameEngine = new GameWorld(mWindow.MapFolder + mWindow.currentMap, mWindow.gameMode, mWindow.difficulty,userInput,clientInput,this );
	    	gameEngine.StartGame();
	    	gameState = gameEngine.renderobj;
	    	
    	}

        setFocusable(true);
        setBackground(Color.BLACK);
    }
	
	
    
    public void updateFrame()
		{
    	// remote send
		if(mWindow.gameMode & !mWindow.clientMode )
		{
	     	synchronized(mWindow.server.serverSend)
			{
			mWindow.server.serverSend.notify();
			}
		}

    	repaint();
    	 
		}
    
    
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//falak, tankok, lövedékek, robbanások kirajzolása

		if (gameState.MapGridArray!=null)
		{
			currentLevel = gameState.MapGridArray;
		
		for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) 
            {
            	if (currentLevel[y][x])
            	{
                	g.drawImage(objIm.wall, x*ObjectImages.objWidth, y*ObjectImages.objHeight, this);
            	}	
            }
            } 
		
	    //objType: 0-Player tank, 1-Enemy tank, 2-CannonShell,3-Explosion
    	//draw AlivePlayerTanks
    	ArrayList<PlayerTank> PlayerTank = gameState.AlivePlayerTanks;
    	for(PlayerTank playerTank:PlayerTank)
    	{
    		
            BufferedImage playerTankImg = objIm.getImg(0,playerTank.Heading);
            g.drawImage(playerTankImg, playerTank.GridLocX*40, playerTank.GridLocY*40, this); 
            
    	}
    	
    	//draw AliveAiTanks
    	ArrayList<AiTank> AliveAiTanks = gameState.AliveAiTanks;
    	for(AiTank tank:AliveAiTanks)
    	{
    		
            BufferedImage tankImg = objIm.getImg(1,tank.Heading);
            g.drawImage(tankImg, tank.GridLocX*40, tank.GridLocY*40, this); 
            
    	}    	
    	
		
    	//draw CannonShell
    	ArrayList<CannonShell> CannonShell = gameState.AliveShells;
    	for(CannonShell cannon:CannonShell)
    	{
    		
            BufferedImage cannonImg = objIm.getImg(2,cannon.Heading);
            g.drawImage(cannonImg, cannon.GridLocX*40 + 18, cannon.GridLocY*40 + 18, this); 
            
    	}
    	
    	//draw Explosions
    	ArrayList<Explosion> Explosions = gameState.ActiveExplosions;
    	for(Explosion explosion:Explosions)
    	{
    		
            BufferedImage explosionImg = objIm.getImg(3,explosion.Heading);
            g.drawImage(explosionImg, explosion.GridLocX*40, explosion.GridLocY*40, this); 
            
    	}    	
		}
		
		if (gameState.GameOver == true)
		{
			g.drawImage(objIm.gameOver, 176, 200, this);
			timer.setRepeats( false );
			timer.start();
		}
		
	}

	//}

	
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            userInput.add(0);
            if(mWindow.clientMode)
            {
		     	synchronized(mWindow.client.clientSend)
				{
				mWindow.client.clientSend.notify();
				}
	     	}
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            userInput.add(1);
            if(mWindow.clientMode)
            {
		     	synchronized(mWindow.client.clientSend)
				{
		     		mWindow.client.clientSend.notify();
				}
	     	}            
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            userInput.add(2);  
            if(mWindow.clientMode)
            {
		     	synchronized(mWindow.client.clientSend)
				{
		     		mWindow.client.clientSend.notify();
				}
	     	}            
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            userInput.add(3);
            if(mWindow.clientMode)
            {
		     	synchronized(mWindow.client.clientSend)
				{
		     		mWindow.client.clientSend.notify();
				}
	     	}            
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            userInput.add(4);
            if(mWindow.clientMode)
            {
		     	synchronized(mWindow.client.clientSend)
				{
		     		mWindow.client.clientSend.notify();
				}
	     	}            
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	mWindow.showPanel(PanelId.GAME_MODE_SELECTOR);  
        	mWindow.gameOn = false;
        	mWindow.clientMode = false;
            if(!mWindow.clientMode)
            {
        	gameEngine.GameOver = true;
            }
            else {
            	if(mWindow.gameMode)
            	{
            		try {
						mWindow.server.serverSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	}
            }
            
        }
              
    
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}