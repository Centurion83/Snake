

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SnakeAI {
	
	private Snake snake;
	private AILearning brain;
	private SnakeComponent comp;
	private String[] directions = new String[] {"Up", "Left", "Right", "Down"};
	private ArrayList<String> weightedDirections;
	private HashMap<String, String> opposites;
	private Random rand;
	private Point goal;
	
	public SnakeAI(Snake snake, AILearning brain, SnakeComponent comp) {
		this.snake = snake;
		this.brain = brain;
		this.comp = comp;
		goal = comp.getFood();
		rand = new Random();
		opposites = new HashMap<String, String>();
		opposites.put("Up", "Down");
		opposites.put("Down", "Up");
		opposites.put("Left", "Right");
		opposites.put("Right", "Left");
		
		weightedDirections = new ArrayList<String>();
		weightedDirections.add("Up");
		weightedDirections.add("Down");
		weightedDirections.add("Left");
		weightedDirections.add("Right");
	}
	
	public void run() {
		String currentDirection = snake.getCurrentDirection();
		String nextDirection;
//		nextDirection = directions[rand.nextInt(4)];
//		ArrayList<String> weightedDirections = 
//		if (comp.getFood() != null) {
//			pickDirections(snake.getPositions().get(0), new Point(comp.getFood().getX(), comp.getFood().getY()));
//		}
		ArrayList<Point> adjacent = pickDirectionsSmarter();
//		if (adjacent.size() < 3) {
//			pickDirections(snake.getPositions().get(0), adjacent.get(rand.nextInt(adjacent.size())));
//		} else if (comp.getFood() != null){
//			pickDirections(snake.getPositions().get(0), comp.getFood());
//		}
		Point choice = adjacent.get(0);
		for (Point p : adjacent) {
			if (brain.getValue(p.x, p.y) > brain.getValue(choice.x, choice.y)) {
				choice = p;
			}
		}
		nextDirection = weightedDirections.get(rand.nextInt(weightedDirections.size()));
		if (opposites.get(nextDirection) != currentDirection) {
			snake.queueDirection(nextDirection);
		}
	}
	
	/**
	 * A method for the AI to pick the direction(s) it thinks is best to travel in
	 * @return a list of directions
	 */
	public void pickDirections(Point current, Point target) {
		
		/*
		 * Simple determination of direction by moving towards the food source.
		 * If the food is directly in one direction it will only select that direction
		 * to move in. If the path to the food is not direct (i.e. up and to the right)
		 * then it will select both directions to get towards the food and add each to the 
		 * list once, therefore they are equal weighted so optimality is not ensured.
		 */
		weightedDirections = new ArrayList<String>();
		if (target.getX() > current.getX()) {
			//Add right
			weightedDirections.add("Right");
		} else if (target.getX() < current.getX()) {
			//Add left
			weightedDirections.add("Left");
		}
		
		if (target.getY() > current.getY()) {
			//Add down
			weightedDirections.add("Down");
		} else if (target.getY() < current.getY()) {
			//Add up
			weightedDirections.add("Up");
		}
	}
	
	/**
	 * Method to check each square adjacent to head of snake and see whether it would
	 * cause the snake to crash. STILL CRASHES
	 * @return a list of adjacent squares that will not cause a crash
	 */
	public ArrayList<Point> pickDirectionsSmarter() {
		ArrayList<Point> adjacent = new ArrayList<Point>();
		Point headLocation = snake.getPositions().get(0);
		ArrayList<Point> out = new ArrayList<Point>();
		adjacent.add(new Point(headLocation.x, headLocation.y+snake.getYSize()));
		adjacent.add(new Point(headLocation.x, headLocation.y-snake.getYSize()));
		adjacent.add(new Point(headLocation.x+snake.getXSize(), headLocation.y));
		adjacent.add(new Point(headLocation.x-snake.getXSize(), headLocation.y));
		
		for (Point p : adjacent) {
			if (!snake.hasCrashed(p)) {
				out.add(p);
			}
		}
		return adjacent;
	}
	
	public double getManhattanDistance(Point current, Point goal) {
		return Math.abs(current.getX()-goal.getX()) + Math.abs(current.getY() - goal.getY());
	}
	
	public int[] getGridFromPoint(Point p) {
		int[] out = new int[2];
		out[0] = (p.y - 20)/20;
		out[1] = (p.x - 20)/20;
		return out;
	}
}
