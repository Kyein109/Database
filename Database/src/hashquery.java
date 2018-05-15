import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class hashquery {

	public static void main(String args[]) throws IOException 
	{
		long startTime = System.currentTimeMillis();
		String query = args[0];
		String page = args[1];
		
		
		int pagesize = Integer.parseInt(page);
		String hashfile = "./hash." + pagesize;
		
		try 
		{
			FileInputStream fis = new FileInputStream(hashfile);
			RandomAccessFile file = new RandomAccessFile("./hash." + pagesize, "rw");
			
			int hash = (query.hashCode() & 0x7fffffff);
			int bucket = hash % 3700000;
			file.seek(125);
			
			String reading = file.readLine();
			if(reading.equals(query))
			{
				System.out.println("Done");
			}
			else
			{
				System.out.println("Could not find the business.");
			}
			long endTime = System.currentTimeMillis();
			long doneTime = endTime - startTime;
			System.out.println("Time Taken For Query: " + doneTime + "ms");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File not found.");
		}
	}
}
