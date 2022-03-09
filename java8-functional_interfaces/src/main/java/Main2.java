
public class Main2 {
	public static void main(String[] args) {
		int[] total = new int[1];
		
		System.out.println(total[0]);
		
		System.out.println("-----------------------");
		
		Runnable r = () -> total[0]++; //<----- increments the variable
		r.run();
		
		System.out.println(total[0]);
		
		System.out.println("-----------------------");
		
		FooInt fooInt = (param) -> incrementInt(param); //<----- does NOT increment the variable
		
		System.out.println(fooInt.method(total[0]));
		
		System.out.println("-----------------------");
		
		System.out.println(total[0]);
	}
	
	public static int incrementInt(int toIncrement) {
		toIncrement++;
		
		return toIncrement;
	}
}
