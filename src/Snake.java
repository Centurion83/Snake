
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;


public class Snake extends Observable{
	
	private int numberOfSections;
	private int stepsTaken;
	private int xPos, yPos, maxX, maxY;
	private int minX = 20;
	private int minY = 20;
	private int xSize = 20;
	private int ySize = 20;
	private int speed = 100;
	private ArrayList<String> directions, directionQueue;
	private ArrayList<Point> positions;
	private String currentDirection;
	private boolean alive;
	private Thread sleeper;
	private SnakeComponent gui;
	
	public Snake(int xPos, int yPos, int maxX, int maxY, SnakeComponent gui) {
		this.gui = gui;
		sleeper = new Thread();
		numberOfSections = 3;
		stepsTaken = 0;
		this.currentDirection = "Up";
		this.xPos = xPos;
		this.yPos = yPos;
		this.maxX = maxX;
		this.maxY = maxY;
		this.alive = true;
		this.directionQueue = new ArrayList<String>();
		
		this.directions = new ArrayList<String>();
		for (int i =0; i <numberOfSections; i++) {
			this.directions.add(currentDirection);
		}
		
		this.positions = new ArrayList<Point>();
		for (int i = 0; i < numberOfSections; i++) {
			this.positions.add(new Point(xPos, yPos+(i*ySize)));
		}
	}
	
	public ArrayList<Point> getPositions() {
		return positions;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public int getNumberOfSections() {
		return numberOfSections;
	}
	
	public int stepsTaken() {
		return stepsTaken;
	}
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getMinX() {
		return minX;
	}
	
	public int getMinY() {
		return minY;
	}
	
	public int getMaxX() {
		return maxX;
	}
	
	public int getMaxY() {
		return maxY;
	}
	
	@SuppressWarnings("static-access")
	public void move() {
			for (int i = 0; i < numberOfSections; i++) {
				switch(directions.get(i)) {
					case "Up":
						moveUp(i);
						break;
					case "Down":
						moveDown(i);
						break;
					case "Left":
						moveLeft(i);
						break;
					case "Right":
						moveRight(i);
						break;
				}
			}
			updateDirections();
			currentDirection = directions.get(0);
			if (eatsFood()) {
				addSection();
				gui.setFood(null);
			}
			stepsTaken++;
			try {
				sleeper.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public String getCurrentDirection() {
		return currentDirection;
	}
	
	public void queueDirection(String direction) {
		while (numberOfSections >= directionQueue.size()) {
			directionQueue.add(direction);
		}
	}
	
	public void updateDirections() {
		if (!directionQueue.isEmpty()) {
			directions.add(0, directionQueue.get(directionQueue.size() - 1));
			directionQueue.remove(0);
			directions.remove(directions.size()-1);
		}
	}
	
	public ArrayList<String> getDirectionArray() {
		return directions;
	}
	
	public void moveUp(int index) {
		positions.get(index).y -= getYSize();
		if (hasCrashed(positions.get(0))) {
			this.alive = false;
		} 
		setChanged();
		notifyObservers();
	}
	
	public void moveRight(int index) {
		positions.get(index).x += getYSize();
		if (hasCrashed(positions.get(0))) {
			this.alive = false;
		} 
		setChanged();
		notifyObservers();
	}
	
	public void moveDown(int index) {
		positions.get(index).y += getYSize();
		if (hasCrashed(positions.get(0))) {
			this.alive = false;
		}
		setChanged();
		notifyObservers();
	}
	
	public void moveLeft(int index) {
		positions.get(index).x -= getYSize();
		if (hasCrashed(positions.get(0))) {
			this.alive = false;
		} 
		setChanged();
		notifyObservers();
	}
	
	public boolean hasCrashed(Point p) {
		if (p.x < getMinX() || 
				p.y < getMinY() ||
			(p.x + getXSize()) > getMaxX() ||
			(p.y + getYSize() > getMaxY())) {
			
			return true;
					
		} else {
			for (int i = 1; i < positions.size(); i++) {
				if (positions.get(i).getX() == positions.get(0).getX() &&
						positions.get(i).getY() == positions.get(0).getY()) {
					return true;
				}
			}
			return false;
		}
			
	}
	public boolean eatsFood() {
		return (positions.get(0).getX() == gui.getFood().getX() &&
				positions.get(0).getY() == gui.getFood().getY());
	}
	
	public void addSection() {
		this.numberOfSections++;
		switch (directions.get(positions.size()-1)) {
			case "Up":
				positions.add(new Point(positions.get(positions.size()-1).x, 
						positions.get(positions.size()-1).y + getYSize()));
				directions.add(directions.get(directions.size()-1));
				break;
			case "Down":
				positions.add(new Point(positions.get(positions.size()-1).x, 
						positions.get(positions.size()-1).y - getYSize()));
				directions.add(directions.get(directions.size()-1));
				break;
			case "Left":
				positions.add(new Point(positions.get(positions.size()-1).x + getXSize(),
						positions.get(positions.size()-1).y));
				directions.add(directions.get(directions.size()-1));
				break;
			case "Right":
				positions.add(new Point(positions.get(positions.size()-1).x - getXSize(),
						positions.get(positions.size()-1).y));
				directions.add(directions.get(directions.size()-1));
				break;
		}
		
	}
	
	public boolean contains(int x, int y) {
		for (Point pos: positions) {
			if (x == pos.x && y == pos.y) {
				return true;
			}
		}
		return false;
	}
	
	

}
