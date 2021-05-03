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
		// simple UI to retrieve name of data file from user input.
		Scanner io = new Scanner(System.in);
		String filename;
		// !!! This print varies between Algorithm versions
		System.out.println("Optimal Page Replacement Algorithm");
		System.out.println();
		while(true) {
			System.out.println("please enter file with required data: ");
			System.out.println("or enter 'exit' to exit.");
			String response = io.next();
			if(response.equals("exit")) System.exit(0);
			try {
				File f = new File(response);
				Scanner read = new Scanner(f);
				read.close();
				filename = response;
				System.out.println("File read success!");
				hr();
				break;
			} catch(FileNotFoundException e) {
				System.out.println("File not Found! Try again.");
				hr();
			}
		}
		io.close();
		
		// read input from file and store into respective arrays
		String[][] input = readRefStrings(filename);
		String[] caps = input[0];
		String[] refStrings = input[1];
		// prepare variables for execution
		PageManager p = null;
		int total = 0;
		int avg = 0;
		int faults;
		// Execute algorithm and print results for each reference string in file
		System.out.println("results: ");
		for(int i = 0; i < refStrings.length; i++) {
			p = new PageManager(refStrings[i], caps[i]);
			// !!! This function below varies between Algorithm versions
			// !!! this line can call either p.FIFO(), p.LRU(), or p.optimal()
			faults = p.optimal();
			System.out.println("Faults: " + faults);
			hr();
			total += faults;
		}
		System.out.println();
		avg = total/refStrings.length;
		System.out.println("average faults: " + avg);
		System.out.println("Complete!");
		
	}
	
	/*
	 * Code used to generate ReferenceStrings.txt
	 * generates 50 lines of 30 randomly generated integers from 1-9
	 * each on a new line
	 */
	public static void generateRefStrings(String filename) {
		try {
			File f = new File(filename);
			f.createNewFile();
			FileWriter writer = new FileWriter(filename);
			for(int i = 1; i <= 50; i++) {
				String content = "";
				// generate number for cap, 3-8 inclusive
				String cap = "";
				cap = Integer.toString(randInt(2,8));
				
				// generate 30 random numbers
				String refStr = "";
				for(int j = 0; j < 30; j++) {
					refStr += Integer.toString(randInt(-1,9));
				}
				if(i == 1)
					content += cap + "\n" + refStr;
				else
					content += "\n" + cap + "\n" + refStr;
				
				writer.write(content);
				
			} 
			writer.close();
		} catch (IOException e) {
			
		}
	}
	
	/*
	 * Reads a reference string file and returns all reference strings in an array of Strings
	 */
	public static String[][] readRefStrings(String filename) {
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f); 
			int linecount = 0;
			while(sc.hasNextLine()) {
				sc.nextLine();
				linecount++;
			}
			sc.close();
			sc = new Scanner(f); 
			String[] refStrings = new String[linecount/2];
			String[] caps = new String[linecount/2];

			int i = 0;
			while(sc.hasNextLine()) {
				caps[i] = sc.nextLine();
				refStrings[i] = sc.nextLine();
				i++;
			}
			sc.close();
			return new String[][] {caps, refStrings};
		} catch (FileNotFoundException e) {
			System.out.println("something went wrong");
			return null;
		}
	}
	
	/*
	 * Obsolete function for translating old ReferenceString.txt format to a new one.
	 */
	public static void translate() {
		try  {
			File f = new File("TestingCaseStrings.txt");
			FileWriter writer = new FileWriter("translated.txt");
			String content = "";
			
			for(int i = 3; i <= 6; i++) {
				Scanner sc = new Scanner(f);
				while(sc.hasNextLine()) {
					content += i + "\n" + sc.nextLine() + "\n";
				}
				sc.close();
			}
			
			writer.write(content);
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
