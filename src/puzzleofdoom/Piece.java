package puzzleofdoom;

public class Piece {
	public int number;
	public int[] colors;

	/**
	 * 
	 * @param num : piece number
	 * @param col : piece colors
	 */
	public Piece(int num, int[] col) {
		number = num;
		colors = col;
		Debug.Log("        -- New Piece " + number);
	}
}
