package hu.bme.mit.battle_city.gui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * A class for map in the game
 *
 * 
 */
public class Map {

    /**
     * Hard coded the size of the board
     * 15x15 map of 40x40 pixel sized objects => 600x600 game window
     */
    public static final int width = 15;
    public static final int height = 15;
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
	

	public Map() 
	{
		initObjects();
	}
    
    
    
    /**
     * Return the map in 2D array
     *
     * @param stage
     * @return 2D array that represents the map
     */
    public static int[][] getMap(int level) {
        switch (level) {
        case 1:  return level0;
        case 2:  return readFromFile("maps/2.txt");
        case 3:  return level0;
        case 4:  return level0;
        default: return level0;
                
    }
    }

    /**
     * Hard-coded and initialize 2D array as map
     */
    public static final int[][] level0 = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},        
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};

    /**
     * Read map from files
     *
     * @param fileName
     * @return an array list that represents a map
     */
    public static int[][]
            readFromFile(String fileName) {
        ArrayList<ArrayList<Integer>> tempMap = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.isEmpty()) {
                } else {
                    ArrayList<Integer> row = new ArrayList<>();
                    String[] values = currentLine.trim().split("");
                    for (String string : values) {
                        if (!string.isEmpty()) {
                        	
                        	if (string.equals("#"))
                            {                              
                             row.add(1);
                            }  
                        	else
                        	{
                             row.add(0);
                            }
                        }
                    }
                    tempMap.add(row);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        int[][] map = arrayListToArray(tempMap);
        return map;
    }
    
    
    public static int[][] arrayListToArray(
            ArrayList<ArrayList<Integer>> arrayList) {
        int[][] intArray = arrayList.stream().map(u -> u.stream().mapToInt(
                i -> i).toArray()).toArray(int[][]::new);
        return intArray;
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

	} catch (IOException e) {
        System.out.println("IOException");
	}   	  
    }
    
}




