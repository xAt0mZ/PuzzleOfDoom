package puzzleofdoom;

import helpers.DebugHelper;

import java.util.ArrayList;
import java.util.List;

public class IslandManager {
	public List<Island> islands;
	public Long islandsCount;
	public Long boardsCount;
	public Long piecesCount;
	public int coeffRepartition;
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
		coeffRepartition = 2;
	}

	public void run() {
		generate();
		evaluate(0);
		while (!hasSolution && !Thread.currentThread().isInterrupted()) {
			DebugHelper.Log("\n>LOOP<");
			crossover();
			selection();
			evaluate(1);
			merge();
			migrate();
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

	private void migrate(){
		boolean needMigration = false;
		int i = 0;
		int j = 0;
		int k = 0;
		ArrayList<ArrayList<Board>> boardToMigrate = new ArrayList<ArrayList<Board>>();
		
		DebugHelper.LogWithStart("MIGRATE");
		for (Island island : islands){
			if (island.needToMigrate()==true){
				needMigration = true;
				break;
			}
		}
		if (needMigration == true){
			for (Island island : islands){
				boardToMigrate.add(island.migrate(islandsCount, coeffRepartition));
			}
		}
		
		// Arriver ici la liste de liste de board a migrer est remplie
		for (ArrayList<Board> boardList : boardToMigrate)
		{
			for (Island island : islands)
			{
				if (i != j)
				{
					while (k < coeffRepartition && boardList.size() > 0)
					{
						// On ajoute
						if (j == 9)
						{
							DebugHelper.Log("debug");
						}
						DebugHelper.Log("Move : ");
						DebugHelper.Log(boardList.get(k).number.toString());
						DebugHelper.Log("From isle : "+i);
						DebugHelper.Log("To isle :"+j);
						island.boards.add(boardList.get(k));
						boardList.remove(k);
						k++;
					}
				}
				k = 0;
				j++;
				/*for (int x = 0; x < island.boards.size(); x++)
					island.boards.get(x).number = (long)i;
				*/
			}
			j = 0;
			i++;
		}
		DebugHelper.LogWithEnd("MIGRATE");
	}
}
