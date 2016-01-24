package puzzleofdoom;

public enum Direction {
	NORTH(0), EAST(1), SOUTH(2), WEST(3);
	private final int value;

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
	
    public int getValue() {
        return value;
    }
}
