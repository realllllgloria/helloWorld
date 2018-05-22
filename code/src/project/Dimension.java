package project;

public class Dimension {
	private int row;
	private int col;
	
	/**
	 * Do not touch
	 * Whether the board is board[row][col] or board[col][row] depends
	 * on this constructor
	 * @param col
	 * @param row
	 */
	public Dimension(int col, int row) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public void printDims() {
		System.out.printf(this.row + "-" + this.col);
	}
	
	public String getDims() {
		return this.row + "-" + this.col;
	}
	
}
