import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Operator class
 * this class, containing the main() function, is used to read from File inputs and operate PageManager instances.
 * 
 * Since each instance of PageManager can only handle one Reference String at a time, this Operator class will be used to
 * hand out Ref Strings to several instances of PageManager at a time, and call each of the three algorithms that can be
 * used to parse them.
 */
public class Operator {

	public static void main(String[] args) {
		String[] refStrings = readRefStrings("ReferenceStrings.txt");
		PageManager p = null;
		int cap = 6;
		int total = 0;
		float avg = 0;
		int temp;
		System.out.println("cap: " + cap);
		System.out.println();
		
		System.out.println("FIFO:");
		System.out.print("results: ");
		for(int i = 0; i < refStrings.length; i++) {
			p = new PageManager(refStrings[i], cap);
			temp = p.FIFO();
			System.out.print(temp + " ");
			total += p.FIFO();
		}
		System.out.println();
		avg = total/refStrings.length;
		System.out.println("average: " + avg);
		hr();
		total = 0;
		System.out.println("LRU:");
		System.out.print("results: ");
		for(int i = 0; i < refStrings.length; i++) {
			p = new PageManager(refStrings[i], cap);
			temp = p.LRU();
			System.out.print(temp + " ");
			total += p.LRU();
		}
		System.out.println();
		avg = total/refStrings.length;
		System.out.println("average: " + avg);
		hr();
		total = 0;
		System.out.println("Optimal: ");
		System.out.print("results: ");
		for(int i = 0; i < refStrings.length; i++) {
			p = new PageManager(refStrings[i], cap);
			temp = p.optimal();
			System.out.print(temp + " ");
			total += p.optimal();
		}
		System.out.println();
		avg = total/refStrings.length;
		System.out.println("average: " + avg);
		
		
		
		
		
	}
	
	/*
	 * Code used to generate ReferenceStrings.txt
	 * generates 50 lines of 30 randomly generated integers from 1-9
	 * each on a new line
	 */
	public static void generateRefStrings() {
		try {
			File f = new File("ReferenceStrings.txt");
			f.createNewFile();
			FileWriter writer = new FileWriter("ReferenceStrings.txt");
			for(int i = 1; i <= 50; i++) {
				String content = "";
				
				// generate 30 random numbers
				String refStr = "";
				for(int j = 0; j < 30; j++) {
					refStr += Integer.toString(randInt(-1,9));
				}
				if(i == 1)
					content += refStr;
				else
					content += "\n" + refStr;
				
				writer.write(content);
				
			} 
			writer.close();
		} catch (IOException e) {
			
		}
	}
	
	/*
	 * Reads a reference string file and returns all reference strings in an array of Strings
	 */
	public static String[] readRefStrings(String filename) {
		try {
			String[] result = new String[50];
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			int i = 0;
			while(sc.hasNextLine()) {
				result[i] = sc.nextLine();
				i++;
			}
			sc.close();
			return result;
		} catch (FileNotFoundException e) {
			System.out.println("something went wrong");
			return null;
		}
	}
	
	// generates a random integer between the maximum and the minimum. (only inclusive of the maximum)
    public static int randInt(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
    
    // prints a long line. that's it.
    public static void hr() {
    	System.out.println("---------------------------------------------------------------------------");
    }

}
