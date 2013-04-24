/*Copyright Friedrich Fell 2013*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	int moveNum;
	char onMove;
	char[][] table = new char[6][5];
	private final int CAPTURE_ONLY = 1;
	private final int NO_CAPTURE = 2;
	private final int ALL_CAPTURE = 0;
	
	public Board(){
		char[][] table1 = {{'k','q','b','n','r'},
				{'p','p','p','p','p'},
				{'.','.','.','.','.'},
				{'.','.','.','.','.'},
				{'P','P','P','P','P'},
				{'R','N','B','Q','K'}};
		table = table1;
		
		moveNum = 1;
		onMove = 'B';
	}
	
	//constructor which initialize a board like the parameter string
	//i.e. Board b = new Board("10 W Q Q Q Q Q Q Q Q Q Q - - - - - - - - - - Q Q Q Q Q Q Q Q Q Q");
	Board(String strBoard)
	{
		stringToBoard(strBoard);
	}
	
	//constructor which initialize a board like the parameter InputStream
	//i.e. read out a file
	Board(InputStream isBoard)
	{
		try 
		{
			BufferedReader brFile = new BufferedReader(new InputStreamReader(isBoard));
			stringToBoard(brFile.readLine());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//save the current board at OutputStream, i.e. writing in a file
	public void print(OutputStream osBoard)
	{
		boardToString(osBoard);
	}
	
	//read out the string and convert it to the board char array
	private void stringToBoard(String strBoard)
	{
		int iCountSquare = 2;
		String[] arrString = strBoard.split(" ");
		moveNum = Integer.parseInt(arrString[0]);
		onMove = arrString[1].charAt(0);
		
		for (int r=0; r < table.length; r++)
		{
			for (int c=0; c < table[r].length; c++)
			{
				table[r][c] = arrString[iCountSquare++].charAt(0);
			}
		}
	}
	
	//read out the board and building a string which can save in a file
	private void boardToString(OutputStream osBoard)
	{
        FileOutputStream fos = (FileOutputStream) osBoard;
        try
        {
        	PrintStream ps = new PrintStream(fos);
        	ps.print(moveNum);
        	ps.print(' ');
        	ps.print(onMove);

    		for (int r=0; r < table.length; r++)
    		{
    			for (int c=0; c < table[r].length; c++)
    			{
    				ps.print(" " + table[r][c]);
    			}
    		}
    		ps.close();
    		fos.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
	}
	
	public String toString(){
		String output = moveNum +" "+ onMove+"\n";
		for(int i = 0; i < table.length; i++){
			for(int x = 0; x < table[i].length; x++){
				output += table[i][x]+" ";
			}
			output += "\n";
		}
		return output;
	}
	
	public ArrayList<Move> legalMoves(){
		ArrayList<Move> moves = new ArrayList<Move>();
		boolean player = true;
		
		if(onMove == 'W'){
			player = false;
		}
		
		for(int i = 0; i < table.length; i++){
			for(int x = 0; x < table[i].length; x++){
				if(Character.isLowerCase(table[i][x]) == player && table[i][x] != '.'){
					switch(Character.toLowerCase(table[i][x])){
						case 'k':	
							addScan(moves, new Square(x, i), -1, -1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, 0, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, 1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, -1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, 1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, -1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 0, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 1, true, ALL_CAPTURE);
							break;
						case 'q':
							addScan(moves, new Square(x, i), -1, -1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, 0, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, 1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, -1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, 1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, -1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 0, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 1, false, ALL_CAPTURE);
							break;
						case 'b':	
							addScan(moves, new Square(x, i), -1, -1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, 0, true, NO_CAPTURE);
							addScan(moves, new Square(x, i), -1, 1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, -1, true, NO_CAPTURE);
							addScan(moves, new Square(x, i), 0, 1, true, NO_CAPTURE);
							addScan(moves, new Square(x, i), 1, -1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 0, true, NO_CAPTURE);
							addScan(moves, new Square(x, i), 1, 1, false, ALL_CAPTURE);
							break;
						case 'n':	
							addScan(moves, new Square(x, i), -2, -1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -2, 1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, -2, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, -2, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 2, -1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 2, 1, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 2, true, ALL_CAPTURE);
							addScan(moves, new Square(x, i), -1, 2, true, ALL_CAPTURE);
							break;
						case 'r':
							addScan(moves, new Square(x, i), -1, 0, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, -1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 0, 1, false, ALL_CAPTURE);
							addScan(moves, new Square(x, i), 1, 0, false, ALL_CAPTURE);
							break;
						case 'p':	
							if(onMove == 'W'){
								addScan(moves, new Square(x, i), -1, -1, true, CAPTURE_ONLY);
								addScan(moves, new Square(x, i), -1, 0, true, NO_CAPTURE);
								addScan(moves, new Square(x, i), -1, 1, true, CAPTURE_ONLY);
							}else{
								addScan(moves, new Square(x, i), 1, -1, true, CAPTURE_ONLY);
								addScan(moves, new Square(x, i), 1, 0, true, NO_CAPTURE);
								addScan(moves, new Square(x, i), 1, 1, true, CAPTURE_ONLY);
								break;
							}
					}
				}
			}
		}
		
		return moves;
	}
	
	public void addScan(ArrayList<Move> moves, Square start, int dr, int dc,
			boolean oneStep, int captureMode) {
		//System.out.println("Start scan");
		Square next;
		Move move; // the actual move from postition to next
		char player = '.'; // Player at to-field or nobody
		int i = 1; // zum Weiterschreiten in die Richtung
		boolean run = true; // Stop after first Step if oneStep is set
		try {
			// generate the Move
			while ((0 <= (start.col + i * dc) && (start.col + i * dc) < 5)
					&& (0 <= (start.row + i * dr) && (start.row + i * dr) < 6)
					&& run) {
				next = new Square((start.col + dc * i), (start.row + dr * i));
				//System.out.println(next.toString());
				move = new Move(start.toString() + "-" + next.toString());
				// check to-field
				char tofield = this.table[move.to.row][move.to.col];
				if (tofield == '.') {
					player = '0';
				} else if (Character.isUpperCase(tofield)) {
					player = 'W';
				} else if (Character.isLowerCase(tofield)) {
					player = 'B';
				}
				// move correct?
				if (player != onMove) {
					if (captureMode == 1 && player != '0') {
						// captureOnly
						moves.add(move);
						run = false;
					} else if (captureMode == 2 && player == '0') {
						// noCapture
						moves.add(move);
					} else if (captureMode == 0) {
						// doesnt matter
						moves.add(move);
						if(player != '0')
							run = false;
					}
				}// end if player!= onMove
				else{
					run = false;
				}
				//System.out.println("Ende einfügen");
				if (oneStep)
					run = false;
				i++;
			} // end while
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public char move(Move _move){		
		if(moveNum == 41){
			return '=';
		}
		
		if(Character.toLowerCase(table[_move.to.row][_move.to.col]) == 'k'){
			table[_move.to.row][_move.to.col] = table[_move.from.row][_move.from.col];
			table[_move.from.row][_move.from.col] = '.';
			return onMove;
		}
		
		if((_move.to.row == 5 || _move.to.row == 0) && Character.toLowerCase(table[_move.from.row][_move.from.col]) == 'p'){
			if(Character.isLowerCase(table[_move.from.row][_move.from.col])){
				table[_move.to.row][_move.to.col] = 'q';
			}else{
				table[_move.to.row][_move.to.col] = 'Q';
			}
		}else{
			table[_move.to.row][_move.to.col] = table[_move.from.row][_move.from.col];
		}
		table[_move.from.row][_move.from.col] = '.';
		
		
		
		if(onMove == 'B'){
			onMove = 'W';
		}else{
			onMove = 'B';
			moveNum++;
		}
		return '?';
	}
	
	public Move randomPlayer(){
		ArrayList<Move> alMoves = legalMoves();
		
		return alMoves.get((int)Math.round(Math.random() * (alMoves.size()-1)));
	}
}
