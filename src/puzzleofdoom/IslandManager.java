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
		selection();
		// migrate();
	}

	private void generate() {
		DebugHelper.LogWithStart("GENERATION");
		for (int i = 0; i < islandsCount; i++) {
			Island island = new Island(i, boardsCount, piecesCount);
			island.generate();
			islands.add(island);
		}
		DebugHelper.LogWithEnd("GENERATION");
	}

	private void evaluate() {
		DebugHelper.LogWithStart("EVALUATION");
		for (Island island : islands) {
			island.evaluate();
		}
		DebugHelper.LogWithEnd("EVALUATION");
	}

	private void crossover() {
		DebugHelper.LogWithStart("CROSSOVER");
		for (Island island : islands) {
			island.crossover();
		}
		DebugHelper.LogWithEnd("CROSSOVER");
	}

	private void selection() {
		DebugHelper.LogWithStart("SELECTION");
		for (Island island : islands) {
			island.selection();
		}
		DebugHelper.LogWithEnd("SELECTION");
	}

}
