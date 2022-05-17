/*
 Author: Gordon Song
 Date: 5/5/22
 Notes: 
 	Identifies And Finds Troops 
 	Using The Pixels From The Game Window
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class IdentifyCard {

	private static String[] colors_large = {"165 125 110", "137 120 100", "150 120 121", "175 107 100", "140 100 75", "150 115 85", "200 105 75"};
	private static String[] names_large = {"Goblin Cage", "XBow", "Cannon", "Tombstone", "Inferno Tower", "Hog Rider", "Hog Rider"};
	private static String[] colors_medium = {"245 105 68", "70 57 46"};
	private static String[] names_medium = {"Balloon", "Mortar"};
	private static String[] colors_small = {"150 150 120"};
	private static String[] names_small = {"Skeleton"};
	
	private static String[][] colors = {colors_large, colors_medium, colors_small};
	private static String[][] names = {names_large, names_medium, names_small};

	public static String getCard(String[] color1, String[] color2, String[] color3) {
		
		String card = "Unknown";
		String[][] colorlist = {color1, color2, color3};
		ArrayList<String[]> weights = new ArrayList<String[]>();
		
		for (int j = 0; j < 3; j++) {
			int size = 30 - j*10;
			int r = Integer.parseInt(colorlist[j][0]);
			int g = Integer.parseInt(colorlist[j][1]);
			int b = Integer.parseInt(colorlist[j][2]);
			String[] s_colors = colors[j];
			String[] s_names = names[j];
			for (int i = 0; i < s_colors.length; i++) {
				int r2 = Integer.parseInt(s_colors[i].split(" ")[0]);
				int g2 = Integer.parseInt(s_colors[i].split(" ")[1]);
				int b2 = Integer.parseInt(s_colors[i].split(" ")[2]);
				int acc = Math.abs(r-r2)+Math.abs(g-g2)+Math.abs(b-b2);
				String[] result = {s_names[i], ""+acc};
				weights.add(result);
			}
		}
		
		int si = 0;
		for (int i = 1; i < weights.size(); i++) {
			if (Integer.valueOf(weights.get(i)[1]) < Integer.valueOf(weights.get(si)[1]))
				si = i;
		}
		
		card = weights.get(si)[0];
		
		return card;
		
	}
	
	public static boolean sameColor(String c1, String c2, int acc) {
		  
		  int r1 = Integer.valueOf(c1.split(" ")[0]);
		  int g1 = Integer.valueOf(c1.split(" ")[1]);
		  int b1 = Integer.valueOf(c1.split(" ")[2]);
		  
		  int r2 = Integer.valueOf(c2.split(" ")[0]);
		  int g2 = Integer.valueOf(c2.split(" ")[1]);
		  int b2 = Integer.valueOf(c2.split(" ")[2]);
		  
		  if(Math.abs(r1-r2)+Math.abs(g1-g2)+Math.abs(b1-b2) < acc)
			  return true;
		  return false;
		  
	}
	  
	
	public static ArrayList<ArrayList<Integer>> getPositions(BufferedImage player) {
		 
		 int x0 = 0;
		 int y0 = 0;
		 int x1 = 553;
		 int y1 = 1015;
		 ArrayList<ArrayList<Integer>> pos = new ArrayList<ArrayList<Integer>>();
	     for (int x = x0; x < x1; x++) {
	        for (int y = y0; y < y1; y++) {
	            Color pixel = new Color(player.getRGB(x, y));
	            if (pixel.getRed() == 236 && pixel.getBlue() == 52 && pixel.getGreen() == 52) {
	            	boolean alreadyFound = false;
	            	for(int i = 0; i < pos.size(); i++) {
	            		if (pos.get(i).get(0) > x - 35 && pos.get(i).get(0) < x + 35 && pos.get(i).get(1) > y - 20 && pos.get(i).get(1) < y + 20)
	            			alreadyFound = true;
	            	}
	            	if (!alreadyFound) {
	            		ArrayList<Integer> newPos = new ArrayList<Integer>();
	            		newPos.add(x);
	            		newPos.add(y);
	            		pos.add(newPos);
	            	}
	            }
	            	
	        }
	     }
		 return pos;
	 }
	
}
