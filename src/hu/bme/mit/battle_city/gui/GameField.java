package hu.bme.mit.battle_city.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A játék grafikus megjelenítését végzo osztály
 */
public class GameField extends MenuPanel implements KeyListener {

    int gameMode=0; // 0 single; 1 server; 2 client;
    int tankx=0;
    int tanky=0;
    int tankori=0;
    int level = 2;
    Map map = null;
	private static final long serialVersionUID = 6958968330216408636L;

	//private GameState currentState; //majd a gamestatebol egy üres példány amibe rak az aktuális állás frissítések között , kliensnél párhuzamosít gamestate és az küld ide majd


	public GameField(Menu gameWindow) {
		super(gameWindow);
		map = new Map();
		startGame();
	    addKeyListener(this);

		// TODO konstruktor: gamelogic létrehozása, inicializálása
	}

	
    private void startGame() {


        // init gamelogic stb. start new thread
        setFocusable(true);
        setBackground(Color.BLACK);
        //setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }
	
	
	
	/**
	 * A megjelenítés alapjául szolgáló adatok frissítése
	 * 
	 * @param gameState
	 *            Az új állapotokat tároló {@link GameState}.
	 */
	/*public void update(GameState gameState) {
		if (mGameState == null) {
			mGameState = gameState;
		} else {
			synchronized (mGameState) {
				mGameState.update(gameState);
			}
		}
		repaint();
	}

	/**
	 * Akkor hívódik, ha hálózati hiba történt
	 */
	/*public void onNetworkError() {
		JOptionPane.showMessageDialog(this, "A hálózati kapcsolat megszakadt", "Hálózati hiba",
				JOptionPane.ERROR_MESSAGE);
		mGameWindow.showPanel(PanelId.GAME_MODE_SELECTOR);
	}
   */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// TODO falak, tankok, lövedékek, robbanások kirajzolása
		//a paint()-et a repaintel lehet meghívni, csak a gamlogic hívja majd a repaintet	
	    //draw bricks
		int[][] currentLevel = Map.getMap(level);
        if(map.wall != null){
                for (int y = 0; y < Map.height; y++) {
                    for (int x = 0; x < Map.width; x++) 
                    {
                    	if (currentLevel[y][x]>0)
                    	{
                        	g.drawImage(map.wall, x*Map.objWidth, y*Map.objHeight, this);
                    	}	
                    }
                    }              
        //read gamestate and draw objects
                
                BufferedImage myTank = null;
				switch (tankori)
                {
                case 0:  myTank=map.myTankDown; break;
                case 1:  myTank=map.myTankRight;break;
                case 2:  myTank=map.myTankUp; break;
                case 3:  myTank=map.myTankLeft; break;
                default: myTank=map.myTankUp; break;
                
                }
                g.drawImage(myTank, tankx, tanky, this);      
                // ide valami mozgás smoothener kéne: két koordináta között a 15x15 csatatéren, pl leoszt a gamestate change ami x ms onként jön, valamivel és a pici idoközönként léptet itt amíg nem jön új 
	}
	}


	
// a gomblenyomások menjenek egy queuba a gamelogichoz vagy a tcp serverhez
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");

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
