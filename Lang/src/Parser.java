



import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public static enum State{Function, Params}
	private static State state = State.Function;
	public static HashMap<String, String> getMap(String path, HashMap<String, Eval> evals)
	{
		String code = Load.load(path);
		HashMap<String, String> map = new HashMap<String, String>();
		
    	Pattern pattern = Pattern.compile("\"[^\"]*\"|[A-Za-z_][A-Za-z_0-9]*|\\.\\d+|\\d+.\\d+|\\d+|\\([^\\(]*\\)");
    	Matcher matcher = pattern.matcher(code); 
    	String id = "";
    	while(matcher.find())
    	{
    		//System.out.print(matcher.group() + "\t");
    		if(state == State.Function)
    		{
    			id = matcher.group();
    			state = State.Params;
    		}
    		else 
    		{
    			String param = matcher.group();
    			if(matcher.group().matches("\"[^\"]*\""))
    			{
    				param = matcher.group().substring(1, matcher.group().length() - 1);
        			map.put(id, param);
    			}
    			else if(matcher.group().matches("\\([^\\(]*\\)"))
    			{
    				if(evals.containsKey(id))
    				{
    					evals.get(id).evaluate(matcher.group().substring(1, matcher.group().length() - 1));
    				}
    			}
    			else
    			{
    				map.put(id, param);
    			}
    			state = State.Function;
    		}
    	}
		return map;
	}

}
