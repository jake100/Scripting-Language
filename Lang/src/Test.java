import java.util.HashMap;


public class Test {

	public static void main(String[] args) 
	{
		HashMap map = Parser.getMap("res/file.txt");
		
		System.out.println(map.get("string"));
		
		System.out.println(map.get("num"));

	}

}
