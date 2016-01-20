package puzzleofdoom;

import java.util.ArrayList;
import java.util.List;

public class Island {
	public int number;
	public List<Board> boards;
	public Long boardsCount;
	public Long piecesCount;

	/**
	 * 
	 * @param num : island number
	 * @param bCount : boards count
	 * @param pCount : pieces by board
	 */
	public Island(int num, Long bCount, Long pCount) {
		number = num;
		piecesCount = pCount;
		boardsCount = bCount;
		boards = new ArrayList<Board>();
		DebugHelper.Log("  -- New Island " + number);
	}

	public void generate() {
		for (int i = 0; i < boardsCount; i++) {
			Board board = new Board(i, piecesCount);
			board.generate();
			boards.add(board);
		}
	}
	
	public void evaluate() {
		DebugHelper.LogWithStart("Island " + number);
		for (Board board : boards) {
			board.evaluate();
		}
		DebugHelper.LogWithEnd("Island " + number);
	}

}
