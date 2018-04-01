import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;

public class dbload {
	public static void main(String[] args) 
	{
		//for time taken, started at start
		long start = System.currentTimeMillis();
		
		if(args.length != 3)
		{
			System.out.println("java dbload -p pagesize data");
			return;
		}
		//arguments being passed from aws
		String p = args[0];
		String pagesize = args[1];
		String FL = args[2];
		
		//locations of each file
		String fileLocation = FL;
        String outputLocation = "./heap." + pagesize;
        
        File file = new File(fileLocation);
        String regname = null;
        StringTokenizer strtok;
        String line;
        int records = 0;
        int pages = 0;
        int size;
        int pagesizes = Integer.parseInt(pagesize);
        int pagesNeeded = pagesizes;
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
            FileOutputStream outfile = new FileOutputStream(outputLocation);  
            DataOutputStream data = new DataOutputStream(outfile);
            String newrecord = "*";
            
            //keep going until no more lines
            while((line=br.readLine()) != null) 
            {
            	strtok = new StringTokenizer(line, ",");
                
            	while(strtok.hasMoreTokens())
            	{
            		//getting the byte for each column
            		regname = strtok.nextToken();
            		byte[] byt = regname.getBytes();
            		
            		//checking to see if the amount of bytes per page is too much each time
            		for(int i = 0 ; i < byt.length ; i++)
            		{
                        size = data.size();
                        
                        
                        if(size >= pagesNeeded)
                        {
                        	String newpage = "   ";
                        	pagesNeeded = pagesNeeded+pagesizes;
                        	
                        	data.writeUTF(newpage);
                        	pages++;
                        }
                        //writing the byte onto file
            			data.writeShort(byt[i]);
            		}
            	}
            	data.writeUTF(newrecord);
            	records++;
            	
            }
            data.flush();  
            data.close();  
            long end = System.currentTimeMillis();
            System.out.println("Records: " +records);
            System.out.println("Pages: " +pages);
            System.out.println("Time Taken: " +(end-start)+ " milliseconds.");
            //close all files
            br.close();
           
        }
        catch(Exception ex) 
        {
            System.err.println("Error");
        }
    }

}
