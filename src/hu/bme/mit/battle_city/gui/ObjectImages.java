package hu.bme.mit.battle_city.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A class for map in the game
 *
 * 
 */
public class ObjectImages {

    /**
     * size of the board
     * 15x15 map of 40x40 pixel sized objects => 600x600 game window
     */

	public static int objWidth = 40;
	public static int objHeight = 40;

    /**
     * Game object images
     */
    BufferedImage wall = null;
    BufferedImage myTankUp = null;
    BufferedImage myTankDown = null;
    BufferedImage myTankRight = null;
    BufferedImage myTankLeft = null;
    
    BufferedImage enemyTankUp = null;
    BufferedImage enemyTankDown = null;
    BufferedImage enemyTankRigth = null;
    BufferedImage enemyTankLeft = null;
    
    BufferedImage rocketUp = null;
    BufferedImage rocketDown = null;
    BufferedImage rocketRight = null;
    BufferedImage rocketLeft = null;
	
    BufferedImage explosion1 = null;
    BufferedImage explosion2 = null;
    BufferedImage explosion3 = null;
   

	public ObjectImages() 
	{
		initObjects();
	}
    
    
    public void initObjects()
    {
	try {
		wall = ImageIO.read(new File("images/wall_brick.png"));
		myTankUp = ImageIO.read(new File("images/playerTank_up.png"));
		myTankDown = ImageIO.read(new File("images/playerTank_down.png"));
		myTankRight = ImageIO.read(new File("images/playerTank_right.png"));
		myTankLeft = ImageIO.read(new File("images/playerTank_left.png"));
		
		enemyTankUp = ImageIO.read(new File("images/tank_basic_up.png"));
		enemyTankDown = ImageIO.read(new File("images/tank_basic_down.png"));
		enemyTankRigth = ImageIO.read(new File("images/tank_basic_right.png"));
		enemyTankLeft = ImageIO.read(new File("images/tank_basic_left.png"));
		
		rocketUp = ImageIO.read(new File("images/bullet_up.png"));
		rocketDown = ImageIO.read(new File("images/bullet_down.png"));
		rocketRight = ImageIO.read(new File("images/bullet_right.png"));
		rocketLeft = ImageIO.read(new File("images/bullet_left.png"));

		explosion1 = ImageIO.read(new File("images/bullet_explosion_1.png"));
		explosion2 = ImageIO.read(new File("images/bullet_explosion_2.png"));
		explosion3 = ImageIO.read(new File("images/bullet_explosion_3.png"));
	} catch (IOException e) {
        System.out.println("IOException");
	}   	  
    }
    
    
    public BufferedImage getImg( int objType,int heading)
    {
    	BufferedImage image = null;
    	switch(objType)
    	{
    	case 0:
				switch (heading)
		        {
		        case 0:  image =  myTankDown;  break;
		        case 1:  image =  myTankRight; break;
		        case 2:  image =  myTankUp;    break;
		        case 3:  image =  myTankLeft;  break;
		        default: image =  myTankUp;    break;
		        
		        }break;
		        
    	case 1:
				switch (heading)
		        {
		        case 0:  image =  enemyTankDown;  break;
		        case 1:  image =  enemyTankRigth; break;
		        case 2:  image =  enemyTankUp;    break;
		        case 3:  image =  enemyTankLeft;  break;
		        default: image =  enemyTankUp;    break;
		        
		        }break;  
		        
    	case 2:
				switch (heading)
		        {
		        case 0:  image =  rocketDown;  break;
		        case 1:  image =  rocketRight; break;
		        case 2:  image =  rocketUp;    break;
		        case 3:  image =  rocketLeft;  break;
		        default: image =  rocketUp;    break;
		        
		        }break;	
		        
    	case 3:
				switch (heading)
		        {
		        case 0:  image =  explosion1;  break;
		        case 1:  image =  explosion2;  break;
		        case 2:  image =  explosion3;  break;
		        default: image =  explosion1;  break;
		        
		        }break;		        
        
    	}
		
		
		return image;
    	
    }
}




