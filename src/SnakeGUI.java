

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
		this.setVisible(true);
		
		//Set up and run game
		AILearning brain = new AILearning();
		for (int i = 1; i <= 10; i++) {
			snakeComp = new SnakeComponent(brain);
			this.add(snakeComp);
			snakeComp.play();
			System.out.println("Steps: " + snakeComp.getSnake().stepsTaken() + " Score: " + (snakeComp.getSnake().getNumberOfSections()-3));
		}
	}
	
	
	public static void main(String[] args) {
			SnakeGUI frame = new SnakeGUI();
	}
}
