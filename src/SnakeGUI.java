

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class SnakeGUI extends JFrame{
	
	private SnakeComponent snakeComp;
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
		@SuppressWarnings("unused")
		SnakeGUI frame = new SnakeGUI();
	}
}
