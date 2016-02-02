package puzzleofdoom;

import java.util.Comparator;

public class PiecesComparator implements Comparator<Piece> {
	
	/** Holder */
	private static class SingletonHolder {
		private final static PiecesComparator instance = new PiecesComparator();
	}

	public static PiecesComparator getInstance() {
		return SingletonHolder.instance;
	}
	
	private PiecesComparator() {
	
	}
	
    @Override
    public int compare(Piece o1, Piece o2) {
    	return (o1.number - o2.number);
    }
}
