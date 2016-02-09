package puzzleofdoom;

import helpers.DebugHelper;

public class Main {

	private static Long islands = new Long(1);
	private static Long boards = new Long(10);
	private static Long pieces = new Long(256);

	public static void main(String[] args) {
		DebugHelper.Log("[BEGIN OF PROGRAM]");
		DebugHelper.Log("Islands:" + islands + " boards:" + boards + " pieces:"
				+ pieces);
		IslandManager manager = new IslandManager(islands, boards, pieces);
		manager.run();
		DebugHelper.Log("[END OF PROGRAM]");
	}

}
