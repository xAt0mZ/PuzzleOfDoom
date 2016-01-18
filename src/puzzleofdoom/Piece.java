package puzzleofdoom;

public class Piece {
	public int number;
	public int position;
	public Direction direction;

	/**
	 * 
	 * @param pos : position on board
	 * @param num : piece number
	 * @param dir : piece orientation
	 */
	public Piece(int pos, int num, Direction dir) {
		position = pos;
		number = num;
		direction = dir;
		DebugHelper.Log("        -- New Piece --  position=" + position + "  number=" + number + "  direction=" + direction);
	}
}
