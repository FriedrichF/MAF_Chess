// Copyright � 2013 MAF 
import java.util.Arrays;


public class Square {
	public int col;
	public int row;
	private final static char[] chars = {'a','b','c','d','e','f','g','h'};
	
	//String Example c4
	public Square(String inputMove) throws Exception{
		if(inputMove.length() == 2){
			try{
				col = Arrays.binarySearch(chars, inputMove.charAt(0));
				row = 8 - Integer.parseInt((""+inputMove.charAt(1)));
				if(col < 0 | col > 8 | row < 0 | row > 8){
					throw new Exception();
				}
			}catch(Exception e){
				throw new Exception("Square input not valid");
			}
		}else{
			throw new Exception("Square input String not 2 char long!");
		}
	}
	
	public Square(int col, int row){
		this.col = col;
		this.row = row;
	}
	
	public String toString(){
		int column = 8-Integer.parseInt(""+row);
		return ""+chars[col]+column;
	}
}
