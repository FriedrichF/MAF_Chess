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
	
	public Board(InputStream stream){
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		String output = "";
		try{
		while((line = reader.readLine()) != null){
			output += line+"\n";
		}
		}catch(Exception e){}
		
		stringToBoard(output);
	}
	
	public Board(String position){
		stringToBoard(position);
	}
	
	private void stringToBoard(String boardString){
		String[] rowInput = boardString.split("\\n");
		String[] numOnMove = rowInput[0].split(" ");
		moveNum = Integer.parseInt(numOnMove[0]);
		onMove = numOnMove[1].charAt(0);
		
		for(int i = 0; i < table.length; i++){
			String[] figures = rowInput[i+1].split(" ");
			for(int x = 0; x < table[i].length; x++){
				table[i][x] = figures[x].charAt(0);
			}
		}
	}
	
	public String toString(){
		String output = moveNum +" "+ onMove+"\n";
		for(int i = 0; i < 6; i++){
			for(int x = 0; x < 5; x++){
				output += table[i][x]+" ";
			}
			output += "\n";
		}
		return output;
	}
	
	public void print(OutputStream outStream){
		
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
