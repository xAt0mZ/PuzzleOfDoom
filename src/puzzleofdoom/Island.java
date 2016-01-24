package puzzleofdoom;

import helpers.DebugHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Island {
	public int number;
	public List<Board> boards;
	public Long boardsCount;
	public Long piecesCount;
	private List<Board> boardListSelect;
	private List<Board> selectedBoards;

	/**
	 * 
	 * @param num : island number
	 * @param bCount : boards count
	 * @param pCount : pieces by board
	 */
	public Island(int num, Long bCount, Long pCount) {
		number = num;
		piecesCount = pCount;
		boardsCount = bCount;
		boards = new ArrayList<Board>();
		DebugHelper.Log("  -- New Island " + number);
	}

	public void generate() {
		for (int i = 0; i < boardsCount; i++) {
			Board board = new Board(i, piecesCount);
			board.generate();
			boards.add(board);
		}
	}
	
	public void evaluate() {
		DebugHelper.LogWithStart("Island " + number);
		for (Board board : boards) {
			board.evaluate();
		}
		DebugHelper.LogWithEnd("Island " + number);
	}
	
	public void selection(){
		int	remainingToSelect = 100; //Remplacer la valeur par une macro / repr�sente le nombre de board restant � selectionner
		long minValue = 1;
		long maxValue = boardsCount+1;
		long random;
		Board boardSelected;
		// Il faut d'abords creer un tableau de board
		// Une board appara�t x fois dans le tableau
		// ou x = rating
		// ex : si b1.rating = 3 et b2.rating=2
		// tabBoard = [b1][b1][b1][b2][b2]
		// Ensuite, tant que remainingToSelect est sup�rieur � 0
		// on selection au hasard une board, une fois s�lectionn�e,
		// toutes les occurences de la board s�lectionn�e sont retir�es
		// du tableau
		for (Board board : boards){
			for (int i=0;i < board.rating;i++){
				boardListSelect.add(board);
			}
		}
		// Normalement arriv� ici la liste pour la selection de board est construite correctement		
		while (remainingToSelect > 0){
			// traitement de la selection
			random = (long)(Math.random() * (maxValue-minValue)) + minValue;
			boardSelected = boardListSelect.get((int)random);
			selectedBoards.add(boardSelected);
			for (Board board : boardListSelect){
				if (board.number == boardSelected.number)
					boardListSelect.remove(board);
			}
			remainingToSelect--;
		}
	}

}
