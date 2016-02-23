

import java.util.Random;

public class SnakeAI {
	private Snake snake;
//	private SnakeGUI gui;
	private String[] directions = new String[] {"Up", "Left", "Right", "Down"};
	private Random rand;
	
	public SnakeAI(Snake snake) {
		this.snake = snake;
//		this.gui = gui;
	}
	
	public void run() {
//		if (snake.isAlive()) {
			snake.queueDirection(directions[rand.nextInt(4)]);
//		}
	}
}
