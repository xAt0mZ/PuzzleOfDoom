package puzzleofdoom;

import java.util.ArrayList;
import java.util.List;

import helpers.DebugHelper;

public class Main {

	private static Long islands = new Long(100);
	private static Long boards = new Long(1000);
	private static Long pieces = new Long(256);

	public static void main(String[] args) {
		DebugHelper.Log("[BEGIN OF PROGRAM]");
		DebugHelper.Log("Islands:" + islands + " boards:" + boards + " pieces:"
				+ pieces);

		IslandManager manager = new IslandManager(islands, boards, pieces);

		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					manager.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				DebugHelper.Log("[W] : Interruption.");
				try {
					t.interrupt();
					t.join();

					DebugHelper.Log("\nRecapitulatif");

					List<Board> maxList = new ArrayList<Board>();

					for (Island i : manager.islands) {
						DebugHelper.Log("\ni:" + i.number);

						Board maxb = new Board(new Long(0), new Long(0));

						for (Board b : i.boards) {
							DebugHelper.Log("b:" + b.number + " r:" + b.rating);
							if (b.rating > maxb.rating)
								maxb = b;
						}
						maxList.add(maxb);

					}

					DebugHelper.Log("\nBEST VALUES");

					for (Board b : maxList) {
						DebugHelper.Log("\nr:" + b.rating);
						b.printBoard();
					}
					DebugHelper.Log("[END OF PROGRAM]");
					DebugHelper.close();
				} catch (InterruptedException e) {
				}
			}
		});
		t.start();
	}

}
