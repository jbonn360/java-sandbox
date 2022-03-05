
public class VehicleDetailsPrinter {
	public VehicleDetailsPrinter(Vehicle vehicle) {
		System.out.println(String.format("This vehicle is a %s %s. The following is its overview: %s",
				vehicle.getMake(), vehicle.getModel(), vehicle.getOverview()));
	}
}
