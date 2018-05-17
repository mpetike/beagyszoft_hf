package hu.bme.mit.battle_city.GameLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MapLoadUtility {	
	
	public static boolean[][] LoadMapFromFile(String path) {
		boolean[][] map_loaded = new boolean[15][15];
		
		//Open file and load into bufferreader
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
