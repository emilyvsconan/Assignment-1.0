package assignment10.junit;

/*The knight moves to any of the closest squares that are not on the same rank, 
 *file, or diagonal, thus the move forms an "L"-shape: two squares vertically and one 
 *square horizontally, or two squares horizontally and one square vertically. 
 *The knight is the only piece that can leap over other pieces.*/
public class PieceKnight extends Piece{
		public int can_move(int x, int y, boolean turn)
		{
			if(turn!=this.color)
				return 0;
			if(x == this.x && y == this.y) //not moving
				return 0;
			if(Math.abs(x-this.x)==1) 
			{
				if(Math.abs(y-this.y)==2)//horizontal 1, vertical 2
					return 1;
			}
			if(Math.abs(x-this.x)==2)
			{
				if(Math.abs(y-this.y)==1)//horizontal 2, vertical 1
					return 1;
			}
			return 0;
		}
		
		public boolean leap_over(int x, int y, Piece p)
		//return true if there's leap_over
		{
			return false;
		}
		
		public int type()
		{
			return 3;
		}
		
		public void print()
		{
			System.out.println("I am "+this.color+" Knight at "+this.position());	
		}
	
}
