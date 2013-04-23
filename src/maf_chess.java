/*Copyright Friedrich Fell 2013*/
import java.io.*;

public class maf_chess {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board _board = new Board();
		BufferedReader _br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.println(_board.toString());
		
		_board.legalMoveTest();
		
		/*
		try {
			System.out.print("Make a Move: ");
			while(!(input = _br.readLine()).equals("x")){
				_board.move(new Move(input));
				System.out.println(_board.toString());
				System.out.print("Make a Move: ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("Bye!!");

	}

}
