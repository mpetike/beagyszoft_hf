package hu.bme.mit.battle_city.GameLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class GameLogicUtility {	
	
	/**
	 * Generates a random coordinate thats not on a wall, and and has a distance from the specified position more than the set value
	 * @param map Gamemap
	 * @param pos_x PositionX
	 * @param pos_y PositionY
	 * @param distance Distance
	 * @return
	 */
	public static int[] RandomPositionGenDistance(boolean[][] map,int pos_x,int pos_y,int distance) {
		int[] random_pos = new int[2];
		int y = 0,x = 0;
		
		Random rand = new Random();
		
		boolean SpaceOccupied = true;
		while(true) {
			SpaceOccupied = true;
			while(SpaceOccupied == true) {
				y = rand.nextInt(15);
				x = rand.nextInt(15);
				if(map[y][x] == false)
					SpaceOccupied = false;
			}		
			float distance_calc = (float) Math.sqrt(Math.pow((x - pos_x), 2) + Math.pow((y - pos_y), 2));
			if(distance_calc >= distance)break;
			
		}
		random_pos[0] = y;
		random_pos[1] = x;
		return random_pos;
	}
	
	/**
	 * Generates random location thats not occupied by a wall
	 * @param map Gamemap
	 * @return
	 */
	public static int[] RandomPositionGen(boolean[][] map) {
		int[] random_pos = new int[2];
		int y = 0,x = 0;
		
		Random rand = new Random();
		
		boolean SpaceOccupied = true;
		
		while(SpaceOccupied == true) {
			y = rand.nextInt(15);
			x = rand.nextInt(15);
			if(map[y][x] == false)
				SpaceOccupied = false;
		}
		random_pos[0] = y;
		random_pos[1] = x;
		return random_pos;
	}
	
	/**
	 * Loads a map from a file, map file has to be made of 15x15 characters, where 1 is a wall 0 is an empty space
	 * @param path Path to mapfile
	 * @return 15x15 boolean array for gameworld
	 */
	public static boolean[][] LoadMapFromFile(String path) {
		boolean[][] map_loaded = new boolean[15][15];
		
		//Open file and load into buffer reader
		FileReader mapfile;
		BufferedReader mapfile_reader;
		
		try {			
			mapfile = new FileReader(path);			
			mapfile_reader = new BufferedReader(mapfile);
			
			//Process file into boolean array
			String line = null;
			int row = 0, collumn = 0;	
			for(row = 0;row != 15;row++)
			{
				line = mapfile_reader.readLine();
				for(collumn = 0;collumn != 15;collumn++) {
					if(line.charAt(collumn) == '1')
						map_loaded[row][collumn] = true;
					else
						map_loaded[row][collumn] = false;
				}
			}
			mapfile_reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			map_loaded = null;
		}				
		
		return map_loaded;
	}
}
