/*Copyright Friedrich Fell 2013*/

import java.io.*;

public class Board {
	int moveNum;
	char onMove;
	char[][] table = new char[6][5];
	
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
			for (int c=0; c < table[c].length; c++)
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
    			for (int c=0; c < table[c].length; c++)
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
	
	public void move(Move _move){
		
		if(onMove == 'B'){
			onMove = 'W';
		}else{
			onMove = 'B';
			moveNum++;
		}
		
		table[_move.to.row][_move.to.col] = table[_move.from.row][_move.from.col];
		table[_move.from.row][_move.from.col] = '.';
	}
	
}
