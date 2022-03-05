
public class Main {
	public static void main(String[] args) {
		Foo foo = parameter -> parameter + " from lambda";
		String result = new UseFoo().add("Message ", foo);
		
		System.out.println(result);
	}
}
