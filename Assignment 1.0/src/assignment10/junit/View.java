package assignment10.junit;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Properties;

public class View extends JFrame implements ActionListener
{    
	Board game = new Board(8);	//use the initialization in Board Class
	private Container myPanel; //background
	private JPanel myBoard = new JPanel(new GridLayout(8,8));//chess board
	private JPanel[][] ChessCells = new JPanel[8][8]; //each square on the board
	private JPanel options = new JPanel();
	private int count, capture=0;
	private int source_x, source_y, prev_x, prev_y, prev_piece, curr_x, curr_y;
	ViewButton[][] buttons = new ViewButton[8][8];
	private ImageIcon[] icon = new ImageIcon[35];
	JButton forfeit = new JButton("Forfeit");
	JButton restart = new JButton("Restart");
	JButton undo = new JButton("  Undo  ");
    JLabel score_label = new JLabel();
    JLabel turn_label = new JLabel();
    int white_score, black_score=0;
    
	/*private ImageIcon rookBlack = new ImageIcon(System.getProperty("user.dir") + "/pieces/rook_dark.png");
	private ImageIcon rookWhite = new ImageIcon(System.getProperty("user.dir") + "/pieces/rook_light.png");
	private ImageIcon bishopBlack = new ImageIcon(System.getProperty("user.dir") + "/pieces/bishop_dark.png");
	private ImageIcon bishopWhite = new ImageIcon(System.getProperty("user.dir") + "/pieces/bishop_light.png");
	private ImageIcon knightBlack = new ImageIcon(System.getProperty("user.dir") + "/pieces/knight_dark.png");
	private ImageIcon knightWhite = new ImageIcon(System.getProperty("user.dir") + "/pieces/knight_light.png");
	private ImageIcon kingBlack = new ImageIcon(System.getProperty("user.dir") + "/pieces/king_dark.png");
	private ImageIcon kingWhite = new ImageIcon(System.getProperty("user.dir") + "/pieces/king_light.png");
	private ImageIcon queenBlack = new ImageIcon(System.getProperty("user.dir") + "/pieces/queen_dark.png");
	private ImageIcon queenWhite = new ImageIcon(System.getProperty("user.dir") + "/pieces/queen_light.png");
	private ImageIcon pawnBlack = new ImageIcon(System.getProperty("user.dir") + "/pieces/pawn_dark.png");
	private ImageIcon pawnWhite = new ImageIcon(System.getProperty("user.dir") + "/pieces/pawn_light.png");
    */  
	 public static void main(String[] args)
     {
    	 /*final View app =*/ new View();             
     }
	 
      /*initialize*/
      public View()
      {
    	  	initialize_icon();
    	  	myPanel = getContentPane();
            setBounds(200, 100, 800, 550); //size & position of the window
            setBackground(new Color(250, 250, 180));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("ChessBoard");
            setResizable(false);
            myPanel.setLayout(null);  
            
            /*initialize the Chess Board*/
            myBoard.setBounds(30, 30, 450, 450);
            myBoard.setBackground(new Color(250, 250, 180));
            myPanel.add(myBoard);
            
            /*option section*/
            options.setBounds(550, 50, 200, 200);
            options.setBackground(new Color(250, 250, 180));
            myPanel.add(options);
            forfeit.addActionListener(f()); //add forfeit button
            options.add(forfeit, BorderLayout.EAST);
            restart.addActionListener(r()); //add forfeit button
            options.add(restart, BorderLayout.EAST); 
            score_label.setText("White " + white_score + ":" + black_score + " Black" );
            options.add(score_label, BorderLayout.SOUTH);
            turn_label.setText("White's turn" );
            options.add(turn_label, BorderLayout.SOUTH);
            undo.addActionListener(u()); //add forfeit button
            options.add(undo, BorderLayout.SOUTH);
            
            /*draw black & white cells and pieces*/
            this.drawChessBoard();
            this.drawChessPieces(); 
            show();

      }

      
      //draws black and white cells on the board
      private void drawChessBoard()
      {
            for (int y = 0; y < 8; y++)
                  for (int x = 0; x < 8; x++)
                  {
                       ChessCells[y][x] = new JPanel(new BorderLayout());
                       myBoard.add(ChessCells[y][x]);
                       buttons[y][x]= new ViewButton(y, x);
                       buttons[y][x].addActionListener(e(x, y));
                       /*make the button transparent*/
                       buttons[y][x].setOpaque(false);
                       buttons[y][x].setContentAreaFilled(false);
                       buttons[y][x].setBorderPainted(false);
                       ChessCells[y][x].add(buttons[y][x],BorderLayout.CENTER);
                       if (y % 2 == 0)
                              if (x % 2 == 1)
                                    ChessCells[y][x].setBackground(new Color(150, 220, 130));
                              else
                                    ChessCells[y][x].setBackground(new Color(250, 220, 130));
                        else
                              if (x % 2 == 0)
                                    ChessCells[y][x].setBackground(new Color(150, 220, 130));
                              else
                                    ChessCells[y][x].setBackground(new Color(250, 220, 130));
                  }
      }   

