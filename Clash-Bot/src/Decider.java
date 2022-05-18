/*
 Author: Max Tran
 Date: 5/5/22
 Notes: 
 	Decides Which Cards To Select,
 	Decides Where To Place Them,
 	Selects The Cards
 */

import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class Decider {

	public String[] currentHand = {"unknown", "unknown", "unknown", "unknown"};
	public int currentElixir = 0;
	public ArrayList<ArrayList<Integer>> positions = new ArrayList<ArrayList<Integer>>();
	public Point[] cardPositions = {new Point(0,0), new Point(0,0), new Point(0,0), new Point(0,0)};
	public ArrayList<ArrayList<Point>> placements = new ArrayList<ArrayList<Point>>();
	public ArrayList<String> cardNames = new ArrayList<String>();

	private void click(int x, int y) {
		int mask = InputEvent.BUTTON1_DOWN_MASK;
		Bot.bot.mouseMove(x, y);
		Bot.bot.mousePress(mask);
		Bot.bot.mouseRelease(mask);
	}

	private void initPlacements(){
		ArrayList<Point> hogRiderPlacements = new ArrayList<Point>();
		placements.add(hogRiderPlacements);
	}

	public Decider(){
		currentHand = Window.hand;
		initPlacements();
	}

	private String getBestMove(){
		Point bestMovePos = new Point(0,0);
		String bestMoveCard = "none";
		return bestMoveCard + " " + bestMovePos.x + " " + bestMovePos.y;
	}

	private void selectCard(String bestMoveCard){
		int cardPos = 0;
		for (int i = 0; i < currentHand.length; i++) {
			if (currentHand[i].equals(bestMoveCard)) {
				cardPos = i;
			}
		}
		int xPos = cardPositions[cardPos].x;
		int yPos = cardPositions[cardPos].y;
		click(xPos, yPos);
	}

	private void placeCard(Point cardPos){
		int xPos = cardPos.x;
		int yPos = cardPos.y;
		click(xPos, yPos);
	}

	public void update(){
		this.currentHand = Window.hand;
		this.positions = Window.positions;
		this.cardNames = Window.cardNames;
		this.currentElixir = Window.elixir;

		if (this.currentElixir > 0){
			String bestMove = getBestMove();
			String[] bestMoveSplit = bestMove.split(" ");
			String bestMoveCard = bestMoveSplit[0];
			int bestMoveX = Integer.parseInt(bestMoveSplit[1]);
			int bestMoveY = Integer.parseInt(bestMoveSplit[2]);
			if (!bestMoveCard.equals("none")){
				selectCard(bestMoveCard);
			}
		}

	}


}
