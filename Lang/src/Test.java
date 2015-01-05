import java.util.HashMap;


public class Test {

	public static void main(String[] args) 
	{
		HashMap<String, Eval> evals = new HashMap<String, Eval>();
		evals.put("sayHi", new Eval(){
			public void evaluate(String params)
			{
				System.out.println("Hi " + params);
			}
		});
		HashMap map = Parser.getMap("res/file.txt", evals);
		
		System.out.println(map.get("string"));
		
		System.out.println(map.get("num"));

	}

}
