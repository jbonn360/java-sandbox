
public interface MotorVehicle extends Vehicle {
	@Override
	public default String getOverview() {
	    return "This is a Motor Vehicle";
	}
}
