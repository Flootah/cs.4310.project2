import java.util.ArrayList;


/*
 * PageManager Class.
 * This class is used to parse a single Reference String and emulates a Page Frame to process them.
 * Once constructed, a PageManager instance has three separate functions that can be used to parse
 * a ref string, and print out the process.
 * the FIFO() function is used to execute the First In, First out algorithm.
 * the LRU() function is used to execute the Least Recently Used algorithm.
 * the optimal() function is used to execute the most optimal algorithm.
 * 
 * each function returns the number of faults that occurred in its process, as an integer.
 */
public class PageManager {
	// Page frame, as an arraylist of Page objects
	public ArrayList<Page> pages;
	// Reference string
	public int[] refStr;
	// frame capacity
	public int frameCap;
	
	// initializes frame and reference string
	public PageManager(String ref, int framecount) {
		refStr = new int[ref.length()];
		for(int i = 0; i < ref.length(); i++) {
			refStr[i] = Character.getNumericValue(ref.charAt(i));
		}
		frameCap = framecount;
		pages = new ArrayList<Page>();

	}
	
	/*
	 * First in, First out algorithm
	 */
	public int FIFO() {
		pages = new ArrayList<Page>();
		int faults = 0;
		// pointer to next page to be replaced
		int ptr = 0;
		// iterate through all of reference string
		for(int i = 0; i < refStr.length; i++) {
			// hit/miss variable
			boolean hit = false;
			// store integer from reference String
			int ref = refStr[i];
			// check if reference string hits on any of our pages
			for(int j = 0; j < pages.size(); j++) {
				if(pages.get(j).pageNum == ref) {
					//hit!
					hit = true;
					break;
				}
			}
			
			// if we have no hits
			if(!hit) {
				// if page frame is full, replace oldest addition.
				// otherwise, just add new page to frame.
				if(pages.size() == frameCap) {
					pages.set(ptr, new Page(ref));
					ptr++;
					// overflow ptr to stay within index
					if(ptr == frameCap) ptr = 0;
				} else {
					pages.add(new Page(ref));
				}
				// increment fault count for miss
				faults++;
			}
			//printPages(ref, hit);
		}
		return faults;
	}
	
	/*
	 * Least Recently Used Algorithm
	 */
	public int LRU() {
		pages = new ArrayList<Page>();
		int faults = 0;
		// iterate through all of reference string
		for(int i = 0; i < refStr.length; i++) {
			//check for a hit in the page frame
			boolean hit = false;
			//store current int
			int ref = refStr[i];
			// check for hit on page frame, if we hit, move page to end of frame
			for(int j = 0; j < pages.size(); j++) {
				if(pages.get(j).pageNum == ref) {
					hit = true;
					pages.add(new Page(pages.get(j).pageNum));
					pages.remove(j);
					break;
				}
			}
			
			// if we have no hits
			if(!hit) {
				// if frame is full, remove first page and append new page to the end
				// otherwise, simply append new page.
				if(pages.size() == frameCap) {
					pages.add(new Page(ref));
					pages.remove(0);

				} else {
					pages.add(new Page(ref));
				}
				faults++;
			}
			//printPages(ref, hit);
		}
		return faults;
	}
	
	/*
	 * Optimal Algorithm
	 */
	public int optimal() {
		pages = new ArrayList<Page>();
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
					break;
				}
			}
			
			// if we have no hits
			if(!hit) {
				if(pages.size() == frameCap) {
					// index of least used page
					int leastUsedPage = 0;
					// counter to track how far the least used page is
					int latestUse = 0;
					// for each page in pages
					for(int j = 0; j < pages.size(); j++) {
						int curpageNum = pages.get(j).pageNum;
						// temp counter for how far next use is
						int lucounter = 0;
						// check all future characters, and determine how far next use is.
						for(int k = i; k < refStr.length; k++) {
							int temp = refStr[k];
							if(temp == curpageNum) {
								break;
							} else {
								lucounter++;
							}
						}
						// if next use is farther than current least used page, replace it.
						if(lucounter > latestUse) {
							latestUse = lucounter;
							leastUsedPage = j;
						}
					}
					// once complete, the index of the least used page is in leastUsedPage.
					// we now replace
					pages.add(leastUsedPage + 1, new Page(ref));
					pages.remove(leastUsedPage);
					// increase fault count
					faults++;
				} else {
					//System.out.println("filling miss");
					pages.add(new Page(ref));
					faults++;
				}
			}
			//printPages(ref, hit);
		}
		return faults;
	}
	
	/*
	 * Prints current page frame and reference string character in a table format.
	 * parameters are to determine which ref string character to print,
	 * and whether we need to print HIT or MISS
	 */
	private void printPages(int ref, boolean hit) {
		System.out.printf("%-6s", ref);
		for(int l = 0; l < frameCap; l++) {
			if(l > pages.size() - 1) {
				System.out.print("|");
				System.out.printf("%-3s", "");
			} else {
				System.out.print("|");
				System.out.printf(" " + pages.get(l).pageNum + " ");
			}

		}
		System.out.print("|");
		System.out.printf("%-6s", (hit ? "  HIT" : "  MISS"));
		System.out.println();
	}
}
