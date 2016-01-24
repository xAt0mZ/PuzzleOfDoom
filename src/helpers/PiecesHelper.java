package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class PiecesHelper {

	private HashMap<Integer, int[]> colorsSave;

	/** Holder */
	private static class SingletonHolder {
		private final static PiecesHelper instance = new PiecesHelper();
	}

	private static PiecesHelper getInstance() {
		return SingletonHolder.instance;
	}

	private PiecesHelper() {
		colorsSave = new HashMap<Integer, int[]>();

		try {
			File file = new File("tiles.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			int key = 0;
			while ((line = bufferedReader.readLine()) != null) {
				int[] tab = new int[4];
				String[] splited = line.split("\\s+");
				for (int i = 0; i < 4; i++) {
					tab[i] = Integer.parseInt(splited[i]);
				}
				colorsSave.put(new Integer(key), tab);
				key++;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// debug
		DebugHelper.Log("");
		Iterator it = colorsSave.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			int[] tab = (int[]) pair.getValue();
			DebugHelper.Log(pair.getKey() + " = [" + tab[0] + "][" + tab[1]
					+ "][" + tab[2] + "][" + tab[3] + "]");
		}
		DebugHelper.Log("");
		// end debug
	}

	public static int[] getPieceColorsByNumber(int number) {
		int[] src = getInstance().getColor(number);
		int[] dest = new int[4];

		System.arraycopy(src, 0, dest, 0, src.length);
		return dest;
	}

	private int[] getColor(int number) {
		return colorsSave.get(number);
	}

}
