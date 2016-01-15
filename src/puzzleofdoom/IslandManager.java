package puzzleofdoom;

import java.util.ArrayList;
import java.util.List;

public class IslandManager {
	public List<Island> islands;
	public Long islandsCount;
	public Long boardsCount;
	public Long piecesCount;

	/**
	 * 
	 * @param count
	 *            : islands count
	 * @param boards
	 *            : boards by island
	 * @param pieces
	 *            : pieces by boards
	 */
	public IslandManager(Long count, Long boards, Long pieces) {
		islandsCount = count;
		piecesCount = pieces;
		boardsCount = boards;
		islands = new ArrayList<Island>();
		Debug.Log("New Island manager");
	}

	public void run() {
		generate();
	}

	private void evaluate() {

	}

	private void generate() {
		for (int i = 0; i < islandsCount; i++) {
			Island island = new Island(i, boardsCount, piecesCount);
			island.generate();
			islands.add(island);
		}
	}
}
