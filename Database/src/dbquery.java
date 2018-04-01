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
		StringBuilder sb = new StringBuilder();
		StringTokenizer strtok;
		ArrayList<String> found = new ArrayList<String>();
		
		//locations of each file
		String fileLocation = "./heap." + pagesize;
        File file = new File(fileLocation);
        
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	String line;
        	
        	//go through file until no more lines
        	while((line=br.readLine()) != null)
        	{
        		strtok = new StringTokenizer(line, "*");
        		
        		//checking each record
        		while(strtok.hasMoreTokens())
        		{
        			sb = new StringBuilder(strtok.nextToken());
        			
        			//converting to string to check for contains
        			String check = sb.toString().toLowerCase();
        			String keyword = search.toLowerCase().trim();
        			
        			//there was a bug here i could no figure out why this didn't work
        			//it only worked for singular characters for an odd reason
        			if(check.contains(keyword))
        			{
        				found.add(check);
        			}
        		}
        		
        		//check if anything was found
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
