import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dbload {

	public static void main(String[] args) 
	{
		String fileLocation = "E:\\Documents\\Database\\New Text Document.txt";
        String outputLocation = "E:\\Documents\\Database\\heap.pagesize";
          
        File file = new File(fileLocation);
        String regname = null;
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            FileOutputStream outfile = new FileOutputStream("E:\\Documents\\Database\\heap.pagesize");  
            DataOutputStream data = new DataOutputStream(outfile);
            
            StringTokenizer strtok;
            
            while((line=br.readLine()) != null) 
            {
            	strtok = new StringTokenizer(line, ",");
                
            	while(strtok.hasMoreTokens())
            	{
            		regname = strtok.nextToken();
            		
                	byte[] b = regname.getBytes();
                	
                	for (int i = 0; i < b.length; i++) 
                	{
                		data.writeInt(b[i]);
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
