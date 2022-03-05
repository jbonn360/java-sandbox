import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {		
		final List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		vehicles.add(new Car("Lamborghini", "Urraco"));
		
		// Anonymous inner class
		vehicles.add(new MotorVehicle() {			
			@Override
			public String getModel() {
				return "Dino";
			}
			
			@Override
			public String getMake() {
				return "Ferrari";
			}
		});
			
		// Method references
		//1. Reference to a static method
		vehicles.forEach(Main::consume);
		System.out.println("\n---------------------------");
		
		//2. Reference to an instance method of a particular object
		final Driver driver = new Driver("Justin");		
		vehicles.forEach(driver::drive);
		System.out.println("\n---------------------------");
		
		//3. Reference to instance method of an arbitrary object of a given type
		vehicles.forEach(Vehicle::printMakeAndModel);
		System.out.println("\n---------------------------");
		
		//4. Reference to a constructor
		vehicles.forEach(VehicleDetailsPrinter::new);
		System.out.println("\n---------------------------");
	}
	
	public static void consume(Vehicle vehicle) {
		System.out.println(vehicle.getOverview());
	}

}
