package hu.bme.mit.battle_city.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import hu.bme.mit.battle_city.GameLogic.GameLogicUtility;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A játék grafikus megjelenítését végzo osztály
 */
public class GameField extends MenuPanel implements KeyListener {

    int tankx=0;
    int tanky=0;
    int tankori=0;
    int objType=0;  // 0-My tank, 1-Enemy tank, 2-Rocket,3-Explosion
    Menu mWindow;
    ObjectImages objIm;
    boolean[][] currentLevel;
    Queue<String> userInput; 
    // GameWorld gameEngine
	private static final long serialVersionUID = 6958968330216408636L;

	//private GameState currentState; 
	//majd a gamestatebol egy üres példány amibe rak az aktuális állás frissítések között , kliensnél párhuzamosít gamestate és az küld ide majd


	public GameField(Menu menuWindow) {
		super(menuWindow);
		objIm = new ObjectImages();
		mWindow = menuWindow;
		startGame();
	    addKeyListener(this);

		// TODO konstruktor: gamelogic létrehozása, inicializálása
	}

	
    private void startGame() {


        // init gamelogic stb. start new thread
    	userInput = new LinkedList<String>();
    	// gameEngine = new GameWorld( this,gamemode,myQ,etc args);
    	// gameEngine.start();
    	/*
    	 * 
    	 * if server akkor queukliensinput obj oda van neki adva és a gameworldnek is, egyik tölti másik olvassa 
    	 * 
    	 * ha kliens akkor tcp kliensnek megy  a queue aki küldi egyesével servernek
    	 * 
    	 * éehet egyszerre írni és olvasni queue-t?
    	 * 
    	 */
        setFocusable(true);
        setBackground(Color.BLACK);
    }
	
    
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// TODO falak, tankok, lövedékek, robbanások kirajzolása
		//a paint()-et a repaintel lehet meghívni, csak a gamlogic hívja majd a repaintet	
	    //draw bricks
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
            System.out.println("Right key pressed");
            userInput.add("r");
	            if(tankori!=1)
	            {
	            tankori=1;
	            }
	            else
	            {
	                if (tankx<560)
	                {
		            tankx = tankx+5;
	                }
	            }
	            repaint();
            }
         
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            userInput.add("l");
            	if(tankori!=3)
            	{
		            tankori=3;
	            }
            	else
            	{
                    if (tankx>0)
                    {
	    	          tankx = tankx-5;
                    }
            	}
                repaint();
            	}
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP key pressed");
            userInput.add("u");
            if (tankori!=2)
	            {
	            tankori=2;
	            }
            else
	            {
	             if (tanky>0)
	             {
	             tanky = tanky - 5;          	
	             }
	            }
            repaint();      
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            userInput.add("d");
            if (tankori!=0)
	            {
	            tankori=0;
	            }
            else
	            {
	             if (tanky<560)
	             {
	             tanky = tanky + 5;          	
	             }
	            }
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //userInput.add("s");
            String first=userInput.poll();
            System.out.println(first);
            repaint();
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
