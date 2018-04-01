import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;

public class dbload {

	public static void main(String[] args) 
	{
		String fileLocation = "E:\\Documents\\Database\\New Text Document.txt";
        String outputLocation = "E:\\Documents\\Database\\heap.pagesize";
          
        File file = new File(fileLocation);
        String regname = null;
        StringTokenizer strtok;
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            FileOutputStream outfile = new FileOutputStream(outputLocation);  
            DataOutputStream data = new DataOutputStream(outfile);
            
            while((line=br.readLine()) != null) 
            {
            	strtok = new StringTokenizer(line, ",");
                
            	while(strtok.hasMoreTokens())
            	{
            		regname = strtok.nextToken();
            		byte[] byt = regname.getBytes();
            		for(int i = 0 ; i < byt.length ; i++)
            		{
            			data.writeInt(byt[i]);
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
