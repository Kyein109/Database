import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dbquery {

	public static void main(String[] args) 
	{
		
		if(args.length != 2)
		{
			System.out.println("java dbquery text pagesize");
			return;
		}
		
		String search = args[0];
		String pagesize = args[1];
		StringBuilder sb = new StringBuilder();StringTokenizer strtok;
		ArrayList<String> found = new ArrayList<String>();
		
		//locations of each file
		String fileLocation = "./heap." + pagesize;
        File file = new File(fileLocation);
        
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	String line;
        	
        	while((line=br.readLine()) != null)
        	{
        		strtok = new StringTokenizer(line, "*");
        		
        		while(strtok.hasMoreTokens())
        		{
        			sb.append(strtok.nextToken());
        			
        			String check = sb.toString().toLowerCase();
        			String keyword = search.toLowerCase().trim();
        			
        			if(check.indexOf(keyword)>=0)
        			{
        				found.add(check);
        			}
        		}
        		
        		if(found.size() >= 1)
        		{
        			for(int i = 0 ; i < found.size() ; i++)
            		{
            			System.out.println(found.get(i));
            		}
        		}
        		else
        		{
        			System.out.println("No matches found");
        		}
        		
        	}
        }
        catch(Exception ex) 
        {
        	System.err.println("Error");
        }
	}
}
