package puzzleofdoom;

public class Main {

	private static Long islands = new Long(10);
	private static Long boards = new Long(10);
	private static Long pieces = new Long(256);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IslandManager manager = new IslandManager(islands, boards, pieces);
		manager.run();
	}

}
