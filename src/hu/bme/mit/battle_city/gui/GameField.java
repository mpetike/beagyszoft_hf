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
 * A j�t�k grafikus megjelen�t�s�t v�gzo oszt�ly
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
	//majd a gamestatebol egy �res p�ld�ny amibe rak az aktu�lis �ll�s friss�t�sek k�z�tt , kliensn�l p�rhuzamos�t gamestate �s az k�ld ide majd


	public GameField(Menu menuWindow) {
		super(menuWindow);
		objIm = new ObjectImages();
		mWindow = menuWindow;
	    addKeyListener(this);

		// TODO konstruktor: gamelogic l�trehoz�sa, inicializ�l�sa
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
    	 * if server akkor queukliensinput obj oda van neki adva �s a gameworldnek is, egyik t�lti m�sik olvassa 
    	 * 
    	 * ha kliens akkor tcp kliensnek megy  a queue aki k�ldi egyes�vel servernek
    	 * 
    	 * 
    	 * 
    	 */
        setFocusable(true);
        setBackground(Color.BLACK);
    }
	
    
    public void updateFrame()//gameState)
		{
         //ezt h�vja gamelogic ha van �j state
    	 //currentGameState=gameState;
    	
    	//teszt tank mozgat�s gamelogic �ltal k�zvetetten:
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
		// TODO falak, tankok, l�ved�kek, robban�sok kirajzol�sa
		//a paint()-et a repaintel lehet megh�vni, csak a gamlogic h�vja majd a repaintet	
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

		// gamestatebol ki iter�l minden object �s kirajzol
        g.drawImage(myTank, tankx, tanky, this);      
        // ide valami mozg�s smoothener k�ne: k�t koordin�ta k�z�tt a 15x15 csatat�ren, pl leoszt a gamestate change ami x ms onk�nt j�n, valamivel �s a pici idok�z�nk�nt l�ptet itt am�g nem j�n �j 

	
	}

	
// a gomblenyom�sok menjenek egy queuba a gamelogichoz vagy a tcp klienshez->server
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
