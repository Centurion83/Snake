
public class AILearning {
	private int rows, cols;
	private int[][] matrix;
	
	public AILearning() {
		rows = 22;
		cols = 22;
		matrix = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public int getValue(int row, int col) {
		return matrix[row][col];
	}
	
	public void setValue(int row, int col, int value) {
		matrix[row][col] = value;
	}
	
	public void displayMatrix() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(matrix[i][j] + "|");
			}
		System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		AILearning al = new AILearning();
		al.displayMatrix();
		al.setValue(10, 15, 100);
		al.displayMatrix();
	}
}
