package hu.bme.mit.battle_city.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import hu.bme.mit.battle_city.GameLogic.AiTank;
import hu.bme.mit.battle_city.GameLogic.CannonShell;
import hu.bme.mit.battle_city.GameLogic.Explosion;
import hu.bme.mit.battle_city.GameLogic.GameLogicUtility;
import hu.bme.mit.battle_city.GameLogic.GameWorld;
import hu.bme.mit.battle_city.GameLogic.PlayerTank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A játék grafikus megjelenítését végzo osztály
 */
public class GameField extends MenuPanel implements KeyListener {


    Menu mWindow;
    ObjectImages objIm;
    boolean[][] currentLevel;
    Queue<Integer> userInput; 
    Queue<Integer> clientInput;     
    GameWorld gameEngine;
	private static final long serialVersionUID = 6958968330216408636L;


	public GameField(Menu menuWindow) {
		super(menuWindow);
		objIm = new ObjectImages();
		mWindow = menuWindow;
	    addKeyListener(this);

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
	    	
	    	
    	}

        setFocusable(true);
        setBackground(Color.BLACK);
    }
	

	
	public void updateRemoteFrame() 
	{
		//deserialize gameworld from tcp client
		
	}
	
    
    public void updateFrame()
		{
    	 repaint();
		}
    
    
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//falak, tankok, lövedékek, robbanások kirajzolása

	    //draw map
		if (gameEngine==null)
			{
				currentLevel = new boolean[15][15];
			}
		else 
		{
			currentLevel = gameEngine.MapGridArray;
		}
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
    	ArrayList<PlayerTank> PlayerTank = gameEngine.AlivePlayerTanks;
    	for(PlayerTank playerTank:PlayerTank)
    	{
    		
            BufferedImage playerTankImg = objIm.getImg(0,playerTank.Heading);
            g.drawImage(playerTankImg, playerTank.GridLocX*40, playerTank.GridLocY*40, this); 
            
    	}
    	
    	//draw AliveAiTanks
    	ArrayList<AiTank> AliveAiTanks = gameEngine.AliveAiTanks;
    	for(AiTank tank:AliveAiTanks)
    	{
    		
            BufferedImage tankImg = objIm.getImg(1,tank.Heading);
            g.drawImage(tankImg, tank.GridLocX*40, tank.GridLocY*40, this); 
            
    	}    	
    	
		
    	//draw CannonShell
    	ArrayList<CannonShell> CannonShell = gameEngine.AliveShells;
    	for(CannonShell cannon:CannonShell)
    	{
    		
            BufferedImage cannonImg = objIm.getImg(2,cannon.Heading);
            g.drawImage(cannonImg, cannon.GridLocX*40 + 18, cannon.GridLocY*40 + 18, this); 
            
    	}
    	
    	//draw Explosions
    	ArrayList<Explosion> Explosions = gameEngine.ActiveExplosions;
    	for(Explosion explosion:Explosions)
    	{
    		
            BufferedImage explosionImg = objIm.getImg(3,explosion.Heading);
            g.drawImage(explosionImg, explosion.GridLocX*40, explosion.GridLocY*40, this); 
            
    	}    	
    	
    	
    	
        // ide valami mozgás smoothener kéne: két koordináta között a 15x15 csatatéren, pl leoszt a gamestate change ami x ms onként jön, valamivel és a pici idoközönként léptet itt amíg nem jön új 

	
	}

	
// a gomblenyomások menjenek egy queuba a gamelogichoz vagy a tcp klienshez->server
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            userInput.add(0);
            //send to TCP client
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            userInput.add(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            userInput.add(2);   
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            userInput.add(3);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            userInput.add(4);

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