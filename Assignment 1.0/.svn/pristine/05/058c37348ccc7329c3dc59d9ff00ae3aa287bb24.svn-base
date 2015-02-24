package assignment10.junit;

/*The rook can move any number of squares along any rank or file, 
 * but may not leap over other pieces.*/
public class PieceRook extends Piece {
	public int can_move(int x, int y, boolean turn)
	{
		if(turn!=this.color)
			return 0;
		if(x == this.x && y == this.y) //not moving
			return 0;
		if(x == this.x || y == this.y) //same rank/file
			return 1;
		return 0;
	}
	
	public boolean leap_over(int x, int y, Piece p)//x,y-destination
	//return true if there's leap_over
	{
		int p_x = p.position()[0];
		int p_y = p.position()[1];
		//if(p_x==x && p_y==y) //already occupied
		//return true;
		if(p_x == this.x)//same file
		{
			if((y<p_y && p_y < this.y)||(this.y<p_y && p_y < y))
				return true;//there's leap over
		}
		if(p_y == this.y)//same rank
		{
			if((x<p_x && p_x < this.x)||(this.x<p_x && p_x < x))
				return true;//there's leap over
		}
		return false;
	}
	
	public int type()
	{
		return 4;
	}

	public void print()
	{
		System.out.println("I am "+this.color+" Rook at "+this.position());	
	}
}

