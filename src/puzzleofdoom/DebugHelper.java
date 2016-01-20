package puzzleofdoom;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DebugHelper {
	private PrintWriter writer = null;

	/** Holder */
	private static class SingletonHolder {
		private final static DebugHelper instance = new DebugHelper();
	}

	private static DebugHelper getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * Ctor
	 */
	private DebugHelper() {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat(
					"yyyy_MM_dd_HH_mm_ss");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String fileName = "logs/logfile_" + strDate + ".txt";
			writer = new PrintWriter(fileName, "UTF-8");
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					DebugHelper.close();
				}
			}));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static void close() {
		if (getInstance().writer != null)
			getInstance().writer.close();
	}
	
	public static void Log(String str) {
		System.out.println(str);
		getInstance().writer.println(str);
	}

	public static void Log(Integer integer) {
		System.out.println(integer);
		getInstance().writer.println(integer);
	}
	
	public static void LogWithStart(String str) {
		System.out.print("[START] ");
		getInstance().writer.print("[START] ");
		Log(str);
	}
	
	public static void LogWithEnd(String str) {
		System.out.print("[END] ");
		getInstance().writer.print("[END] ");
		Log(str);
	}
}
