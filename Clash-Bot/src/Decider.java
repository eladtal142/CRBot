import java.awt.event.InputEvent;

/*
 Author: Max Tran
 Date: 5/5/22
 Notes: 
 	Decides Which Cards To Select,
 	Decides Where To Place Them,
 	Selects The Cards
 */

public class Decider {

	  public static void click(int x, int y) {
		  int mask = InputEvent.BUTTON1_DOWN_MASK;
		  Bot.bot.mouseMove(x, y);
		  Bot.bot.mousePress(mask);
		  Bot.bot.mouseRelease(mask);
	  }
	  
	  
	
}
