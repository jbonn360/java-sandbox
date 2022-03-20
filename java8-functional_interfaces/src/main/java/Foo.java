@FunctionalInterface // limits this interface to the spec of a functional interface
public interface Foo {
	// functional interface can only have ONE abstract method
	// it CAN have other methods that are not abstract though
    String method(String string);
}
