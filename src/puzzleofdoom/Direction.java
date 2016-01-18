package puzzleofdoom;

public enum Direction {
	NORTH(0), SOUTH(1), WEST(2), EAST(3);
	private int value;

	private Direction(int val) {
		value = val;
	}
	
	public static Direction fromValue(int value) {
		for (Direction my : Direction.values()) {
			if (my.value == value) {
				return my;
			}
		}
		return null;
	}
}
