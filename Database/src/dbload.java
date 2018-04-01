import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dbload {

	public static void main(String[] args) 
	{
		String fileLocation = "E:\\Documents\\Database\\FormattedCSV.csv";
        String outputLocation = "E:\\Documents\\Database\\heap.pagesize";

        File file = new File(fileLocation);
        ArrayList<Byte> changed = new ArrayList<Byte>();
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringTokenizer strtok;
            
            while((line=br.readLine()) != null) 
            {
            	strtok = new StringTokenizer(line, ",");
            	
            	while(strtok.hasMoreTokens())
            	{
            		String regname = strtok.nextToken();
            		String bname = strtok.nextToken();
            		String status = strtok.nextToken();
            		String regDT = strtok.nextToken();
            		String cancelDT = strtok.nextToken();
            		String renewDT = strtok.nextToken();
            		String stateNo = strtok.nextToken();
            		String stateofreg = strtok.nextToken();
            		String ABN = strtok.nextToken();
            	}
            }
            //close all files
            br.close();
            
           //write all the lines to the file
           File out = new File(outputLocation);
           PrintWriter write = new PrintWriter(out);
           for(int i = 0 ; i < changed.size() ; i++) 
           {
        	   write.println(changed.get(i));
           }

           write.close();
        }
        catch(Exception ex) 
        {
            System.err.println("Error");
        }
    }
}
