package puzzleofdoom;

public enum Direction {
	NORTH(0), EAST(1), SOUTH(2), WEST(3);
	private final int value;

	private Direction(final int val) {
		value = val;
	}

	public static Direction fromValue(int value) {
		switch (value) {
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		}
		return NORTH;
	}

	public int getValue() {
		return value;
	}
}