      //place chess pieces at their positions
      private void drawChessPieces()
      {                
    	    int[][] Pieces = game.board; //Pieces to arrange
            for(int y = 0; y < 8; y++)       
            	for(int x = 0; x < 8; x++) 
            {                
                  //this.ChessCells[x][y].add(this.getPieceObject(Pieces[y][x], y ,x), BorderLayout.CENTER);
            	  getPiece(Pieces[x][y], x ,y);
            }          
      }

      private void getPiece(int PieceNum, int x, int y)
      {
    	  if(PieceNum==0)
    		  buttons[y][x].setIcon(null);
    	  else
    		  buttons[y][x].setIcon(icon[PieceNum]);
    	  /*if(PieceNum == 9||PieceNum == 16)
    	     buttons[y][x].setIcon(this.rookBlack);
    	  else if(PieceNum == 10||PieceNum == 15)
    		  buttons[y][x].setIcon(this.bishopBlack);
    	  else if(PieceNum == 11||PieceNum == 14)
    		  buttons[y][x].setIcon(this.knightBlack);
    	  else if(PieceNum == 12)
    		  buttons[y][x].setIcon(this.queenBlack);
    	  else if(PieceNum == 13)
    		  buttons[y][x].setIcon(this.kingBlack);
    	  else if(PieceNum >= 1 && PieceNum <= 8)
    		  buttons[y][x].setIcon(this.pawnBlack);
    	  else if(PieceNum == 25 || PieceNum == 32)
    		  buttons[y][x].setIcon(this.rookWhite);
    	  else if(PieceNum == 26 || PieceNum == 31)
    		  buttons[y][x].setIcon(this.bishopWhite);
    	  else if(PieceNum == 27 || PieceNum == 30)
    		  buttons[y][x].setIcon(this.knightWhite);
    	  else if(PieceNum == 28)
    		  buttons[y][x].setIcon(this.queenWhite);
    	  else if(PieceNum == 29)
    		  buttons[y][x].setIcon(this.kingWhite);
    	  else if(PieceNum >= 17 && PieceNum <= 24)
    		  buttons[y][x].setIcon(this.pawnWhite);*/
      }
      
      
      //lookup table - match chess pieces with numbers
      /*private JLabel getPieceObject(int PieceNum, int y, int x)
      {
    	  JLabel lbl;
    	  if(PieceNum == 9||PieceNum == 16)
    	  {	  //buttons[x][y].setIcon(this.rookBlack);
    	      //lbl=null;}
    	  	 lbl = new JLabel(this.rookBlack);}
    	  else if(PieceNum == 10||PieceNum == 15)
    		  lbl = new JLabel(this.bishopBlack);
    	  else if(PieceNum == 11||PieceNum == 14)
    		  lbl = new JLabel(this.knightBlack);
    	  else if(PieceNum == 12)
    		  lbl = new JLabel(this.queenBlack);
    	  else if(PieceNum == 13)
    		  lbl = new JLabel(this.kingBlack);
    	  else if(PieceNum >= 1 && PieceNum <= 8)
    		  lbl = new JLabel(this.pawnBlack);
    	  else if(PieceNum == 25 || PieceNum == 32)
    		  lbl = new JLabel(this.rookWhite);
    	  else if(PieceNum == 26 || PieceNum == 31)
    		  lbl =  new JLabel(this.bishopWhite);
    	  else if(PieceNum == 27 || PieceNum == 30)
    		  lbl = new JLabel(this.knightWhite);
    	  else if(PieceNum == 28)
    		  lbl = new JLabel(this.queenWhite);
    	  else if(PieceNum == 29)
              lbl = new JLabel(this.kingWhite);
    	  else if(PieceNum >= 17 && PieceNum <= 24)
              lbl = new JLabel(this.pawnWhite);
    	  else
    		  lbl = new JLabel();
    	  return lbl;
      }

      private void initializeButton(JPanel b) {
          JButton button = new JButton();
          button.setBackground(Color.BLACK);
          button.addActionListener(this);
          b.add(button, BorderLayout.EAST);
      }*/
      
