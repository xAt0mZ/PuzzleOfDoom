package puzzleofdoom;

import helpers.DebugHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Island {
	public int number;
	public List<Board> boards;
	public Long boardsCount;
	public Long piecesCount;
	public List<Board> boardListSelect;
	public List<Board> selectedBoards;
	public int selectionNumber = 10;

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
		boardListSelect = new ArrayList<Board>();
		selectedBoards = new ArrayList<Board>();
		
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
		// Il faut d'abords creer un tableau de board
		// Une board appara�t x fois dans le tableau
		// ou x = rating
		// ex : si b1.rating = 3 et b2.rating=2
		// tabBoard = [b1][b1][b1][b2][b2]
		// Ensuite, tant que remainingToSelect est sup�rieur � 0
		// on selection au hasard une board, une fois s�lectionn�e,
		// toutes les occurences de la board s�lectionn�e sont retir�es
		// du tableau
		
		
		System.out.println("Avant selection :");
		System.out.println("------------------------------------");
		

		int	remainingToSelect = selectionNumber;
		Board boardSelected;
		
		for (Board board : boards){
			System.out.println(board.number + "  " + board.rating);
			for (int i=0;i < board.rating;i++){
				boardListSelect.add(board);
				System.out.print(board.number);
			}
			System.out.println("");
		}
		System.out.println("------------------------------------");
		System.out.println("Apres selection :");

		// Normalement arriv� ici la liste pour la selection de board est construite correctement		
		System.out.println("------------------------------------");
		if (boardListSelect.size() == 0)
			return ;
		
		Random rand = new Random();
		while (remainingToSelect > 0){
			
			int maxValue = boardListSelect.size();
			boardSelected = boardListSelect.get(rand.nextInt(maxValue));
			selectedBoards.add(boardSelected);
			
			//clean probas
			ArrayList<Board> tmpList = new ArrayList<Board>();
			tmpList.add(boardSelected);
			boardListSelect.removeAll(tmpList);
			
			
			remainingToSelect--;
		}
		for (Board board : boardListSelect){
			System.out.print(board.number);
		}
		System.out.println("------------------------------------");
		for (Board board : selectedBoards){
			System.out.print(board.number);
		}
		System.out.println("------------------------------------");
	}

}
