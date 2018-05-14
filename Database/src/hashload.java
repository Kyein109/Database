import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class hashload {
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		String pagesize = args[0];
		
		String fileLocation = "./heap.4096";
		String outputLocation = "./hash." + pagesize;
		File file = new File(fileLocation);
		String line;
		StringTokenizer strtok;
		StringBuilder sb = new StringBuilder();
		String record = null;
		int hash = 0; 
       
		try 
        {
			BufferedReader heapfile = new BufferedReader(new FileReader(file));
	        FileOutputStream outfile = new FileOutputStream(outputLocation);
	        
	        while((line=heapfile.readLine()) != null) 
            {
	        	strtok = new StringTokenizer(line, "*");
	        	while(strtok.hasMoreTokens())
            	{
	        		
	        		record = strtok.nextToken();
	        		sb = new StringBuilder(record);
	        		String row = sb.toString().toLowerCase();
	        		hash = row.hashCode();
            	}
	        	outfile.write(hash);
	        	
            }
	        outfile.flush();  
	        outfile.close();
        }
		catch(Exception ex) 
        {
            System.err.println("Error");
        }
    
        
	}
}
