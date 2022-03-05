
public class Driver {
	private String name;

	public Driver(String name) {
		this.name = name;
	}

	public void drive(Vehicle vehicle) {
		System.out.println(String.format("Driver %s is driving a %s %s", name, vehicle.getMake(), vehicle.getModel()));
	}
}
