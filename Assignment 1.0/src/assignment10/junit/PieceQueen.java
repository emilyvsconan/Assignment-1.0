package assignment10.junit;

/*The queen combines the power of the rook and bishop and can move any 
 *number of squares along rank, file, or diagonal, but it may not leap 
 *over other pieces.
 */
public class PieceQueen extends Piece{

	public int can_move(int x, int y, boolean turn)
	{
		if(turn!=this.color)
			return 0;
		if(x == this.x && y == this.y) //not moving
			return 0;
		if(x == this.x || y == this.y) //same rank/file
			return 1;
		if(Math.abs(x-this.x) == Math.abs(y-this.y))//diagonal
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
		if(p_x-this.x == p_y-this.y)// on the \ diagonal
		{
			if(p_x<this.x && p_x>x && p_y<this.y && p_y>y)//x<p_x<this.x, y<p_y<this.y
				return true; //there's leap over
		}
		if(p_x - this.x == -(p_y - this.y))// on the / diagonal
		{
			if(p_x<this.x && p_x>x && p_y>this.y && y>p_y)//x<p_x<this.x, y>p_y>this.y
				return true; //there's leap over
			if(p_x>this.x && p_x<x && p_y<this.y && y<p_y)//x>p_x>this.x, y<p_y<this.y
				return true; //there's leap over
		}
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
		return 1;
	}
	
	public void print()
	{
		System.out.println("I am "+this.color+" Queen at "+this.position());	
	}
}
