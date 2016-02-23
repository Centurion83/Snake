
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScoreView extends JLabel implements Observer {
	
	private Snake snake;
	
	public ScoreView(Snake snake) {
		this.snake = snake;
	}

	@Override
	public void update(Observable o, Object arg) {
		setText("Current Score: " + (snake.getNumberOfSections() - 3));
	}
	
	

}
