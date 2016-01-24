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
		DebugHelper.LogWithStart("Board " + number);
		for (Piece piece : pieces) {
			int x = piece.position % 16;
			int y = piece.position / 16;
			PiecesHelper.getPieceColorsByNumber(piece.number);
			if (x == 0)
				;
			else if (x == 15)
				;
			if (y == 0)
				;
			else if (y == 15)
				;
		}
		DebugHelper.LogWithEnd("Board " + number);
	}
}
