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
		DebugHelper.Log("  -- New Island " + number);
	}

	public void generate() {
		for (long i = 0; i < boardsCount; i++) {
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
	
	public void crossover() {
		
		DebugHelper.LogWithStart("Island " + number);
		Random randB1 = new Random();
		Random randB2 = new Random();
		int r2 = randB1.nextInt(selectedBoards.size());
		int r1 = randB2.nextInt(selectedBoards.size());
		Board b1;
		Board b2;
		Board child;
		long pCount = 0;
		for (int i=0; i < selectedBoards.size();i++)
		{
			boardsCount += 1;
			b1 = selectedBoards.get(r1);
			while (r2 == r1){
				r2 = randB2.nextInt(selectedBoards.size());
			}
			b2 = selectedBoards.get(r2);
			child = new Board(boardsCount, pCount);
			DebugHelper.Log("Parent are : ["+b1.number+"] and ["+b2.number+"]");
			DebugHelper.Log("Child is : "+child.number);
			for (Piece piece : b1.pieces){
				child.pieces.add(piece);				
			}
			for (Piece piece : b2.pieces){
				if (child.pieces.contains(piece) == false)
				child.pieces.add(piece);				
			}
			child.piecesCount = (long)child.pieces.size();
			boards.add(child);		
			r2 = randB1.nextInt(selectedBoards.size());
			r1 = randB2.nextInt(selectedBoards.size());
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
		
		ArrayList<Board> boardListSelect = new ArrayList<Board>();
		selectedBoards = new ArrayList<Board>();
		int	remainingToSelect = selectionNumber;
		
		
		
		for (Board board : boards){
			for (int i=0;i < board.rating;i++){
				boardListSelect.add(board);
			}
		}

		// Normalement arriv� ici la liste pour la selection de board est construite correctement		
		if (boardListSelect.size() == 0)
			return ;
		
		Random rand = new Random();
		while (remainingToSelect > 0){
			
			int maxValue = boardListSelect.size();
			Board boardSelected = boardListSelect.get(rand.nextInt(maxValue));
			selectedBoards.add(boardSelected);
			
			//clean probas
			ArrayList<Board> tmpList = new ArrayList<Board>();
			tmpList.add(boardSelected);
			boardListSelect.removeAll(tmpList);	
			remainingToSelect--;
		}
		
		DebugHelper.LogWithoutNewline("Island " + number + "   selected Boards : ");
		for (Board board : selectedBoards){
			DebugHelper.LogWithoutNewline(board.number + " ");
		}
		DebugHelper.Log("");
	}

}