      private void reset()
      {
     	 game = new Board(8);
     	 count = 0;
     	 this.drawChessPieces();
      }
      
      
      private ActionListener e(final int x, final int y)
      {
    	  return new ActionListener() {
              public void actionPerformed(ActionEvent event) {
            	  if(count%2==0 && game.board[x][y] > 0)
            	  {
            		   source_x=x;
            		   source_y=y;
            		   count++;
            		   System.out.print(count);
            	  }
            	  else if(count%2==1)
            	  {
            		   int piece_num = game.board[source_x][source_y];
            		   prev_x = source_x;
            		   prev_y = source_y;
            		   prev_piece = piece_num;
            		   curr_x = x;
            		   curr_y = y;
            		   capture = game.board[x][y];
            		   if(game.Board_move(x, y, game.array_p[game.board[source_x][source_y]])>0)
            		   {
            			   buttons[source_y][source_x].setIcon(null);
            			   buttons[y][x].setIcon(icon[piece_num]);
            			   count++;
            			   if(!game.turn)
            				   turn_label.setText("White's turn" );
            			   else
            				   turn_label.setText("Black's turn" );
            			   if(game.win==1)
            			   {	JOptionPane.showMessageDialog(null,
            	                   "White Win!" , "Congrats", JOptionPane.INFORMATION_MESSAGE);
            					white_score++;
            					score_label.setText("White " + white_score + ":" + black_score + " Black" );
            					reset();
            					//score_label.setText("White " + white_score + ":" + black_score + " Black" );
            			   }
            			   if(game.win==2)
            			   {	JOptionPane.showMessageDialog(null,
            	                   "Black Win!" , "Congrats", JOptionPane.INFORMATION_MESSAGE);
            					black_score++;
            					score_label.setText("White " + white_score + ":" + black_score + " Black" );
            					reset();
            					//score_label.setText("White " + white_score + ":" + black_score + " Black" );
            			   }
            		   }	
            		   else{
            			   count++;
               			   JOptionPane.showMessageDialog(null,
                	                   "Illegal move!" , "YOOO", JOptionPane.INFORMATION_MESSAGE);
            	 
            		   }
                   }
              }
            };
      }
      
      
      private ActionListener f()
      {
    	  return new ActionListener() {
              public void actionPerformed(ActionEvent event) {
            	  if(game.turn)
            	  {	  JOptionPane.showMessageDialog(null,
       	                   "White Win!" , "Congrats", JOptionPane.INFORMATION_MESSAGE);
            	  	  white_score++;
            	  	  score_label.setText("White " + white_score + ":" + black_score + " Black" );
            	  	  reset();
     				  turn_label.setText("White's turn" );
     			   
            	  	  //score_label.setText("White " + white_score + ":" + black_score + " Black" );
            	  }
            	  else
            	  {	  JOptionPane.showMessageDialog(null,
       	                   "Black Win!" , "Congrats", JOptionPane.INFORMATION_MESSAGE);
            	      black_score++;
            	      score_label.setText("White " + white_score + ":" + black_score + " Black" );
            	      reset();
       				  turn_label.setText("White's turn" );

            	  }
               }
              
           };
      }
      
