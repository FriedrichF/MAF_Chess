/*Copyright Friedrich Fell 2013*/
import java.io.*;

public class maf_chess {
	public static void main(String[] args) {
		//Änderung!
		Board _board = new Board();
		BufferedReader _br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.println(_board.toString());
		
		
		 Board _board2 = new Board("14 W . . . k . . . . . Q . . . . . . . . . . . . . P P . . . . K");
		 System.out.println(_board2.toString());
		 System.out.println(_board2.getStateScore());
		 _board2.move(new Move("e5-d6"));
		 System.out.println(_board2.toString());
		 System.out.println(_board2.getStateScore());
		
		 
		 
		try {
			//System.out.print("Make a Move: ");
			while(!(input = _br.readLine()).equals("x")){
				char moveReturn = _board.move(_board.heuristicPlayer());
				if(moveReturn == '='){ 
					System.out.println("DRAW!!");
					System.out.println(_board.toString());
					break;
				}
				else if(moveReturn == 'W'){
					System.out.println("White WIN!");
					System.out.println(_board.toString());
					break;
				}
				else if(moveReturn == 'B'){
					System.out.println("Black WIN!");
					System.out.println(_board.toString());
					break;
				}
				else{ //(_board.move(_board.randomPlayer()) == '?')
					System.out.println(_board.toString());
					System.out.println("Score: " + _board.getStateScore());
					continue;
				}
				//System.out.print("Make a Move: ");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Bye!!");

	}

}
