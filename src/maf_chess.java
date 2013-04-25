import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Copyright © 2013 MAF 

public class maf_chess 
{	
	public static void test(){
		Board b = new Board(
				"10 B " +
				". . . . . " +
				". . . . . " +
				". . r . . " +
				". . . . . " +
				". . q . . " +
				". . . . .");
		ArrayList<Move> moveList = b.legalMoves();
		for(Move move : moveList){
			System.out.print(move.toString()+"|");
		}
		System.out.print("\n");
		System.out.println(b.toString());
	}
	
	public static void main(String[] args) {
		boolean inputError = false;
		char input2 = 0;
		do {
			inputError = false;
			System.out.println("Welcome to MAF_Chess");
			System.out.println("Select mode:");
			System.out.println("0 - play on the local Server");
			System.out.println("1 - play on IMCS");
			BufferedReader brConsole = new BufferedReader(
					new InputStreamReader(System.in));
			try {
				input2 = brConsole.readLine().charAt(0);
				//input2 = input.charAt(0);
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				inputError = true;
			}

			if (input2 != '0' && input2 != '1')
				inputError = true;
		} while (inputError);

		if (input2 == '0')
			playLocal();
		else if (input2 == '1')
			playIMCS();

	}

	public static void playLocal() {	
		//declare variables
		FileInputStream fis = null;
		BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		String kindOfGame = "";
		Board oBoard = null;
		boolean humanMove = true;
		char legalMove = ' ';
		
		//start screen of the MAF_Chess game
		System.out.println("New Game (Human Game)- N");
		System.out.println("New Game (Human vs. Random)- HR");
		System.out.println("New Game (Random vs. Random)- RR");
		System.out.println("New Game (Random vs. BetterRandom)- RB");
		System.out.println("Load Game - L");
		System.out.println("Exit - X");
		System.out.println("");
		
		try
		{
			System.out.print("Enter your action:");
			kindOfGame = brConsole.readLine();
			
			if (kindOfGame.equals("N") || kindOfGame.equals("HR") || kindOfGame.equals("RR") || kindOfGame.equals("RB"))
			{
				oBoard = new Board();
				/*
				 * creating board with a string as parameter
				 * Board b = new Board("10 W Q Q Q Q Q Q Q Q Q Q - - - - - - - - - - Q Q Q Q Q Q Q Q Q Q");
				 */
			}
			else if (kindOfGame.equals("L"))
			{
				fis = new FileInputStream("C:\\Daten\\Studium\\Vorlesungen\\FWPM\\MiniChess\\Projekt\\Dateien\\savedBoard.txt");
				oBoard = new Board(fis);
			}
			else if (kindOfGame.equals("X"))
			{
				System.out.println(" :-( ");
				return;
			}
		
			//Show current Board before first move
			System.out.println(oBoard.toString() + "\n");

			while(true)
			{
				if (input.equals("x"))
				{
					saveGame(oBoard);
					break;
				}
				if (legalMove == 'W')
				{
					System.out.println("white player wins the game!!!");
					break;
				}
				else if (legalMove == 'B')
				{
					System.out.println("black player wins the game!!!");
					break;
				}
				else if (legalMove == '=')
				{
					System.out.println("DRAW!!!");
					break;
				}
				else if (legalMove == '?')
				{
					//next move
					
				}
				
				if (kindOfGame.equals("N"))
				{
					System.out.print("Player: " + oBoard.onMove + "\nIt's your move. Please enter your move(like a1-c3):");
					input = brConsole.readLine();
				
					if (input.equals("x"))
					{
						saveGame(oBoard);
						break;
					}
					else
					{
						oBoard.humanMove(new Move(input));
					}
				}
				else if (kindOfGame.equals("HR"))
				{
					if (legalMove == '?')
					{
						humanMove = !humanMove;
					}
					else if (legalMove == 'N')
					{
						
					}
					
					if (humanMove)
					{
						System.out.print("Player: " + oBoard.onMove + "\nIt's your move. Please enter your move(like a1-c3):");
						input = brConsole.readLine();
					
						if (!input.equals("x"))
						{
							legalMove = oBoard.humanMove(new Move(input));
						}
					}
					else
					{
						System.out.print("Player: " + oBoard.onMove + "\npress any key for random move:");
						input = brConsole.readLine();
						
						legalMove = oBoard.randomMove();
					}
				}
				else if (kindOfGame.equals("RR"))
				{
					System.out.print("Player: " + oBoard.onMove + "\npress any key for random move:");
					input = brConsole.readLine();
					
					legalMove = oBoard.randomMove();
				}
				else if (kindOfGame.equals("RB"))
				{
					System.out.print("Player: " + oBoard.onMove + "\npress any key for random move:");
					input = brConsole.readLine();
					if(oBoard.onMove == 'W'){
						System.out.println("Heuristic Player:");
						legalMove = oBoard.move(oBoard.heuristicPlayer());
						System.out.println(oBoard.toString());
					}else{
						System.out.println("NegamaxPrune Player:");
						legalMove = oBoard.move(oBoard.negamaxPlayerTime());
						System.out.println(oBoard.toString());
					}
				}
			}
			System.out.println("\n\nThank you for playing MAF_Chess!!!");
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}//end playLocal
	
	private static void saveGame(Board pBoard)
	{
		try
		{
			BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("\nDo you want to save the game? (y/n) ");
			String input = brConsole.readLine();
			if (input.equals("y"))
			{
				System.out.println("game saved");
				FileOutputStream fos = new FileOutputStream("C:\\Daten\\Studium\\Vorlesungen\\FWPM\\MiniChess\\Projekt\\Dateien\\savedBoard.txt");
				
				pBoard.print(fos);
				fos.close();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	} //end saveGame
	
	private static void playIMCS() {
		BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
		char inputChar = ' ';
		String id = "";
		char color = '?';
		char myColor = 'W';
		Move myMove = new Move("a2-a3");// only thats not null
		Move oppMove = new Move("a2-a3");// only thats not null
		Client connection;
		boolean inputError = false;
		char player = '0';

		try {
			connection = new Client("imcs.svcs.cs.pdx.edu", "3589","MAF_Chess", "MAF_ChessWins");
		} catch (Exception e) {
			return;
		}

		// choose player
		do {
			inputError = false;
			System.out.println("----choose player----");
			System.out.println("0 - randomPlayer");
			System.out.println("1 - heuristicPlayer");
			System.out.println("2 - negamaxPlayer");
			System.out.println("3 - ");
			System.out.println("4 - ");
			System.out.println("5 - ");

			try {
				player = brConsole.readLine().charAt(0);
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				inputError = true;
			}
			// check if input is correct
			if (!(player >= '0' && player <= '5'))
				inputError = true;
		} while (inputError);

		// offer or accept a game
		do {
			inputError = false;
			System.out.println("Play on IMCS");
			System.out.println("Select mode:");
			System.out.println("0 - accept a game");
			System.out.println("1 - offer a game");
			// BufferedReader brConsole = new BufferedReader(
			// new InputStreamReader(System.in));
			try {
				inputChar = brConsole.readLine().charAt(0);
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				inputError = true;
			}
			// check if input is correct
			if (inputChar != '0' && inputChar != '1')
				inputError = true;
		} while (inputError);

		// accept a game
		if (inputChar == '0') {
			do {
				inputError = false;
				System.out.println("----Accept a game----");
				try {
					System.out.print("Enter Game id: ");
					id = brConsole.readLine();
					System.out.print("Choose a Color (B/W/?): ");
					color = brConsole.readLine().charAt(0);
				} catch (Exception e) {
					System.out.println("Wrong input");
					inputError = true;
				}
				// accept the Game
				try {
					myColor = connection.accept(id, color);
				} catch (Exception e) {
					// System.out.print("Error: ");
					// System.out.println(e);
					inputError = true;
				}
			} while (inputError);

			// offer a game
		} else if (inputChar == '1') {
			do {
				inputError = false;
				System.out.println("----Offer a Game----");
				try {
					System.out.print("Choose a Color (B/W/?): ");
					color = brConsole.readLine().charAt(0);
				} catch (Exception e) {
					System.out.println("Wrong input!");
					inputError = true;
				}
				// offer the Game
				try {
					myColor = connection.offer(color);
				} catch (Exception e) {
					// System.out.print("Error: ");
					// System.out.println(e);
					inputError = true;
				}
			} while (inputError);

		}

		// create a local Board
		Board oBoard = new Board();
		oBoard.onMove = 'W';
		System.out.println(oBoard.toString());
		System.out.println("My Color: " + myColor);
		System.out.println("------Game starts------");

		if (myColor == 'B') {
			try {
				String test = connection.getMove();
				System.out.println("I get: " + test);
				oppMove = new Move(test);
				if (oppMove != null)
					oBoard.move(oppMove);
				// System.out.println("Opponent moved: " + oBoard.toString());
			} catch (Exception e) {

			}
		}

		while (oppMove != null) {

			// my turn
			do {
				inputError = false;
				try {
					// select Player and get Move
					switch (player) {
					case '0':
						myMove = oBoard.randomPlayer();
						break;
					case '1':
						myMove = oBoard.heuristicPlayer();
						break;
					case '2':
						myMove = oBoard.negamaxPlayer();
						break;
					case '3':
						break;
					case '4':
						break;
					case '5':
						break;
					default:
						myMove = oBoard.heuristicPlayer();
						break;
					}// end switch
					connection.sendMove("! " + myMove.toString());
				} catch (Exception e) {
					inputError = true;
				}
			} while (inputError);
			oBoard.move(myMove);
			System.out.println("I moved: " + oBoard.toString());

			// opponents turn
			// Get Opponents Move and take the move at local board
			do {
				inputError = false;
				try {
					oppMove = new Move(connection.getMove());
				} catch (Exception e) {
					inputError = true;
				}
			} while (inputError);

			if (oppMove != null)
				oBoard.move(oppMove);
			System.out.println("Opponent moved: " + oBoard.toString());
		}

		System.out.println("GAME IS OVER");
		System.out.println(oBoard.toString());

	}// end playIMCS
	
}