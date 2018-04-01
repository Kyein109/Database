import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
            String regname = null;
            String bname;
            String status;
            String regDT;
            String cancelDT;
            String renewDT;
            String stateNo;
            String stateofreg;
            String ABN;
            
            while((line=br.readLine()) != null) 
            {
            	strtok = new StringTokenizer(line, ",");
            	
            	while(strtok.hasMoreTokens())
            	{
            		regname = strtok.nextToken();
            		bname = strtok.nextToken();
            		status = strtok.nextToken();
            		regDT = strtok.nextToken();
            		cancelDT = strtok.nextToken();
            		renewDT = strtok.nextToken();
            		stateNo = strtok.nextToken();
            		stateofreg = strtok.nextToken();
            		ABN = strtok.nextToken();
            	}

        		byte[] byt = regname.getBytes();
        		for (byte b : byt) {
        			System.out.println(Arrays.toString(byt));
        		}
            }
            System.out.println("done");
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
