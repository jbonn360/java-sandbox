
public class Car implements MotorVehicle {	
	private String make;
	private String model;		
	
	public Car(String make, String model) {
		this.make = make;
		this.model = model;		
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public String getMake() {
		return make;
	}
	
	@Override
	public String getOverview() {
		return "This is a Car";
	}
	
	public String producer() {
		return "Car Producer";
	}
}
