import java.util.ArrayList;
import java.util.Scanner;

public class PageManager {
	// Page frame
	public ArrayList<Page> pages;
	// Reference string
	public int[] refStr;
	public int pageFrames;
	
	// initializes frame and reference string
	public PageManager(String ref, int framecount) {
		refStr = new int[ref.length()];
		for(int i = 0; i < ref.length(); i++) {
			refStr[i] = Character.getNumericValue(ref.charAt(i));
		}
		pageFrames = framecount;
		pages = new ArrayList<Page>();

	}
	
	/*
	 * First in, First out algorithm
	 */
	public int FIFO() {
		int faults = 0;
		int ptr = 0;
		for(int i = 0; i < refStr.length; i++) {
			// fill first n slots, giving 8 faults
			while(i < pageFrames) {
				pages.add(new Page(refStr[i]));
				faults++;
				i++;
			}
			
			boolean hit = false;
			int ref = refStr[i];
			for(int j = 0; j < pages.size(); j++) {
				if(pages.get(j).pageNum == ref) {
					//hit!
					hit = true;
					break;
				}
			}
			
			// if we have no hits
			if(hit == false) {
				pages.set(ptr, new Page(ref));
				ptr++;
				// overflow ptr to stay withing 8 items
				if(ptr == pageFrames) ptr = 0;
				faults++;
			}
		}
		
		return faults;
	}
	
	/*
	 * Least Recently Used Algorithm
	 */
	public int LRU() {
		int faults = 0;
		for(int i = 0; i < refStr.length; i++) {
			//check for a hit in the page frame
			boolean hit = false;
			int ref = refStr[i];
			for(int j = 0; j < pages.size(); j++) {
				if(pages.get(j).pageNum == ref) {
					//hit!
					//System.out.println("hit");
					hit = true;
					pages.add(new Page(pages.get(j).pageNum));
					pages.remove(j);
					break;
				}
			}
			
			// if we have no hits
			if(!hit) {
				if(pages.size() == pageFrames) {
					//System.out.println("miss");
					pages.add(new Page(ref));
					pages.remove(0);
					faults++;
				} else {
					//System.out.println("filling miss");
					pages.add(new Page(refStr[i]));
					faults++;
				}
			}

			
			
			// printing
			/*
			for(int l = 0; l < pages.size(); l++) {
				System.out.print(pages.get(l).pageNum);
				System.out.print(" ");
			}
			System.out.println();
			*/
		}
		//System.out.println("========================================================");
		return faults;
	}
	
	/*
	 * Optimal Algorithm
	 */
	public int[] optimal() {
		
		
		
		return null;
	}
}
