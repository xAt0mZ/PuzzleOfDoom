package puzzleofdoom;

import helpers.DebugHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {
	public int number;
	public List<Board> boards;
	public Long boardsCount;
	public Long piecesCount;

	public List<Board> selectedBoards;
	public List<Board> childrenBoards;

	/**
	 * 
	 * @param num
	 *            : island number
	 * @param bCount
	 *            : boards count
	 * @param pCount
	 *            : pieces by board
	 */
	public Island(int num, Long bCount, Long pCount) {
		number = num;
		piecesCount = pCount;
		boardsCount = bCount;
		boards = new ArrayList<Board>();
		// DebugHelper.Log("i:" + number);
	}

	public void generate() {
		for (long i = 0; i < boardsCount; i++) {
			Board board = new Board(i, piecesCount);
			board.generate();
			boards.add(board);
		}
	}

	public void evaluate(int type) {
		// DebugHelper.LogWithStart("Island " + number);
		// DebugHelper.Log("\ni:" + number);
		for (Board board : (type == 0 ? boards : childrenBoards)) {
			board.evaluate();
		}
		// DebugHelper.LogWithEnd("Island " + number);
	}

	public void crossover() {

		// DebugHelper.LogWithStart("Island " + number);
		Random random = new Random();

		Board b1;
		Board b2;
		Board child;

		ArrayList<Board> boardsCopy = new ArrayList<Board>();
		childrenBoards = new ArrayList<Board>();
		long remainingToCreate = boardsCount / 2;

		for (Board board : boards) {
			for (int i = 0; i < board.rating; i++) {
				boardsCopy.add(board);
			}
		}

		// liste de nombres de 0 à piecesCount
		ArrayList<Integer> remainingNumbersSave = new ArrayList<Integer>();
		for (int i = 0; i < piecesCount; i++)
			remainingNumbersSave.add(i);

		while (remainingToCreate > 0) {
			b1 = boardsCopy.get(random.nextInt(boardsCopy.size()));
			do {
				b2 = boardsCopy.get(random.nextInt(boardsCopy.size()));
			} while (b1.equals(b2));

			child = new Board(0l, piecesCount);
			// DebugHelper.Log("p:[" + b1.number + "][" + b2.number + "]");

			ArrayList<Integer> remainingPositions = new ArrayList<Integer>();
			ArrayList<Integer> remainingNumbers = (ArrayList<Integer>) remainingNumbersSave
					.clone();

			for (int i = 0; i < piecesCount; i++) {
				int r = random.nextInt() % 2;
				Piece p;
				if (r == 0) {
					p = b1.pieces.get(i); // get by position
					if (!remainingNumbers.contains(p.number))
						p = b2.pieces.get(i);
				} else {
					p = b2.pieces.get(i);
					if (!remainingNumbers.contains(p.number))
						p = b1.pieces.get(i);
				}

				if (!remainingNumbers.contains(p.number)) {
					remainingPositions.add(i);
					// DebugHelper.Log("    to refill position : " + i);
				} else {
					// DebugHelper.Log(" adding at position " + i +
					// " the number " + p.number);
					child.pieces.add(p);
					final Piece finalp = p;
					remainingNumbers.removeIf(s -> s == finalp.number);
				}
			}

			// for (Integer i : remainingNumbers)
			// DebugHelper.Log("   remaining number " + i);
			// for (Integer i : remainingPositions)
			// DebugHelper.Log("   remaining position " + i);

			for (Integer i : remainingNumbers) {
				int r = random.nextInt() % 2;
				Piece p;
				if (r == 0)
					p = b1.pieces.stream().filter(s -> s.number == i)
							.findFirst().get();
				else
					p = b1.pieces.stream().filter(s -> s.number == i)
							.findFirst().get();
				p.position = remainingPositions.get(0);
				remainingPositions.remove(0);
				// DebugHelper.Log("   adding number " + p.number + " at pos " +
				// p.position);
				child.pieces.add(p);
			}

			// reorder pieces list by position
			child.pieces.sort(PiecesComparator.getInstance());

			childrenBoards.add(child);
			remainingToCreate--;
		}
		// DebugHelper.LogWithEnd("Island " + number);
	}

	public void selection() {
		// Il faut d'abords creer un tableau de board
		// Une board appara�t x fois dans le tableau
		// ou x = rating
		// ex : si b1.rating = 3 et b2.rating=2
		// tabBoard = [b1][b1][b1][b2][b2]
		// Ensuite, tant que remainingToSelect est sup�rieur � 0
		// on selection au hasard une board, une fois s�lectionn�e,
		// toutes les occurences de la board s�lectionn�e sont retir�es
		// du tableau

		ArrayList<Board> boardListSelect = new ArrayList<Board>();
		selectedBoards = new ArrayList<Board>();
		long remainingToSelect = boardsCount / 2;

		for (Board board : boards) {
			for (int i = 0; i < board.rating; i++) {
				boardListSelect.add(board);
			}
		}

		// Normalement arriv� ici la liste pour la selection de board est
		// construite correctement
		if (boardListSelect.size() == 0)
			return;

		Random rand = new Random();
		while (remainingToSelect > 0) {

			int maxValue = boardListSelect.size();
			Board boardSelected = boardListSelect.get(rand.nextInt(maxValue));
			selectedBoards.add(boardSelected);

			// clean probas
			ArrayList<Board> tmpList = new ArrayList<Board>();
			tmpList.add(boardSelected);
			boardListSelect.removeAll(tmpList);
			remainingToSelect--;
		}

		// DebugHelper.LogWithoutNewline("Island " + number
		// + "   selected Boards : ");
		// for (Board board : selectedBoards) {
		// DebugHelper.LogWithoutNewline(board.number + " ");
		// }
		// DebugHelper.Log("");
	}

	public void merge() {
		boards.clear();
		boards.addAll(childrenBoards);
		boards.addAll(selectedBoards);
		for (int i = 0; i < boards.size(); i++) {
			boards.get(i).number = (long) i;
			// DebugHelper.Log(boards.get(i).number + " r:" +
			// boards.get(i).rating);
		}
		// DebugHelper.Log("list:" + boards.size());
	}

}
