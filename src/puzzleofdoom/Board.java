package puzzleofdoom;

import java.util.ArrayList;
import java.util.List;

import helpers.DebugHelper;
import helpers.GeneratorHelper;

public class Board {
	public Long number;
	public List<Piece> pieces;
	public Long piecesCount;
	public int rating;

	/**
	 * 
	 * @param num
	 *            : board number
	 * @param pCount
	 *            : pieces count
	 */
	public Board(Long num, Long pCount) {
		number = num;
		piecesCount = pCount;
		pieces = new ArrayList<Piece>();
		rating = 0;
		DebugHelper.Log("      -- New Board " + number);
	}

	public void generate() {
		GeneratorHelper.resetPositionCounter();
		for (int i = 0; i < piecesCount; i++) {
			Piece piece = new Piece(i, GeneratorHelper.nextRandomPosition(),
					GeneratorHelper.randomDirection());
			pieces.add(piece);
		}
		DebugHelper.Log("");
	}

	public void evaluate() {
		rating = 0;
		DebugHelper.LogWithStart("Board " + number);
		for (Piece piece : pieces) {
			int x = piece.position % 16;
			int y = piece.position / 16;
			int[] colors = piece.colors;

//			DebugHelper.Log("EVALUATING PIECE : " + "[" + x + " " + y + "]  N="
//					+ colors[Direction.NORTH.getValue()] + "  E="
//					+ colors[Direction.EAST.getValue()] + "  S="
//					+ colors[Direction.SOUTH.getValue()] + "  W="
//					+ colors[Direction.WEST.getValue()]);

			// check left
			if (x == 0 && colors[Direction.WEST.getValue()] == 0) {
//				DebugHelper.Log(" -- +1 --");
				rating += 1;
			}
			// check top
			if (y == 0 && colors[Direction.NORTH.getValue()] == 0) {
//				DebugHelper.Log(" -- +1 --");
				rating += 1;
			}
			// check right
			if (x == 15) {
				if (colors[Direction.SOUTH.getValue()] == 0) {
					rating += 1;
//					DebugHelper.Log(" -- +1 --");
				}
			} else {
				// int[] tmp = PiecesHelper.getPieceColors(pieces
				// .get(piece.position + 1));

				Piece tmp = pieces.get(piece.position + 1);

//				DebugHelper.Log(tmp.number + "   [" + tmp.position % 16 + " "
//						+ tmp.position / 16 + "]  N="
//						+ tmp.colors[Direction.NORTH.getValue()] + "  E="
//						+ tmp.colors[Direction.EAST.getValue()] + "  S="
//						+ tmp.colors[Direction.SOUTH.getValue()] + "  W="
//						+ tmp.colors[Direction.WEST.getValue()]);
//				DebugHelper.Log("droite cmp : "
//						+ colors[Direction.EAST.getValue()] + "  "
//						+ tmp.colors[Direction.WEST.getValue()]);

				if (colors[Direction.EAST.getValue()] == tmp.colors[Direction.WEST
						.getValue()]) {
					//DebugHelper.Log(" -- +1 --");
					rating += 1;
				}
			}
			// check bottom
			if (y == 15) {
				if (colors[Direction.EAST.getValue()] == 0)
					rating += 1;
			} else {
				// int[] tmp = PiecesHelper.getPieceColors(pieces
				// .get(piece.position + 16));
				Piece tmp = pieces.get(piece.position + 16);
				
//				DebugHelper.Log(tmp.number + "   [" + tmp.position % 16 + " "
//						+ tmp.position / 16 + "]  N="
//						+ tmp.colors[Direction.NORTH.getValue()] + "  E="
//						+ tmp.colors[Direction.EAST.getValue()] + "  S="
//						+ tmp.colors[Direction.SOUTH.getValue()] + "  W="
//						+ tmp.colors[Direction.WEST.getValue()]);
//				DebugHelper.Log("dessous cmp : "
//						+ colors[Direction.SOUTH.getValue()] + "  "
//						+ tmp.colors[Direction.NORTH.getValue()]);

				if (colors[Direction.SOUTH.getValue()] == tmp.colors[Direction.NORTH
						.getValue()]) {
//					DebugHelper.Log(" -- +1 --");
					rating += 1;
				}
			}
			//DebugHelper.Log("");
		}
		DebugHelper.Log("  rating :" + rating);
		DebugHelper.LogWithEnd("Board " + number);
		if (rating > 400)
		{
			for (Piece p : pieces)
			{
				DebugHelper.Log("Piece : "+p.number);
			}
		}
		
	}
}
