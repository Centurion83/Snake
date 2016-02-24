


import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SnakeComponent extends JPanel {
	
	private SnakeView view;
	private ScoreView score;
	private Snake snake;
	private Point food;
	private SnakeAI ai;
	private int width = 500;
	private int height = 500;
	
	public SnakeComponent() {
		this.setLayout(new BorderLayout());
		this.setFocusable(true);
		this.requestFocus();
		
		//Model
		snake = new Snake(100, 100, width-40, height-60, this);
		
		//View - Score
		score = new ScoreView(snake);
		
		//View - Snake
		view = new SnakeView(snake, this);
		snake.addObserver(view);
		snake.addObserver(score);
		
		//Controller
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 38) { // UP
					if (snake.getCurrentDirection() != "Down") {
						snake.queueDirection("Up");
					}
				} else if (e.getKeyCode() == 39) { //RIGHT
					if (snake.getCurrentDirection() != "Left") {
						snake.queueDirection("Right");
					}
				} else if (e.getKeyCode() == 40) { //DOWN
					if (snake.getCurrentDirection() != "Up") {
						snake.queueDirection("Down");
					}
				} else if (e.getKeyCode() == 37) { //LEFT
					if (snake.getCurrentDirection() != "Right") {
						snake.queueDirection("Left");
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//Add AI
		ai = new SnakeAI(snake, this);
		
		//Add components
		this.add(score, BorderLayout.PAGE_END);
		this.add(view, BorderLayout.CENTER);
		
	}
	
	public void play() {
		Random rand = new Random();
		//Runs the game
		while(snake.isAlive()) {
			if (food == null) {
				food = new Point(20+rand.nextInt((width-40)/100)*100, 20+rand.nextInt((height - 60)/100)*100);
				while(snake.contains(food.x, food.y)) {
						food = new Point(20+rand.nextInt((width-40)/100)*100, 20+rand.nextInt((height - 60)/100)*100);
				}
			}
			
			snake.move();
			ai.run();
		}
	}
	public Snake getSnake() {
		return snake;
	}
	
	public SnakeAI getAI() {
		return ai;
	}
	
	public Point getFood() {
		return food;
	}
	
	public void setFood(Point food) {
		this.food = food;
	}
}
