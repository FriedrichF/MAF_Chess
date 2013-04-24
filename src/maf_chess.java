import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

// Copyright © 2013 MAF 

public class maf_chess 
{
	public static void main(String[] args) 
	{
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
						legalMove = oBoard.randomMove();
					}else{
						System.out.println("Heuristic Player:");
						legalMove = oBoard.move(oBoard.heuristicPlayer());
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
	}
	
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
	}
}