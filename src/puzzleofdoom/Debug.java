package puzzleofdoom;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Debug {
	private static PrintWriter writer = null;

	private Debug() {
	}

	public static void Log(String str) {
		if (writer == null) {
			try {
				SimpleDateFormat sdfDate = new SimpleDateFormat(
						"yyyy_MM_dd_HH_mm_ss");
				Date now = new Date();
				String strDate = sdfDate.format(now);
				String fileName = "logfile_" + strDate + ".txt";
				writer = new PrintWriter(fileName, "UTF-8");
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				    public void run() {
				    	Debug.close();
				    }
				}));
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println(str);
		writer.println(str);
	}

	public static void close() {
		if (writer != null)
			writer.close();
	}
}
