package puzzleofdoom;

import helpers.DebugHelper;
import helpers.PiecesHelper;

public class Piece {
	public int number;
	public int position;
	public Direction direction;
	public int[] colors;

	/**
	 * 
	 * @param pos
	 *            : position on board
	 * @param num
	 *            : piece number
	 * @param dir
	 *            : piece orientation
	 */
	public Piece(int pos, int num, Direction dir) {
		position = pos;
		number = num;
		direction = dir;
		colors = PiecesHelper.getPieceColors(this);
		DebugHelper.Log("    -- num:" + num + "  dir:" + dir + "   N="
				+ colors[Direction.NORTH.getValue()] + "  E="
				+ colors[Direction.EAST.getValue()] + "  S="
				+ colors[Direction.SOUTH.getValue()] + "  W="
				+ colors[Direction.WEST.getValue()]);
	}
}
