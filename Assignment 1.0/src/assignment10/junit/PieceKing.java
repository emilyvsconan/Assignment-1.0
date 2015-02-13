package assignment10.junit;

/*The king moves one square in any direction.*/
public class PieceKing extends Piece{

	public int can_move(int x, int y, boolean turn)
	{
		if(turn!=this.color)
			return 0;
		if(x == this.x && y == this.y) //not moving
			return 0;
		if(Math.abs(x-this.x)>1) 
			return 0;
		if(Math.abs(y-this.y)>1)
			return 0;
		return 1;
	}
	
	public boolean leap_over(int x, int y, Piece p)
	//return true if there's leap_over
	{
		int p_x = p.position()[0];
		int p_y = p.position()[1];
		//if(p_x==this.x && p_y==this.y)//occupied
		//return true;
		return false;
	}
	
	public int type()
	{
		return 0;
	}
	public void print()
	{
		System.out.println("I am "+this.color+" King at "+this.position());	
	}
}

