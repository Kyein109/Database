import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class dbload {

	public static void main(String[] args) 
	{
		String fileLocation = "E:\\Documents\\Database\\BUSINESS_NAMES_201803.csv";
        String outputLocation = "E:\\Documents\\Database\\FormattedCSV2.csv";

        File file = new File(fileLocation);
        ArrayList<String> changed = new ArrayList<String>();
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder builder;
            
            while((line=br.readLine()) != null) 
            {
	            builder = new StringBuilder(line);
	            //finding index of tab to convert to commas
	            int index = builder.indexOf("\t");
	            
	            //for consecutive commas
	            int conComma = 1;
	
	            //converting to comma until there is no more tabs
	            while (index >= 0) 
	            {
	            	//setting tab to comma
	            	builder.setCharAt(index , ',');
		            //get next index of tab
		            index = builder.indexOf("\t" , index + 1);
		            //check for consecutive tab
		            if(index != -1)
		            {
		            	if(conComma == index)
		            	{
			            	builder.setCharAt(index , ',');
			                //get next index of tab
			                index = builder.indexOf("\t" , index + 1);
		            	}
		            }
	            }
	            
	            //converting reg/dereg to reduce size
	            int reg = builder.indexOf("Registered");
	            int dereg = builder.indexOf("Deregistered");
	            
				//checking if line has registered or deregistered
	            if(reg != -1)
	            {
		            builder.replace(reg , reg+10 , "1");
	            }
	            else if(dereg != -1)
	            {
	            	builder.replace(dereg , dereg+12 , "2");
	            }
	            
	            //add the changed lines for output file
	            changed.add(builder.toString());
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
