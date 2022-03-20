import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Main2 {
	//from(1): https://www.baeldung.com/java-8-functional-interfaces
	//form(2): https://www.baeldung.com/java-8-lambda-expressions-tips
	
	public static void main(String[] args) {
		int[] total = new int[1];
		
		System.out.println(total[0]);
		
		System.out.println("-----------------------");
		
		Runnable r = () -> total[0]++; //<----- increments the variable
		r.run();
		
		System.out.println(total[0]);
		
		System.out.println("-----------------------");
		
		FooInt fooInt = param -> incrementInt(param); //<----- does NOT increment the variable
		
		FooInt fooInt2 = Main2::incrementInt;
		
		System.out.println(fooInt.method(total[0]));
		System.out.println(fooInt2.method(total[0]));
		
		System.out.println("-----------------------");
		
		System.out.println(total[0]);
		
		System.out.println("-----------------------");
		
		Map<String, Integer> nameMap = new HashMap<>();
		nameMap.put("John", 26);
		Integer value = nameMap.computeIfAbsent("John", s -> s.length());
		
		System.out.println(value);
		
		System.out.println("-----------------------");
		
		composeDemo2();
	}
	
	public static int incrementInt(int toIncrement) {
		toIncrement++;
		
		return toIncrement;
	}
	
	public static void composeDemo() {
		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";

		Function<Integer, String> quoteIntToString = quote.compose(intToString);

		System.out.println(quoteIntToString.apply(5));
	}
	
	public static void composeDemo2() {		
		List<String> strings = new ArrayList<String>();
		
		strings.add("Dog");
		strings.add("Cat");
		strings.add("Chicken");		
		
		strings.forEach(System.out::print); //<---- needs spaces
		
		System.out.println("\n-----------------------");		
		
		strings.forEach(s -> System.out.print(s.concat(" "))); //<---- good enough, clean
		
		System.out.println("\n-----------------------");		
		
		// the long ass, wannabe good programmer, complicated way
		Function<String, String> formatter = s -> s + " ";
		Function<String, String> printer = (s) -> { System.out.print(s); return null; };		
		Function<String, String> printString = printer.compose(formatter);
		
		strings.forEach(s -> printString.apply(s));
		
		System.out.println("\n-----------------------");	
	}
}
