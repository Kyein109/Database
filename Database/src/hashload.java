import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

/**
 * Database Systems - HEAP IMPLEMENTATION
 */

public class hashload {
	private static final int RECORD_SIZE = 297;

	// initialize
	public static void main(String args[]) 
	{
		//this code was taken from the provided qbquery
		int noOfRecords = 0;
		File heapfile = new File("./heap.4096");
		int intSize = 4;
		int pageCount = 0;
		int recCount = 0;
		int recordLen = 0;
		int rid = 0;
		boolean isNextPage = true;
		boolean isNextRecord = true;
		
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		
		String arg1 = args[0];
		int pagesize = Integer.parseInt(arg1);
		try {
			FileInputStream fis = new FileInputStream(heapfile);
			BufferedWriter writer = new BufferedWriter(new FileWriter("./hash." + pagesize));
			RandomAccessFile file = new RandomAccessFile("./hash." + pagesize, "rw");
			// reading page by page
			while (isNextPage) {
				byte[] bPage = new byte[pagesize];
				byte[] bPageNum = new byte[intSize];
				fis.read(bPage, 0, pagesize);
				System.arraycopy(bPage, bPage.length - intSize, bPageNum, 0, intSize);

				// reading by record, return true to read the next record
				isNextRecord = true;
				while (isNextRecord) {
					byte[] bRecord = new byte[RECORD_SIZE];
					byte[] bnName = new byte[200];
					
					byte[] bRid = new byte[intSize];
					try 
					{
						System.arraycopy(bPage, recordLen, bRecord, 0, RECORD_SIZE);
						System.arraycopy(bRecord, 0, bRid, 0, intSize);
						System.arraycopy(bRecord, recordLen, bnName, 0, 200);
						rid = ByteBuffer.wrap(bRid).getInt();
						if (rid != recCount) 
						{
							isNextRecord = false;
						} 
						else 
						{
							//start of personal code
							//below function allows me to convert the byte array into a string
							String BN_NAME = new String(bnName);
							//removing excess from the business name
							String BN_NAMEc = BN_NAME.replace("BUSINESS NAMES", "");
							
							//making sure all hash indexes are positive (makes it easier for me to load)
							int hash = (BN_NAMEc.hashCode() & 0x7fffffff);
							int bucket = hash % 3700000; //moduled as seen in discussion
							
							//converting hash to a byte to place in the file
							byte[] bytes = new byte[4];
							ByteBuffer.wrap(bytes).putInt(hash);
							
							//RAF using bucket as the pointer
							file.seek(bucket);
							//file.writeBytes(BN_NAMEc + ":" + hash);
							boolean done = true;
							while(done == true)
							{
								String reaad = file.readLine();
								//loop until an empty space is found in the file in order to place business name
								if(reaad != null)
								{
									file.seek(file.getFilePointer() + 204);
								}
								else
								{	
									//writing to file
									file.write(bnName);file.write(bytes);
									
									done = false;
								}
								
							}
							


							//rest are from dbquerya
							
							recordLen += RECORD_SIZE;
						}
						recCount++;
						// if recordLen exceeds pagesize, catch this to reset to next page
					} 
					catch (ArrayIndexOutOfBoundsException e) 
					{
						isNextRecord = false;
						recordLen = 0;
						recCount = 0;
						rid = 0;
					}
				}
				// check to complete all pages
				if (ByteBuffer.wrap(bPageNum).getInt() != pageCount) {
					isNextPage = false;
				}
				pageCount++;
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}