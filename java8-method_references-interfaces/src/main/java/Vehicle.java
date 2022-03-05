
public interface Vehicle {
	public String getMake(); // <--- defaults to abstract

	public abstract String getModel();

	// Static methods are available ONLY via the class/interface type in which they
	// were declared. They can never be overridden. 
	// (The implementing class can however have it's own version with the same name)
	public static String producer() {
		return "Vehicle Producer";
	}

	// Code inside 'default' method in an interface will only be called if the 
	// implementing class does not override it with its own version.
	// Hence, it does not need to be overridden like the other (abstract) methods 
	// in an interface
	public default String getOverview() {
		return "This is a Vehicle";
	}

	public default void printMakeAndModel() {
		System.out.println(String.format("This is a %s %s", getMake(), getModel()));
	}

	public default void compareTo(Vehicle toCompare) {

		System.out.println(String.format("Comparing this %s %s to a %s %s", getMake(), getModel(), toCompare.getMake(),
				toCompare.getModel()));

	}
}
