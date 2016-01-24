package puzzleofdoom;

import helpers.DebugHelper;

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
	}

	public void run() {
		DebugHelper.Log("IslandManager Running");
		generate();
		evaluate();
		// crossover();
		// selection();
		// migrate();
	}

	private void generate() {
		DebugHelper.LogWithStart("Generating islands");
		for (int i = 0; i < islandsCount; i++) {
			Island island = new Island(i, boardsCount, piecesCount);
			island.generate();
			islands.add(island);
		}
		DebugHelper.LogWithEnd("Generating islands");
	}

	private void evaluate() {
		DebugHelper.LogWithStart("Evaluating islands");
		for (Island island : islands) {
			island.evaluate();
		}
		DebugHelper.LogWithEnd("Evaluating islands");
	}
	
	private void selection(){
		DebugHelper.LogWithStart("Selectioning boards");
		for (Island island : islands) {
			island.selection();
		}
	}
	
}
