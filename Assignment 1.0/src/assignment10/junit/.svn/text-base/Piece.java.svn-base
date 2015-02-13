package assignment10.junit;

public abstract class Piece {
	protected int x; //x coordinate of the piece
	protected int y; //y coordinate of the piece
	protected boolean color;  //false-white true-black
	protected boolean status; //false-die true-alive
	protected int id;
	
	public void initial(int x, int y, boolean color, boolean status, int id)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		this.status = status;
		this.id = id;
	}
	
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void remove()
	{
		this.status = false;
	}
	
	public int[] position()
	{
		int position[] = {x,y};
		return position;
	}
	
	public int id()
	{
		return this.id;
	}
	
	public boolean status()
	{
		return status;
	}
	
	public boolean color()
	{
		return color;
	}
	
	public boolean out_of_board(int x, int y, int size)
	{
		if(x>=size||y>=size||x<0||y<0)
			return true;
		return false;
	}

	abstract public int can_move(int x, int y, boolean turn);
	abstract public boolean leap_over(int x, int y, Piece p);
	abstract public int type();
	abstract public void print();
	
	
	// TODO Auto-generated constructor stub
	

}
