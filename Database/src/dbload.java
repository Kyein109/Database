import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;

public class dbload {

	public static void main(String[] args) 
	{
		String p = args[0];
		String pagesize = args[1];
		String FL = args[2];
		
		String fileLocation = FL;
        String outputLocation = "./heap.pagesize";
          
        File file = new File(fileLocation);
        String regname = null;
        StringTokenizer strtok;
        String line;
        
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
            FileOutputStream outfile = new FileOutputStream(outputLocation);  
            DataOutputStream data = new DataOutputStream(outfile);
            
            
            
            while((line=br.readLine()) != null) 
            {
            	int pagesizes = Integer.parseInt(pagesize);
                int size = data.size();
                
                String newpage = "*";
                
                if(size >= pagesizes)
                {
                	data.writeUTF(newpage);
                }
                
            	strtok = new StringTokenizer(line, ",");
                
            	while(strtok.hasMoreTokens())
            	{
            		regname = strtok.nextToken();
            		byte[] byt = regname.getBytes();
            		for(int i = 0 ; i < byt.length ; i++)
            		{
            			data.writeShort(byt[i]);
            		}
            	}
            }
            data.flush();  
            data.close();  
            System.out.println("done");
            //close all files
            br.close();
           
        }
        catch(Exception ex) 
        {
            System.err.println("Error");
        }
    }
}
