package puzzleofdoom;

import java.util.ArrayList;
import java.util.Random;

public final class GeneratorHelper {

	private int maxPosition = 255;
	private Random rand;

	private ArrayList<Integer> positions;
	private ArrayList<Integer> saveList;

	/** Holder */
	private static class SingletonHolder {
		private final static GeneratorHelper instance = new GeneratorHelper();
	}

	private static GeneratorHelper getInstance() {
		return SingletonHolder.instance;
	}

	private GeneratorHelper() {
		rand = new Random();
		saveList = new ArrayList<Integer>();
		for (int i = 0; i <= maxPosition; i++) {
			Integer item = new Integer(i);
			saveList.add(i, item);
		}
	}

	public static int nextRandomPosition() {
		int nb = getInstance().rand.nextInt(getInstance().positions.size());
		Integer item = getInstance().positions.get(nb);
		getInstance().positions.remove(nb);
		return item.intValue();
	}

	public static void resetPositionCounter() {
		getInstance().positions = new ArrayList<Integer>(getInstance().saveList);
	}

	public static Direction randomDirection() {
		return (Direction.fromValue(getInstance().rand.nextInt(4)));
	}

}
