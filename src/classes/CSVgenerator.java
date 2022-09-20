/**
 * 
 */
package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author luisgzz
 *
 */
public class CSVgenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateCSVFILE("Products.csv");
	}

	private static void generateCSVFILE(String sFileName) {
		File dir = new File("Files");
		dir.mkdirs();
		
		File file = new File(dir, sFileName);
		FileWriter writer = null;
		
		try {
			//Create Folder
	
			writer = new FileWriter(file);
			
			
				
		}
		
		catch (IOException e){
			
			e.printStackTrace();
			
			
		}
		
		finally {
			
			try{
			writer.flush();
			writer.close();
			
			System.out.println("File Created Succesfully");
			}
			
			catch (IOException e) {
				e.printStackTrace();
				
				
			}
		}
		
	}

}
