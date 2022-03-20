
public class Main {
	public static void main(String[] args) {
		final UseFoo useFoo = new UseFoo();
		
		Foo foo = parameter -> parameter + " from lambda";
		
		Foo foo2 = parameter -> duplicateStringTenTimes(parameter);
		
		//equivalent to the above
		Foo foo3 = Main::duplicateStringTenTimes;
		
		System.out.println("--------------------------------");
		System.out.println(useFoo.add("Message ", foo));
		
		System.out.println("--------------------------------");
		System.out.println(useFoo.add("Message ", foo2));
		
		System.out.println("--------------------------------");
		System.out.println(useFoo.add("Message ", foo3));
	}
	
	private static String duplicateStringTenTimes(String param) {
		String result = "";
		
		for(int i = 0; i < 10; i ++) {
			result = result.concat(param + "\n");
		}
		
		return result;
	}
}
