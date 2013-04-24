/*Copyright Friedrich Fell 2013*/
import java.io.*;

public class maf_chess {
	public static void main(String[] args) {
		Board _board = new Board();
		BufferedReader _br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.println(_board.toString());
		
		
		
		try {
			//System.out.print("Make a Move: ");
			while(!(input = _br.readLine()).equals("x")){
				char moveReturn = _board.move(_board.randomPlayer());
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
