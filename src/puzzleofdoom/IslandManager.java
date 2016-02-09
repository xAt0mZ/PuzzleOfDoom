package puzzleofdoom;

import helpers.DebugHelper;

import java.util.ArrayList;
import java.util.List;

public class IslandManager {
	public List<Island> islands;
	public Long islandsCount;
	public Long boardsCount;
	public Long piecesCount;
	static public boolean hasSolution = false;

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
		evaluate(0);
		while (!hasSolution) {
			DebugHelper.Log("\n>LOOP<");
			crossover();
			selection();
			evaluate(1);
			merge();
			//migrate();
		}
		
		DebugHelper.Log("\nRecapitulatif\n");
		
		for (Island i : islands) {
			DebugHelper.Log("Island : " + i.number);
			for (Board b : i.boards) {
				DebugHelper.Log("Board : num=" + b.number + "  rating=" + b.rating);
			}
		}
	}

	private void generate() {
		DebugHelper.LogWithStart("GEN");
		for (int i = 0; i < islandsCount; i++) {
			Island island = new Island(i, boardsCount, piecesCount);
			island.generate();
			islands.add(island);
		}
		DebugHelper.LogWithEnd("GEN");
	}

	private void evaluate(int type) {
		DebugHelper.LogWithStart("EVAL");
		for (Island island : islands) {
			island.evaluate(type);
		}
		DebugHelper.LogWithEnd("EVAL");
	}

	private void crossover() {
		DebugHelper.LogWithStart("CROSS");
		for (Island island : islands) {
			island.crossover();
		}
		DebugHelper.LogWithEnd("CROSS");
	}

	private void selection() {
		DebugHelper.LogWithStart("SELECT");
		for (Island island : islands) {
			island.selection();
		}
		DebugHelper.LogWithEnd("SELECT");
	}

	private void merge() {
		DebugHelper.LogWithStart("MERGE");
		for (Island island : islands) {
			island.merge();
		}
		DebugHelper.LogWithEnd("MERGE");
	}

}
