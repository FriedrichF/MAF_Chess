/*Copyright Friedrich Fell 2013*/

public class Move {
	public Square from;
	public Square to;
	
	//String in form: c1-c3
	public Move(String s){
		try{
			if(s.length() == 5){
				from = new Square(s.substring(0,2));
				to = new Square(s.substring(3));
			}else{
				throw new Exception("String not long enough!");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public String toString(){
		return ""+from+"-"+to;
	}
}
