package puzzleofdoom;

import helpers.DebugHelper;
import helpers.GeneratorHelper;
import helpers.PiecesHelper;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public int number;
	public List<Piece> pieces;
	public Long piecesCount;
	public int rating;
	public int life;

	/**
	 * 
	 * @param num
	 *            : board number
	 * @param pCount
	 *            : pieces count
	 */
	public Board(int num, Long pCount) {
		number = num;
		piecesCount = pCount;
		pieces = new ArrayList<Piece>();
		rating = 0;
		life = 5;
		DebugHelper.Log("      -- New Board " + number);
	}

	public void generate() {
		GeneratorHelper.resetPositionCounter();
		for (int i = 0; i < piecesCount; i++) {
			Piece piece = new Piece(i, GeneratorHelper.nextRandomPosition(),
					GeneratorHelper.randomDirection());
			pieces.add(piece);
		}
	}

	public void evaluate() {
		rating = 0;
		DebugHelper.LogWithStart("Board " + number);
		for (Piece piece : pieces) {
			int x = piece.position % 16;
			int y = piece.position / 16;
			int[] colors = PiecesHelper.getPieceColors(piece);

			// check left
			if (x == 0 && colors[Direction.WEST.getValue()] == 0)
				rating += 1;
			// check top
			if (y == 0 && colors[Direction.NORTH.getValue()] == 0)
				rating += 1;
			// check bottom
			if (x == 15) {
				if (colors[Direction.SOUTH.getValue()] == 0)
					rating += 1;
			} else {
				Piece tmp = pieces.get(piece.position + 16);
			}
			// check right
			if (y == 15) {
				if (colors[Direction.EAST.getValue()] == 0)
					rating += 1;
			} else {
			}
		}
		DebugHelper.LogWithEnd("Board " + number);
	}
}
