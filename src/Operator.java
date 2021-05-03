
public class Operator {

	public static void main(String[] args) {
		PageManager p = new PageManager("130356", 3);
		
		//System.out.println(p.FIFO());
		
		PageManager q = new PageManager("021640103121", 4);
		
		//System.out.println(q.FIFO());
		
		PageManager l = new PageManager("135732345051740", 5);
		System.out.println(l.LRU());
		
		PageManager i = new PageManager("70120304230321201701", 3);
		System.out.println(i.LRU());
	}

}
