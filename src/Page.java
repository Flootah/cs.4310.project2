
public class Page {
	public int pageNum;
	public int timesUsed;
	public int futureUses;
	

	public Page(int a) {
		pageNum = a;
		timesUsed = 0;
		futureUses = 0;
	}

	public void timesUsedpp() {
		timesUsed++;
	}
	
	public void futureUsespp() {
		futureUses++;
	}
}