      private ActionListener r()
      {
    	  return new ActionListener() {
              public void actionPerformed(ActionEvent event) {
            	 reset();
            	 turn_label.setText("White's turn" );
              }
              
           };
      }
      
      private ActionListener u()
      {
    	  return new ActionListener() {
              public void actionPerformed(ActionEvent event) {
            	 if(count==0||count==1)
            		 JOptionPane.showMessageDialog(null,
      	                   "Cannot Undo" , "Warning", JOptionPane.INFORMATION_MESSAGE);
            	 else if(count%2==1)
            		 count--;	 
            	 else
            	 {
            		 if(capture!=0)
            			 buttons[curr_y][curr_x].setIcon(icon[capture]);
            		 else	 
            			 buttons[curr_y][curr_x].setIcon(null);
      			     buttons[prev_y][prev_x].setIcon(icon[prev_piece]); 
      			     game.board[prev_x][prev_y] = game.board[curr_x][curr_y];
      			     game.board[curr_x][curr_y] = 0;
      			     game.array_p[prev_piece].move(prev_x, prev_y);
      			     game.turn = !game.turn;
      			     if(!game.turn)
      			    	 turn_label.setText("White's turn" );
      			     else
      			    	 turn_label.setText("Black's turn" );
            	 }
              }
              
           };
      }
      
      /*public void actionPerformed(ActionEvent e) {
	        JOptionPane.showMessageDialog(null,
	                    ,
	                    "Title here", JOptionPane.INFORMATION_MESSAGE);
	  }*/
      
      private void initialize_icon()
      {
    		for(int i=0; i<8;i++){	
    			icon[i+1] = new ImageIcon(System.getProperty("user.dir") + "/pieces/pawn_dark.png");
    		}
    		//black
    		icon[9] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/rook_dark.png");
    		icon[10] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/knight_dark.png");
    		icon[11] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/bishop_dark.png");
    	    icon[12] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/queen_dark.png");
    	    icon[13] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/king_dark.png");
    	    icon[14] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/bishop_dark.png");
    	    icon[15] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/knight_dark.png");
    	    icon[16] = new  ImageIcon(System.getProperty("user.dir") + "/pieces/rook_dark.png");	
    	    //0-8 white pawn
    	    for(int j=16; j<24;j++){
    	    	icon[j+1] = new ImageIcon(System.getProperty("user.dir") + "/pieces/pawn_light.png");
    		}
    	    //white
    		icon[25] = new ImageIcon(System.getProperty("user.dir") + "/pieces/rook_light.png");
    		icon[26] = new ImageIcon(System.getProperty("user.dir") + "/pieces/knight_light.png");
    		icon[27] = new ImageIcon(System.getProperty("user.dir") + "/pieces/bishop_light.png");
    		icon[28] = new ImageIcon(System.getProperty("user.dir") + "/pieces/queen_light.png");
    		icon[29] = new ImageIcon(System.getProperty("user.dir") + "/pieces/king_light.png");
    		icon[30] = new ImageIcon(System.getProperty("user.dir") + "/pieces/bishop_light.png");
    		icon[31] = new ImageIcon(System.getProperty("user.dir") + "/pieces/knight_light.png");
    		icon[32] = new ImageIcon(System.getProperty("user.dir") + "/pieces/rook_light.png");
    		icon[33] = new ImageIcon(System.getProperty("user.dir") + "/pieces/doge_dark.png");
    		icon[34] = new ImageIcon(System.getProperty("user.dir") + "/pieces/doge_light.png");
      }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
