


import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;


public class SnakeComponent extends JPanel {
	
	private SnakeView view;
	private ScoreView score;
	private Snake snake;
	private Food food;
	private SnakeAI ai;
	private int width = 500;
	private int height = 500;
	
	public SnakeComponent() {
		this.setLayout(new BorderLayout());
		this.setFocusable(true);
		this.requestFocus();
//		this.addFocusListener(new FocusListener() {
//
//			@Override
//			public void focusGained(FocusEvent e) {
//				// TODO Auto-generated method stub
//				
//				play();
//			}
//
//			@Override
//			public void focusLost(FocusEvent e) {
//				// TODO Auto-generated method stub
////				requestFocus();
//			}
//			
//		});
		
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
		ai = new SnakeAI(snake);
//		ai.run();
		
		//Add components
		this.add(score, BorderLayout.PAGE_END);
		this.add(view, BorderLayout.CENTER);
		
		
//		Random rand = new Random();
//		//Runs the game
//		while(snake.isAlive()) {
//			if (food == null) {
//				food = new Food(20+rand.nextInt((width-40)/100)*100, 20+rand.nextInt((height - 60)/100)*100);
//				while(snake.contains(food.getX(), food.getY())) {
//						food = new Food(20+rand.nextInt((width-40)/100)*100, 20+rand.nextInt((height - 60)/100)*100);
//				}
//			}
//			
//			snake.move();
//		}
		
		
	}
	
	public void play() {
		Random rand = new Random();
		//Runs the game
		while(snake.isAlive()) {
			if (food == null) {
				food = new Food(20+rand.nextInt((width-40)/100)*100, 20+rand.nextInt((height - 60)/100)*100);
				while(snake.contains(food.getX(), food.getY())) {
						food = new Food(20+rand.nextInt((width-40)/100)*100, 20+rand.nextInt((height - 60)/100)*100);
				}
			}
			
			snake.move();
			ai.run();
		}
	}
	
	public SnakeAI getAI() {
		return ai;
	}
	
	public Food getFood() {
		return food;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}
}
