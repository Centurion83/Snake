

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;


public class SnakeGUI extends JFrame{
	
	private SnakeView view;
	private SnakeComponent snakeComp;
	private ScoreView score;
	private Snake snake;
	private Food food;
	private int width = 500;
	private int height = 500;
	
	public SnakeGUI() {
		//Set up frame
		this.setSize(width, height);
		this.setTitle("Snake");
		this.setFocusable(false);
		snakeComp = new SnakeComponent();
		this.add(snakeComp);
		this.setVisible(true);
		snakeComp.play();
	}
	
	
	public static void main(String[] args) {
		SnakeGUI frame = new SnakeGUI();
	}
}
