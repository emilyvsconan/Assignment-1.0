package assignment10.junit;

public class main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piece white_king = new PieceKing();
		white_king.initial(1, 3, false, true, 10);
		System.out.println(white_king.can_move(1, 3, true));
	}

}
