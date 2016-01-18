package puzzleofdoom;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public int number;
	public List<Piece> pieces;
	public Long piecesCount;
	public int rating;

	/**
	 * 
	 * @param num : board number
	 * @param pCount : pieces count
	 */
	public Board(int num, Long pCount) {
		number = num;
		piecesCount = pCount;
		pieces = new ArrayList<Piece>();
		rating = 0;
		DebugHelper.Log("      -- New Board " + number);
	}

	public void generate() {
		GeneratorHelper.resetPositionCounter();
		for (int i = 0; i < piecesCount; i++) {
			Piece piece = new Piece(i, GeneratorHelper.nextRandomPosition(), GeneratorHelper.randomDirection());
			pieces.add(piece);
		}
	}

	public void evaluate() {

	}
}
