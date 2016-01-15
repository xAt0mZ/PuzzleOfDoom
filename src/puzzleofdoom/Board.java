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
		Debug.Log("      -- New Board " + number);
	}

	public void generate() {
		for (int i = 0; i < piecesCount; i++) {
			Piece piece = new Piece(i, new int[4]);
			pieces.add(piece);
		}
	}

	public void evaluate() {

	}
}
