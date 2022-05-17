/*
 Author: Bryan Liu
 Date: 5/5/22
 Notes: 
 	Handles The Window Component,
 	Displays Key Bot Information
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

public class Window extends JPanel
{
  
  public static int elixir = 0;
  public static String[] hand = {"unknown", "unknown", "unknown", "unknown"};
  public static String[] next = {"unknown", "unknown", "unknown", "unknown"};
  public static ArrayList<ArrayList<Integer>> positions = new ArrayList<ArrayList<Integer>>();
  public static ArrayList<String> cardNames = new ArrayList<String>();
  public static BufferedImage player;
    
  public Window()
  {
    setBackground(Color.white);
    repaint();
  }
  
  // Draws the rainbow.
  public void paint(Graphics g)
  {
	g.setColor(Color.white);
	g.fillRect(0, 0, 553, 1015);
	
	g.drawImage((Image)player, 0, 0, this);
	
	Font stringFont = new Font( "SansSerif", Font. PLAIN, 25 );
	Font smaller = new Font( "SansSerif", Font. PLAIN, 15 );
	g.setFont(stringFont);
	g.setColor(Color.green);
	
	g.drawString("Elixir: " + elixir, 0, 110);
	g.drawString("Hand: " + hand[0] + ", " + hand[1] + ", " + hand[2] + ", " + hand[3], 0, 135);
	
	g.setFont(smaller);
	
	for (int i = 0; i < positions.size(); i++) {
		g.setColor(Color.red);
		ArrayList<Integer> xy = positions.get(i);
		if (xy.size() > 1 && xy.get(0)+20 < 553 && xy.get(1)+40 < 1015 && xy.get(0) > 0 && xy.get(1)-10 > 0) {
			g.drawRect(xy.get(0)+4, xy.get(1)+10, 10, 10);
			g.setColor(Color.red);
			g.drawString(cardNames.get(i), xy.get(0)+5, xy.get(1)-10);
		}
	}
	
	
	
	try {
		repaint();
		Thread.sleep(10);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }


}
