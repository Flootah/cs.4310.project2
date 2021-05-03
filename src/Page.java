/*
 * the Page class is implemented as an object.
 * This is mostly a holdover from an older implementation of this code,
 * where other fields were required to make Pages work.
 * 
 * It remains this way as a future-proof of sorts.
 * and because rewriting all of it now would be troublesome
 */
public class Page {
	public int pageNum;
	
	public Page(int a) {
		pageNum = a;
	}
}
