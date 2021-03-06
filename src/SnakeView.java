
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


@SuppressWarnings("serial")
public class SnakeView extends JPanel implements Observer {
	
	private Snake snake;
	private SnakeComponent gui;
	
	public SnakeView(Snake snake, SnakeComponent gui) {
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.snake = snake;
		this.gui = gui;
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Paint food
		if (gui.getFood() != null) {
			g.setColor(Color.green);
			g.fillRect(gui.getFood().x, gui.getFood().y, snake.getXSize(), snake.getYSize());
		}
		
		g.setColor(Color.black);
		//Draw border
		g.drawRect(snake.getMinX(), snake.getMinY(), snake.getMaxX()-snake.getMinX(), snake.getMaxY()-snake.getMinY());
		
		for (int i  = 0; i <snake.getPositions().size(); i++) {
			if (!snake.isAlive()) {
				g.setColor(Color.red);
			}
			g.fillRect(snake.getPositions().get(i).x, snake.getPositions().get(i).y, snake.getXSize(), snake.getYSize());
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		
	}

}
