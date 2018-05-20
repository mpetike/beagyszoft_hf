package hu.bme.mit.battle_city.GameLogic;

import java.util.ArrayList;

public class PathNode {
	public int x;
	public int y;
	public ArrayList<int[]> Path;
	
	public PathNode(int y_in, int x_in,PathNode Path2Here) {
		x = x_in;
		y = y_in;
		if(Path2Here == null)
			Path = new ArrayList<int[]>();
		else
			Path = new ArrayList<int[]>(Path2Here.Path);
		int[] addval = {y,x};
		Path.add(addval);	
	}
	
}
