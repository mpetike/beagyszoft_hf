package hu.bme.mit.battle_city.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.SwingUtilities;

import hu.bme.mit.battle_city.GameLogic.GameLogicUtility;
import hu.bme.mit.battle_city.GameLogic.GameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A játék grafikus megjelenítését végzo osztály
 */
public class GameField extends MenuPanel implements KeyListener {

    int tankx=0;
    int tanky=0;
    int tankori=0;
    Menu mWindow;
    ObjectImages objIm;
    boolean[][] currentLevel;
    Queue<Integer> userInput; 
    Queue<Integer> clientInput;     
    GameWorld gameEngine;
	private static final long serialVersionUID = 6958968330216408636L;

	//private GameState currentState; 
	//majd a gamestatebol egy üres példány amibe rak az aktuális állás frissítések között , kliensnél párhuzamosít gamestate és az küld ide majd


	public GameField(Menu menuWindow) {
		super(menuWindow);
		objIm = new ObjectImages();
		mWindow = menuWindow;
	    addKeyListener(this);

		// TODO konstruktor: gamelogic létrehozása, inicializálása
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
    	/*
    	 * 
    	 * if server akkor queukliensinput obj oda van neki adva és a gameworldnek is, egyik tölti másik olvassa 
    	 * 
    	 * ha kliens akkor tcp kliensnek megy  a queue aki küldi egyesével servernek
    	 * 
    	 * 
    	 * 
    	 */
        setFocusable(true);
        setBackground(Color.BLACK);
    }
	
    
    public void updateFrame()//gameState)
		{
         //ezt hívja gamelogic ha van új state
    	 //currentGameState=gameState;
    	
    	//teszt tank mozgatás gamelogic által közvetetten:
    	 tankx=tankx+100;
    	 repaint();
		}
    
   /* Gameworldbe hogy menjen:
    * 
    * private GameField gameField;
    * 
    * GameWorld(String map_path, boolean sp_mode, int difficulty,Queue<Integer> InputQueue,Queue<Integer> RemoteInput, GameField gameFieldObj)
    * 	gameField = gameFieldObj;
    * 
    * 
    SwingUtilities.invokeLater(new Runnable() 
    {
      public void run()
      {
    	  gameFieldobj,updateFrame();
      }		
    });
    
    */
    
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// TODO falak, tankok, lövedékek, robbanások kirajzolása
		//a paint()-et a repaintel lehet meghívni, csak a gamlogic hívja majd a repaintet	
	    //draw bricks
	    int objType=0;  // 0-My tank, 1-Enemy tank, 2-Rocket,3-Explosion
	    
		if (currentLevel==null)
			{
				currentLevel = GameLogicUtility.LoadMapFromFile(mWindow.MapFolder+mWindow.currentMap);
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
		
		
        //read gamestate and draw objects
                
        BufferedImage myTank = objIm.getImg(objType,tankori);

		// gamestatebol ki iterál minden object és kirajzol
        g.drawImage(myTank, tankx, tanky, this);      
        // ide valami mozgás smoothener kéne: két koordináta között a 15x15 csatatéren, pl leoszt a gamestate change ami x ms onként jön, valamivel és a pici idoközönként léptet itt amíg nem jön új 

	
	}

	
// a gomblenyomások menjenek egy queuba a gamelogichoz vagy a tcp klienshez->server
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            userInput.add(0);
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
