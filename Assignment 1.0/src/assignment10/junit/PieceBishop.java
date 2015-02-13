package assignment10.junit;

/*The bishop can move any number of squares diagonally,
 *but may not leap over other pieces.*/
public class PieceBishop extends Piece{

	public int can_move(int x, int y, boolean turn)
	{
		if(turn!=this.color)
			return 0;
		if(x == this.x && y == this.y) //not moving
			return 0;
		if(Math.abs(x-this.x) == Math.abs(y-this.y))//diagonal
		{	
			
			return 1;
		}
		return 0;
	}
	
	public boolean leap_over(int x, int y, Piece p)
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
		return false;
	}
	
	public int type()
	{
		return 2;
	}
	
	public void print()
	{
		System.out.println("I am "+this.color+" Bishop at "+this.position());	
	}
}

