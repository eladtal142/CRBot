/*
 Author: Elad Tal
 Date: 5/5/22
 Notes: 
 	Handles The Card Types,
 	And Holds The Main Method
*/

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Robot;

public class Bot {
	
	  public static Robot bot;
	  private static Decider decider;
	  
	  static double xscale = 1.53;
	  static double yscale = 1.57;
	  
	  static int card_x_start = (int)(83*xscale+0.5);
	  static int card_y_start = (int)(525*yscale+0.5);
	  static int card_width = (int)(58*xscale+0.5);
	  static int card_height = (int)(64*yscale+0.5);
	  static int spacing = (int)(10*xscale+0.5);	
	  static int elixir_spacing = 38;
	  static int elixir_x = 184;
	  static int elixir_y = 960;
//	  static String[] colors = {"124 91 82", "90 49 75", "148 98 88", "107 120 129", "99 136 186", "149 136 127", "103 118 150", "99 83 72"};
	  static String[] colors = {"111 94 89", "85 58 87", "143 91 86", "101 125 136", "93 138 187", "136 136 133", "92 120 158", "94 88 81"};
	  
	  static String[] names = {"hog rider", "firecracker", "valkyrie", "skeletons", "ice spirit", "log", "tesla", "earthquake"};
	  
	  public static BufferedImage getPlayer() {
		return bot.createScreenCapture(new Rectangle(1350, 35, 553, 1015));
	  }
	  
	
	 public static void main(String args[]) {
		  try {
			   bot = new Robot();
	      } catch (Exception failed) {}
		  
		  JFrame w = new JFrame("Clash-Bot");
		  w.setBounds(1930, 0, 553, 1015);
		  w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  Container c = w.getContentPane();
		  c.add(new Window());
		  w.setVisible(true);
		  decider = new Decider();
		  
		  while (true) {
			  BufferedImage player = getPlayer();
			  String[] hand = {"unknown", "unknown", "unknown", "unknown"};
			  for (int i = 0; i < 4; i++) {
				  BufferedImage cropped = player.getSubimage(card_x_start+i*(card_width+spacing), card_y_start, card_width, card_height);
				  String color = averageColor(cropped, 0, 0, card_width, card_height);
				  for (int j = 0; j < 8; j++) {
					  if (IdentifyCard.sameColor(color, colors[j], 10))
						  hand[i] = names[j];
				  }
			  }
			  ArrayList<ArrayList<Integer>> positions = IdentifyCard.getPositions(player);
			  ArrayList<String> cardNames = getNames(player, positions);
			  //Update Window State
			  Window.elixir = getElixir(player);
			  Window.hand = hand;
			  Window.positions = positions;
			  Window.player = player; 
			  Window.cardNames = cardNames;

			  //Update Decider State
			  decider.update();
			  

		  }
		  
	 }
	 
	 public static ArrayList<String> getNames(BufferedImage player, ArrayList<ArrayList<Integer>> pos) {
		 
		 ArrayList<String> names = new ArrayList<String>();
		 
		 for (int i = 0; i < pos.size(); i++) {
			 ArrayList<Integer> xy = pos.get(i);
			 int x = xy.get(0);
			 int y = xy.get(1);
			 String name = "Unknown";
			 String[] color1 = averageColor(player, x+8, y+10, 30, 30).split(" ");
			 String[] color2 = averageColor(player, x+6, y+10, 20, 20).split(" ");
			 String[] color3 = averageColor(player, x+4, y+10, 10, 10).split(" ");
			 name = IdentifyCard.getCard(color1, color2, color3);
			 if (!name.equals("Unknown"))
				names.add(name);
				 
			 if (name.equals("Unknown"))
				 names.add("Unknown");
		 }
		 
		 return names;
		 
	 }
	 
	 public static int getElixir(BufferedImage player) {
		
		 int elixir = 0;
		 
		 for (int i = 0; i < 10; i++) {
			 Color pixel = new Color(player.getRGB(elixir_x+i*elixir_spacing, elixir_y));
			 if (pixel.getRed() > pixel.getGreen())
				 elixir += 1;
		 }
		 
		 return elixir;
	 }
	 	 
	 public static String averageColor(BufferedImage newBufferedImage, int startX, int startY, int w, int h) {
		    int x1 = startX + w;
		    int y1 = startY + h;
		    long sumred = 0, sumgreen = 0, sumblue = 0;
		    for (int x = startX; x < x1; x++) {
		        for (int y = startY; y < y1; y++) {
		            Color pixel = new Color(newBufferedImage.getRGB(x, y));
		            sumred += pixel.getRed();
		            sumgreen += pixel.getGreen();
		            sumblue += pixel.getBlue();
		        }
		    }
		    int num = w * h;
		    return sumred/num + " " + sumgreen/num + " " + sumblue/num;
	  }
	 
}
