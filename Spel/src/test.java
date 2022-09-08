import java.util.List;
import java.util.ArrayList;

public class test {
	
	private List<Integer> p;

	
	
	public test(){
		p = new ArrayList<Integer>();
	}
	
	
	public static void main(String[] args) {
		
		
		
		
		double a = System.nanoTime();
		double k = (Math.pow(10, 12));
		int b = 50*2+7;
		
		long c = System.nanoTime();
		System.out.println(a / k);
		System.out.println(c);
		System.out.println(k);

		// TODO Auto-generated method stub

	}

}
